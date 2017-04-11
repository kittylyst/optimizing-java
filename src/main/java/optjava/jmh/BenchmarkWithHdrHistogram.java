package optjava.jmh;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.HdrHistogram.Histogram;

/**
 * Generated from simple GC logs via:
 * cat premature3.log | perl -ne 'print "$1\n" if /^([0-9]+\.[0-9]+).*$/' > millis.txt
 * @author ben
 */
// tag::HDR_BENCHMARK[]
public class BenchmarkWithHdrHistogram {
    private static final long NORMALIZER = 1_000_000;
    
    private static final Histogram HISTOGRAM
            = new Histogram(TimeUnit.MINUTES.toMicros(1), 2);
    
    public static void main(String[] args) throws Exception {
        final List<String> values = Files.readAllLines(Paths.get(args[0]));
        double last = 0;
        for (final String tVal : values) {
            double parsed = Double.parseDouble(tVal);
            double gcInterval = parsed - last;
            last = parsed;
            HISTOGRAM.recordValue((long)(gcInterval * NORMALIZER));
        }
        HISTOGRAM.outputPercentileDistribution(System.out, 1000.0);
    }
}
// end::HDR_BENCHMARK[]

package optjava.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations=5)
@Measurement(iterations=5)
public class ConsumeCPU {
	@Benchmark
    public void consume_0000() {
        Blackhole.consumeCPU(0);
    }

    @Benchmark
    public void consume_0001() {
        Blackhole.consumeCPU(1);
    }

    @Benchmark
    public void consume_0002() {
        Blackhole.consumeCPU(2);
    }

    @Benchmark
    public void consume_0004() {
        Blackhole.consumeCPU(4);
    }

    @Benchmark
    public void consume_0008() {
        Blackhole.consumeCPU(8);
    }

    @Benchmark
    public void consume_0016() {
        Blackhole.consumeCPU(16);
    }

    @Benchmark
    public void consume_0032() {
        Blackhole.consumeCPU(32);
    }

    @Benchmark
    public void consume_0064() {
        Blackhole.consumeCPU(64);
    }

    @Benchmark
    public void consume_0128() {
        Blackhole.consumeCPU(128);
    }

    @Benchmark
    public void consume_0256() {
        Blackhole.consumeCPU(256);
    }

    @Benchmark
    public void consume_0512() {
        Blackhole.consumeCPU(512);
    }

    @Benchmark
    public void consume_1024() {
        Blackhole.consumeCPU(1024);
    }
}



//# Run complete. Total time: 00:02:04
//
//Benchmark                Mode  Cnt     Score     Error  Units
//ConsumeCPU.consume_0000  avgt    5     2.431 ±   0.761  ns/op
//ConsumeCPU.consume_0001  avgt    5     3.470 ±   0.279  ns/op
//ConsumeCPU.consume_0002  avgt    5     4.574 ±   0.618  ns/op
//ConsumeCPU.consume_0004  avgt    5     6.535 ±   1.207  ns/op
//ConsumeCPU.consume_0008  avgt    5    11.658 ±   1.789  ns/op
//ConsumeCPU.consume_0016  avgt    5    25.543 ±   5.375  ns/op
//ConsumeCPU.consume_0032  avgt    5    60.252 ±   4.279  ns/op
//ConsumeCPU.consume_0064  avgt    5   132.653 ±   6.821  ns/op
//ConsumeCPU.consume_0128  avgt    5   274.877 ±   9.914  ns/op
//ConsumeCPU.consume_0256  avgt    5   557.129 ±  32.590  ns/op
//ConsumeCPU.consume_0512  avgt    5  1148.191 ±  74.115  ns/op
//ConsumeCPU.consume_1024  avgt    5  2276.372 ± 107.088  ns/op

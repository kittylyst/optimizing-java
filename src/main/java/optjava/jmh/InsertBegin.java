package optjava.jmh;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 20, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(1)
public class InsertBegin {
	private static final String item = "*";
	private static final int N = 1_000_000;
	
	private static List<String> arrayList = new ArrayList<>();
	private static List<String> linkedList = new LinkedList<>();
	
	@Setup(Level.Invocation)
	public static final void setup() {
		arrayList = new ArrayList<>();
		linkedList = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			arrayList.add(item);
			linkedList.add(item);
		}
	}
	
	@Benchmark
	public String beginArrayList() {
		 arrayList.add(0, item);
		 return arrayList.get(0);
	}
	
	@Benchmark
	public String beginLinkedList() {
		 linkedList.add(0, item);
		 return linkedList.get(0);
	}
}

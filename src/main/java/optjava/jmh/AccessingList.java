package optjava.jmh;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
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
public class AccessingList {
	private static final String item = "*";
	private static final int N = 1_000_000;
	
	private static final List<String> arrayList = new ArrayList<>();
	private static final List<String> linkedList = new LinkedList<>();
	
	@Setup
	public static final void setup() {
		for (int i = 0; i < N; i++) {
			arrayList.add(item);
			linkedList.add(item);
		}
	}
	
	@Benchmark
	public String accessArrayList() {
		return arrayList.get(500_000);
	}
	
	@Benchmark
	public String accessLinkedList() {
		return linkedList.get(500_000);
	}
}

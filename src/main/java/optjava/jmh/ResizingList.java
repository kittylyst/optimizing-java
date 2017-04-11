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
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 20, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(1)
public class ResizingList {
	
	private final String item = "*";

	@Benchmark
	public List<String> properlySizedArrayList() {
		List<String> list = new ArrayList<>(1_000_000);
		for(int i=0; i < 1_000_000; i++) {
			list.add(item);
		}	
		return list;		
	}
	
	@Benchmark
	public List<String> resizingArrayList() {
		List<String> list = new ArrayList<>();
		for(int i=0; i < 1_000_000; i++) {
			list.add(item);
		}	
		return list;
	}
	
	@Benchmark
	public List<String> linkedList() {
		List<String> list = new LinkedList<>();
		for(int i=0; i < 1_000_000; i++) {
			list.add(item);
		}	
		return list;		
	}
}

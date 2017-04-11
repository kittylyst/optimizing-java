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

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations=5)
@Measurement(iterations=5)
public class Problem1 {
	
	private double x = 5;
	
	@Benchmark
	public void emptyMethod() {
		//Empty
	}
	
	@Benchmark
	public double calculateLog() {
		return Math.log(x);
	}
	
}




















//
//# Run complete. Total time: 00:13:27
//
//Benchmark               Mode  Cnt  Score   Error   Units
//Problem1.calculateLog  thrpt  200  2.903 ± 0.056  ops/ns
//Problem1.emptyMethod   thrpt  200  2.965 ± 0.015  ops/ns
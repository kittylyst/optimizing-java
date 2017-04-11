package optjava.jmh;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class OldBenchmark {

	private static final int N = 1_000_000;
	private static final int I = 100;
	private static final List<Integer> testData = new ArrayList<>();

	public static void main(String[] args) {
		Random randomGenerator = new Random();
		for (int i = 0; i < N; i++) {
			testData.add(randomGenerator.nextInt(Integer.MAX_VALUE));
		}
		System.out.println("Standard Sort");

		for (int x = 0; x < 5; x++) {
			double startTime = System.currentTimeMillis();

			for (int i = 0; i < I; i++) {
				testData.stream().sorted().collect(Collectors.toList());
			}

			double endTime = System.currentTimeMillis();
			double timePerOperation = ((endTime - startTime) / I) / 1000;
			System.out.println("Iteration " + x + ": " + (1 / timePerOperation)
					+ "op/s");
		}

		System.out.println("Parallel Sort");

		for (int x = 0; x < 5; x++) {
			double startTime = System.currentTimeMillis();

			for (int i = 0; i < I; i++) {
				testData.parallelStream().sorted().collect(Collectors.toList());
			}

			double endTime = System.currentTimeMillis();
			double timePerOperation = ((endTime - startTime) / I) / 1000;
			System.out.println("Iteration " + x + ": " + (1 / timePerOperation)
					+ "op/s");
		}

	}
}

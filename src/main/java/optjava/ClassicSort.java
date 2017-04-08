package optjava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// tag::CLASSIC_SORT[]
public class ClassicSort {
    private static final int NUM_ITEMS = 100_000;
    private static final int ITERATIONS = 300_000;
    private static final List<Integer> testData = new ArrayList<>();

    public static void main(String[] args) {
        Random randomGenerator = new Random();
        for (int i = 0; i < NUM_ITEMS; i++) {
            testData.add(randomGenerator.nextInt(Integer.MAX_VALUE));
        }

        System.out.println("Testing Sort Algorithm");

        double startTime = System.nanoTime();

        for (int i = 0; i < ITERATIONS; i++) {
            List<Integer> copy = new ArrayList<>(testData);
            Collections.sort(copy);
        }

        double endTime = System.nanoTime();
        double timePerOperation = ((endTime - startTime) / (NUM_ITEMS * ITERATIONS));
        System.out.println("Result: " + (1 / timePerOperation) + " op/s");
    }
}
// end::CLASSIC_SORT[]

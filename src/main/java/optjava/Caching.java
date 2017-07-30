package optjava;

// tag::CACHE_EFFECT[]
public class Caching {
    private final int ARR_SIZE = 10 * 1024 * 1024;
    private final int[] testData = new int[ARR_SIZE];

    private void run() {
        for (int i = 0; i < 10_000; i++) {
            touchEveryLine();
            touchEveryItem();
        }
        System.out.println("Line     Item");
        for (int i = 0; i < 100; i++) {
            long t0 = System.nanoTime();
            touchEveryLine();
            long t1 = System.nanoTime();
            touchEveryItem();
            long t2 = System.nanoTime();
            long elEveryLine = t1 - t0;
            long elEveryItem = t2 - t1;
            System.out.println(elEveryLine + " " + elEveryItem);
        }
    }

    private void touchEveryItem() {
        for (int i = 0; i < testData.length; i++)
            testData[i]++;
    }

    private void touchEveryLine() {
        for (int i = 0; i < testData.length; i += 16)
            testData[i]++;
    }

    public static void main(String[] args) {
        Caching c = new Caching();
        c.run();
    }
}
// end::CACHE_EFFECT[]

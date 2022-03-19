package optjava.execution;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicsExamples implements Runnable {

    private final AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        AtomicsExamples ae = new AtomicsExamples();
        Thread tA = new Thread(ae);
        Thread tB = new Thread(ae);
        tA.start();
        tB.start();
        tA.join();
        tB.join();
    }


    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("Starting on thread: "+ name);
        for (int i = 0; i < 100; i += 1) {
            try {
                Thread.sleep((long) (100 * Math.random()));
                System.out.println(name +": "+ count.incrementAndGet());
            } catch (InterruptedException e) {
                System.out.println("Interrupted on "+ name);
            }
        }
        System.out.println("Finished on "+ name);
    }

}

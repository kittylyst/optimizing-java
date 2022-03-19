package optjava.execution;

import java.util.concurrent.*;

public class ExecExamples {

    public static void main(String[] args) {
        ExecExamples ee = new ExecExamples();
        ee.run();
    }

    private void run() {
        Runnable r = () -> {
            String name = Thread.currentThread().getName();
            System.out.println("Starting on thread: "+ name);
            try {
                Thread.sleep((long) (5 * Math.random()));
            } catch (InterruptedException e) {
                System.out.println("Interrupted on "+ name);
            }
            System.out.println("Finished on "+ name);
        };

        ExecutorService ex = Executors.newSingleThreadExecutor();
        ex.execute(r);
        ex.execute(r);
        ex.execute(r);
        ex.execute(r);
        ex.execute(r);

        ex.execute(r);
        ex.execute(r);
        ex.execute(r);
        ex.execute(r);
        ex.execute(r);

        ex.execute(r);
        ex.execute(r);
        ex.execute(r);
        ex.execute(r);
        ex.execute(r);

        ex.execute(r);
        ex.execute(r);
        ex.execute(r);
        ex.execute(r);
        ex.execute(r);

        ex.shutdown();

        while (!ex.isTerminated()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Exiting");
    }

//    private void run2() {
//        Callable<Integer> r = () -> {
//            System.out.println("Hello Thread: "+ Thread.currentThread().getName());
//            Thread.sleep((long) (1000 * Math.random()));
//            return 17;
//        };
//
//        ExecutorService x = null;
//        Future<Integer> fi = x.submit(r);
//        x.submit(r);
//        x.submit(r);
//        x.submit(r);
//        x.submit(r);
//        while (!fi.isDone()) {
//            try {
//                System.out.print(".");
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        // f.isDone() is TRUE - result has beeen generated
//        x.shutdown();
//        System.out.println();
//        try {
//            System.out.println("Result: "+ fi.get());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
}

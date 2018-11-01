package optjava;

import java.security.SecureRandom;

public final class OverLocking {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        SecureRandom sr = new SecureRandom();
        Runnable r = () -> {
            sr.setSeed(System.nanoTime());
            final int initialSleep = Math.abs(sr.nextInt() % 10_000);
            try {
                Thread.sleep(initialSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                try {
                    final int sleep = Math.abs(sr.nextInt() % 2_000);
                    Thread.sleep(sleep);
                    System.out.println("Thread: "+ Thread.currentThread().getName() +" finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread[] t = new Thread[20];
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(r);
            t[i].setName("Waiter-"+ i);
            t[i].start();
        }
        for (int i = 0; i < t.length; i++) {
            try {
                t[i].join();
                System.out.println("Thread: "+ t[i].getName() +" joined");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

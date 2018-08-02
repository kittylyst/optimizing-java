package optjava;

import java.security.SecureRandom;

public final class OverLocking {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        SecureRandom sr = new SecureRandom();
        Runnable r = () -> {
            synchronized (lock) {
                sr.setSeed(System.nanoTime());
                final int sleep = Math.abs(sr.nextInt() % 10000);
                try {
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

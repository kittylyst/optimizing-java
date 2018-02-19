package handsonmjc;

/**
 *
 * @author ben
 */
public class ThreadExample {

    public static void main(String[] args) throws Exception {
        Runnable r = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted!");
            }
            System.out.println("Finished");
        };

        doThread(r);
        System.out.println("Carry on...");
    }

    private static void doThread(Runnable r) throws Exception  {
        Thread t = new Thread(r);
        t.setName("Background");
        t.start();
        Thread.sleep(100);
        t.interrupt();
        Thread.sleep(100);
        System.out.println(t.isInterrupted());
        t.join();
    }
    
}

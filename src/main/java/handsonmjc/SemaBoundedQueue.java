package handsonmjc;

import java.util.concurrent.Semaphore;

/**
 *
 * @author ben
 */
public class SemaBoundedQueue implements BoundedQueue {

    private final Object[] items = new Object[100];
    private int next = 0;

    private final Semaphore occupied = new Semaphore(0);
    private final Semaphore free = new Semaphore(100);

    @Override
    public void put(Object x) throws InterruptedException {
        try {
            free.acquire();
        } catch (InterruptedException ignore) {
        }
        synchronized (this) {
            items[next++] = x;
        }
        occupied.release();
    }

    @Override
    public Object take() throws InterruptedException {
        Object out;
        try {
            occupied.acquire();
        } catch (InterruptedException ignore) {
        }
        synchronized (this) {
            out = items[--next];
            items[next] = null;
        }
        free.release();
        return out;
    }

}

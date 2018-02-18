package handsonmjc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author ben
 */
public class JUCBoundedQueue implements BoundedQueue {

    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    private final Object[] items = new Object[100];
    private int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            // If full, release lock & wait until we get it back
            while (count == items.length)
                notFull.await();

            items[putptr] = x;
            if (++putptr == items.length)
                putptr = 0;
            ++count;

            // signal notEmpty that it can try for the lock again
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            // If empty, release the lock & wait
            while (count == 0)
                notEmpty.await();

            Object x = items[takeptr];
            if (++takeptr == items.length)
                takeptr = 0;
            --count;

            // Signal notFull that it can stop waiting & try for the lock
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }

}

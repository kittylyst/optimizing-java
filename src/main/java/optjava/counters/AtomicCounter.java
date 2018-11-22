package optjava.counters;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

public final class AtomicCounter implements Counter {

    private static final Unsafe unsafe; // = Unsafe.getUnsafe();
    private static final long valueOffset;

    private volatile int value = 0;

    // setup to use Unsafe.compareAndSwapInt for updates
    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            valueOffset = unsafe.objectFieldOffset
                (AtomicCounter.class.getDeclaredField("value"));
            System.out.println("Offset: "+ valueOffset);
        } catch (Exception ex) { throw new Error(ex); }
    }

    /**
     * Atomically increments by one the current value.
     *
     * @return the updated value
     */
    public int increment() {
        return unsafe.getAndAddInt(this, valueOffset, 1) + 1;
    }

    /**
     * Gets the current value.
     *
     * @return the current value
     */
    public int get() {
        return value;
    }

    /**
     * Sets to the given value.
     *
     * @param newValue the new value
     */
    public void set(int newValue) {
        value = newValue;
    }

}

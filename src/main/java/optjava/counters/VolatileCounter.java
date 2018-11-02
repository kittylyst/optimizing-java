package optjava.counters;

/**
 *
 * @author ben
 */
public final class VolatileCounter implements Counter {

    private volatile int i = 0;

    public int increment() {
        return i = i + 1;
    }

    public int get() {
        return i;
    }

}

package optjava;

/**
 *
 * @author ben
 */
public class Counter {
    private volatile int i = 0;

    public int increment() {
        return i = i + 1;
    }
}

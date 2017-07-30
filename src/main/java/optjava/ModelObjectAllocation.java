package optjava;

/**
 * This class contains an amount of storage and possesses a lifetime in milliseconds.
 * After the object has "expired" it will become eligible for GC.
 * 
 * @author ben
 */
// tag::OBJECT_ALLOC[]
public class ModelObjectAllocation implements Runnable {
    private final byte[][] allocated;
    private final int lifeTime;

    public ModelObjectAllocation(final int x, final int y, final int liveFor) {
        allocated = new byte[x][y];
        lifeTime = liveFor;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(lifeTime);
            System.err.println(System.currentTimeMillis() +": "+ allocated.length);
        } catch (InterruptedException ex) {
        }
    }
}
// end::OBJECT_ALLOC[]
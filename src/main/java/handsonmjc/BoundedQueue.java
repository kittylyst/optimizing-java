/*
 * 
 * 
 * 
 */
package handsonmjc;

/**
 *
 * @author kittylyst
 */
public interface BoundedQueue {

    public void put(Object x) throws InterruptedException;

    public Object take() throws InterruptedException;

}

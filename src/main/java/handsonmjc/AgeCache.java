package handsonmjc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AgeCache {
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock readLock = rwl.readLock();
    private final Lock writeLock = rwl.writeLock();
    private Map<String, Integer> ageCache = new HashMap<>();
    
    public Integer getAge(String name) {
    	readLock.lock();
    	try {
    		return ageCache.get(name);
    	} finally {
			readLock.unlock();
		}
    }
    
    public void updateAge(String name, int newAge) {
    	writeLock.lock();
    	try {
    		ageCache.put(name, newAge);
    	} finally {
    		writeLock.unlock();
    	}
    }
    
}	

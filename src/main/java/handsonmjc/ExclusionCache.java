package handsonmjc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExclusionCache implements Cache {
    private final Lock lock = new ReentrantLock();
    private final Map<String, Integer> ageCache = new HashMap<>();

    public Integer getAge(String name) {
        lock.lock();
        try {
            return ageCache.get(name);
        } finally {
            lock.unlock();
        }
    }

    public void updateAge(String name, int newAge) {
        lock.lock();
        try {
            ageCache.put(name, newAge);
        } finally {
            lock.unlock();
        }
    }

}

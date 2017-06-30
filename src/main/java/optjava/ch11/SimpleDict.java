package optjava.ch11;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ben
 */
public class SimpleDict implements Map<String, String> {

    private final Node[] table;
    private int size;

    /**
     * SimpleDict requires a capacity (and is non-resizable). If the capacity
     * specified is not a power of 2, it is rounded down to the nearest power
     * of 2.
     * 
     * @param capacity  the number of hash buckets
     */
    public SimpleDict(int capacity) {
        if (capacity <= 0) {
            table = new Node[16];
        } else {
            byte count = 0;
            while (capacity > 0) {
                capacity = capacity >> 1;
                count++;
            }
            table = new Node[1 << (count - 1)];
        }
    }

    private static class Node implements Entry<String, String> {

        final int hash;
        final String key;
        String value;
        Node next;

        Node(int h, String k, String v, Node n) {
            hash = h;
            key = k;
            value = v;
            next = n;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public String setValue(String v) {
            value = v;
            return value;
        }
    }

    public int capacity() {
        return table.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return getNode(key) != null;
    }

    private Node getNode(Object key) {
        int hash = key.hashCode();
        Node[] tab;
        Node first, e;
        int n;
        String k;
        if ((tab = table) != null && (n = tab.length) > 0
                && (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && // always check first node
                    ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                do {
                    if (e.hash == hash
                            && ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

    @Override
    public String get(Object key) {
        // Null keys are not supported
        if (key == null)
            return null;

        int hash = key.hashCode();
        int i = indexFor(hash, table.length);
        for (Node e = table[i]; e != null; e = e.next) {
            String k;
            if (e.hash == hash
                    && ((k = e.key) == key || key.equals(k)))
                return e.value;
        }

        return null;
    }

    private int indexFor(int h, int length) {
        return h & (length - 1);
    }

    @Override
    public String put(String key, String value) {
        // Null keys are not supported
        if (key == null)
            return null;

        int hash = key.hashCode();
        int i = indexFor(hash, table.length);

        if (table[i] == null) {
            size++;
            table[i] = new Node(hash, key, value, null);
            return table[i].getValue();
        }
        Node curr = table[i];
        for (Node e = table[i]; e != null; e = e.next) {
            String k;
            if (e.hash == hash
                    && ((k = e.key) == key || key.equals(k))) {
                e.setValue(value);
                return e.value;
            }
            curr = e;
        }
        size++;
        curr.next = new Node(hash, key, value, null);
        return curr.next.getValue();
    }

    @Override
    public String remove(Object key) {
        // Null keys are not supported
        if (key == null)
            return null;

        int hash = key.hashCode();
        int i = indexFor(hash, table.length);

//        Node head = table[i];
//        if (head == null)
//            return null;
//        
//        if (head.hash == hash
//                && (head.key == key || key.equals(head.key))) {
//            table[i] = head.next;
//        }
        Node last = table[i];
        for (Node e = table[i]; e != null; e = e.next) {
            String k;
            if (e.hash == hash
                    && ((k = e.key) == key || key.equals(k))) {
                // Remove the Node
                last.next = e.next;
                size--;
                return e.value;
            }
            last = e;
        }

        return null;
    }

    @Override
    public void clear() {
        Node[] tab;
        if ((tab = table) != null && size > 0) {
            size = 0;
            for (int i = 0; i < tab.length; ++i)
                tab[i] = null;
        }
    }

    /////////////////////////////////////////////////////////////////////
    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<String> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

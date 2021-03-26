package optjava.collections;

import java.util.*;

public class Dictionary implements Map<String, String> {
    private Node[] table = new Node[8];
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String get(Object key) {
        if (key == null)
            return null;
        int hash = hash(key.hashCode());
        for (Node e = table[indexFor(hash, table.length)];
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
                return e.value;
        }
        return null;
    }

    @Override
    public String put(String key, String value) {
        if (key == null)
            return null;

        int hash = hash(key.hashCode());
        int i = indexFor(hash, table.length);
        for (Node e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                String oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }

        Node e = table[i];
        table[i] = new Node(hash, key, value, e);

        return null;
    }

    @Override
    public String remove(Object key) {
        int hash = (key == null) ? 0 : hash(key.hashCode());
        int i = indexFor(hash, table.length);
        Node prev = table[i];
        Node e = prev;

        while (e != null) {
            Node next = e.next;
            Object k;
            if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k)))) {
                size--;
                if (prev == e)
                    table[i] = next;
                else
                    prev.next = next;
            }
            prev = e;
            e = next;
        }

        return (e == null ? null : e.value);
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

    @Override
    public boolean containsKey(Object key) {
        int hash = (key == null) ? 0 : hash(key.hashCode());
        for (Node e = table[indexFor(hash, table.length)];
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null)
            return false;

        Node[] tab = table;
        for (int i = 0; i < tab.length ; i++)
            for (Node e = tab[i]; e != null ; e = e.next)
                if (value.equals(e.value))
                    return true;
        return false;
    }

    ////////////////////////////////
    // Unsupported

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> values() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        throw new UnsupportedOperationException();
    }

    ////////////////////////////////

    static int indexFor(int h, int length) {
        return h & (length-1);
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    static class Node implements Entry<String,String> {
        final int hash;
        final String key;
        String value;
        Node next;

        Node(int hash, String key, String value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final String getKey()        { return key; }
        public final String getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final String setValue(String newValue) {
            String oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Node) {
                Node e = (Node)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
}

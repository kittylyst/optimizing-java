package optjava.collections;

public class Box<T> {
    private T value;

    public void box(T t) {
        value = t;
    }

    public T unbox() {
        return value;
    }

    @Override
    public String toString() {
        return "Box{" +
                "value=" + value +
                ", unbox=" + unbox() +
                '}';
    }

    // static boxer() goes here
}
package optjava;

public class Main {

    public static void main(String[] args) {
        AtomicCounter ac = new AtomicCounter();
        System.out.println(ac.increment());
    }
}


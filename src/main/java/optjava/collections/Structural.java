package optjava.collections;

public class Structural {
        public static void main(String[] args) {
            (new Object() {
                public void bar() {
                    System.out.println("bar!");
                }
            }).bar();
        }
    }
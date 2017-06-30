package optjava.ch11;

/**
 *
 * @author ben
 */
// tag::ORDER_ITEM[]
public class OrderItem {
    private final long id;
    private final String description;
    private final double price;

    public OrderItem(long id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "id=" + id + ", description=" + description + ", price=" + price + '}';
    }
}
// end::ORDER_ITEM[]
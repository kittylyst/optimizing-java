package optjava.ch11;

import optjava.ch11.DeliverySchedule;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ben
 */
// tag::ORDER[]
public class Order {
    private final long id;
    private final List<OrderItem> items = new ArrayList<>();
    private DeliverySchedule schedule;

    public Order(long id) {
        this.id = id;
    }

    public DeliverySchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(DeliverySchedule schedule) {
        this.schedule = schedule;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public long getId() {
        return id;
    }
}
// end::ORDER[]
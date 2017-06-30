package optjava.ch11;

import java.time.LocalDate;

/**
 *
 * @author ben
 */
// tag::DELIVERY_SCHEDULE[]
public final class DeliverySchedule {
    private final LocalDate deliveryDate;
    private final String address;
    private final double deliveryCost;

    private DeliverySchedule(LocalDate deliveryDate, String address, double deliveryCost) {
        this.deliveryDate = deliveryDate;
        this.address = address;
        this.deliveryCost = deliveryCost;
    }
    
    public static DeliverySchedule of(LocalDate deliveryDate, String address, double deliveryCost) {
        return new DeliverySchedule(deliveryDate, address, deliveryCost);
    }

    @Override
    public String toString() {
        return "DeliverySchedule{" + "deliveryDate=" + deliveryDate + ", address=" + address + ", deliveryCost=" + deliveryCost + '}';
    }
}
// end::DELIVERY_SCHEDULE[]
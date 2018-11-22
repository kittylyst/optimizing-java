package optjava.agent;

/**
 *
 * @author kittylyst
 */
// tag::RUNTIME_RECORDER[]
public class RuntimeCostAccounter {
    private static final ThreadLocal<Long> allocationCost = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return 0L;
        }
    };

    public static void recordAllocation(final String typeName) {
        // More sophistication clearly necessary
        // E.g. caching approximate sizes for types that we encounter
        checkAllocationCost(1);
    }

    public static void recordArrayAllocation(final int length, final int multiplier) {
        checkAllocationCost(length * multiplier);
    }

    private static void checkAllocationCost(final long additional) {
        final long newValue = additional + allocationCost.get();
        allocationCost.set(newValue);
        // Take action? e.g. failing if some threshold has been exceeded
    }

    // This could be exposed, e.g via a JMX counter
    public static long getAllocationCost() {
        return allocationCost.get();
    }

    public static void resetCounters() {
        allocationCost.set(0L);
    }
}
// end::RUNTIME_RECORDER[]
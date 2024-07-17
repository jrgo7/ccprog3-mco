/**
 * Represents a deluxe room, whose base rate is 20% higher than that of
 * {@link Room}.
 */
public class DeluxeRoom extends Room {
    /** Initializes a DeluxeRoom instance as an instance of {@link Room}. */
    public DeluxeRoom(String name, double basePrice) {
        super(name, basePrice);
    }

    /**
     * {@inheritDoc} Calculated as 20% more than the base price of {@link Room}.
     */
    @Override
    public double getBasePrice() {
        return super.getBasePrice() * 1.20;
    }
}
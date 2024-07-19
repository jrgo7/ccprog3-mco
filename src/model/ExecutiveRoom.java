package src.model;

/**
 * Represents an executive room, whose base rate is 35% higher than that of
 * {@link Room}.
 */
public class ExecutiveRoom extends Room {
    /** Initializes a ExecutiveRoom instance as an instance of {@link Room}. */
    public ExecutiveRoom(String name, double basePrice) {
        super(name, basePrice);
    }

    /**
     * {@inheritDoc} Calculated as 35% more than the base price of {@link Room}.
     */
    @Override
    public double getBasePrice() {
        return super.getBasePrice() * 1.35;
    }
}

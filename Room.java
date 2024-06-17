public class Room {
    private String name;
    private double basePrice;

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public Room(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public boolean isAvailableOn(int date) {
        return true; // TODO
    }

    public String getAvailability() {
        // TODO: Probably a for loop spanning 1-31
        // TODO: check if available on that date and add to a string 
        return "TBA";
    }

    public String getDataString() {
        return String.format("""
            Room name: %s
            Price per night: %lf
            Availability: %s
            """,
            this.getName(),
            this.getBasePrice(),
            this.getAvailability()); // TODO
    }
}

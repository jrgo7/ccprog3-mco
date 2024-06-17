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
}

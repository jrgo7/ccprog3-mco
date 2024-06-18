import java.util.ArrayList;

public class Room {
    private String name;
    private double basePrice;
    private ArrayList<Reservation> reservations; // Necessary for isAvailableOn() - wafl

    public Room(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public String getName() {
        return name;
    }

    public String getAvailability() {
        String availabilityString = "Available on the following dates: ";
        for (int day = 1; day <= 31; day++) {
            if (this.isAvailableOn(day)) {
                availabilityString += String.valueOf(day) + " ";
            }
        }
        return availabilityString;
    }

    public String toString() {
        return String.format("""
                Room name: %s
                Price per night: %f
                Availability:\n\t%s
                """,
                this.getName(),
                this.getBasePrice(),
                this.getAvailability());
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public boolean isAvailableOn(int date) {
        for (Reservation reservation : reservations) {
            if (reservation.getCheckIn() <= date && date < reservation.getCheckOut()) {
                return false; // TODO: Crucify me if you must
            }
        }
        return true;
    }
}

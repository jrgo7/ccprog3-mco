public class Reservation {
    private String guestName;
    private int checkIn;
    private int checkOut;
    private Room room;

    public Reservation(String guestName, int checkIn, int checkOut, Room room) {
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.room = room;
    }

    // TODO
    public double getTotalPrice() {
        double basePrice = room.getBasePrice();
        return (checkIn - checkOut /* no + 1 */) * basePrice;
    }

    public String getPriceBreakdown() {
        return "TBA"; // TODO
    }

    public String getGuestName() {
        return guestName;
    }

    public Room getRoom() {
        return this.room;
    }

    public int getCheckIn() {
        return this.checkIn;
    }

    public int getCheckOut() {
        return this.checkOut;
    }

    public String getDataString() {
        return String.format("""
                   Guest: %s
                   Room Information: %s
                   Check-in: %s
                   Check-out: %s
                   Total price: %s
                   Price breakdown: %s
                   """,
                   this.getGuestName(),
                   this.getRoom().getDataString(),
                   this.getCheckIn(),
                   this.getCheckOut(),
                   this.getTotalPrice(),
                   this.getPriceBreakdown());
    }
}

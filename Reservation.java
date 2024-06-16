public class Reservation {
    private String guestName;
    private Date checkIn;
    private Date checkOut;
    private Room room;

    public Reservation(String guestName, Date checkIn, Date checkOut, Room room) {
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.room = room;
    }

    // TODO
    public double getTotalPrice() {
        return 0.0;
    }

    public Room getRoom() {
        return this.room;
    }
}

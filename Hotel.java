import java.util.ArrayList;

public class Hotel {
    private String name;
    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;
    private double basePrice;

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<Room>();
    }

    public String getName() {
        return this.name;
    }

    public int getRoomCount() {
        return this.rooms.size();
    }

    public double getEarnings() {
        return this.basePrice * getRoomCount();
    }

    public void setName(String name) {
        this.name = name;
    }

    // TODO: Add validation
    // * set return type to boolean?
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    // TODO
    // * set return type to boolean?
    public void addRoom(String name) {

    }

    // TODO
    public void removeRoom(int index) {

    }

    // TODO: Add validation
    // * set return type to boolean?
    public void addReservation(String guestName, Date checkIn, Date checkOut, String roomName) {
        Room room = new Room(roomName, this.basePrice);
        Reservation reservation = new Reservation(guestName, checkIn, checkOut, room);
        reservations.add(reservation);      
    }
}

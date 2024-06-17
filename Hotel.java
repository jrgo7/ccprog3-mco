import java.util.ArrayList;

public class Hotel {
    private String name;
    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;
    private double basePrice;

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<Room>();
        this.reservations = new ArrayList<Reservation>();
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
    // I think this is all the validation needed
    public boolean setBasePrice(double basePrice) {
        this.basePrice = basePrice;

        if (basePrice < 0 || !rooms.isEmpty())
            return false;

        for (Room room : rooms)
            room.setBasePrice(basePrice);
        return true;
    }

    // TODO
    // * set return type to boolean?
    // Done also add 50 room limit
    // We may also need to check if a room with `name` already exists
    public boolean addRoom(String name) {
        if (rooms.size() >= 50)
            return false;

        rooms.add(new Room(name, basePrice));
        return true;
    }

    // TODO
    public boolean removeRoom(int index) {
        // validation
        if (index < 0 || index >= rooms.size())
            return false;

        rooms.remove(index);
        return true;
    }

    // TODO: Add validation
    // * set return type to boolean?
    // room name has to exist first before you can make a reservation for it
    public boolean addReservation(String guestName, int checkIn, int checkOut, String roomName) {
        for (Room room : rooms)
            if (room.getName().equals(roomName)) {
                Reservation reservation = new Reservation(guestName, checkIn, checkOut, room);
                reservations.add(reservation);
                return true;
            }
        return false;
    }

    public ArrayList<String> getRoomsString() {
        ArrayList<String> roomsString = new ArrayList<String>();
        int i = 0;
        for (Room room : rooms) {
            roomsString.add(String.format("[%d] %s\n", 1 + i++, room.getName()));
        }
        return roomsString;
    }

    public Room getRoom(int roomIndex) {
        return this.rooms.get(roomIndex);
    }

    public ArrayList<String> getReservationsString() {
        ArrayList<String> reservationsString = new ArrayList<String>();
        int i = 0;
        // TODO: How to represent a single reservation?
        for (Reservation reservation : reservations) {
            reservationsString.add(String.format("[%d] %s booked by %s\n",
                    1 + i++, reservation.getRoom(), reservation.getGuestName()));
        }
        return reservationsString;
    }

    public Reservation getReservation(int reservationIndex) {
        return this.reservations.get(reservationIndex);
    }
}

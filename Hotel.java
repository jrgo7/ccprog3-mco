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
    this.basePrice = 1299;
  }

  public int countReservationsOnDay(int date) {
    int retval = 0;
    for (Reservation i : reservations) 
      if (date >= i.getCheckIn() && date <= i.getCheckOut())
        retval++;
    return retval;
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

  public Room getRoom(int index) {
    return rooms.get(index);
  }

  // TODO
  // * set return type to boolean?
  // Done also add 50 room limit
  public boolean addRooms(int count) {
    int start = 1;
    int initcount = rooms.size();

    if (rooms.size() != 0)
      start = Integer.parseInt(rooms.get(rooms.size() - 1).getName().substring(2)) + 1;
    
    for (int i = 0; i < count && rooms.size() < 50; i++)
      rooms.add(new Room("RM" + String.format("%03d", start++), basePrice));
    
    return rooms.size() != initcount;
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
  public boolean addReservation(String guestName, int checkIn, int checkOut,
      String roomName) {
    for (Room room : rooms)
      if (room.getName().equals(roomName)) {
        Reservation reservation = new Reservation(guestName, checkIn, checkOut,
            room);
        reservations.add(reservation);
        return true;
      }
    return false;
  }

  public void addReservation(String guestName, int checkIn, int checkOut,
      Room room) {
        reservations.add(new Reservation(guestName, checkIn, checkOut, room));

  }

  public String[] getRoomNames() {
    int i, count = rooms.size();
    String[] retval = new String[count];
    
    for (i = 0; i < count; i++)
      retval[i] = rooms.get(i).getName();
    
    return retval;
  }

  public String getDataString() {
    return String.format("""
        Name: %s
        Number of rooms: %d
        Estimated earnings for the month: %f
        """,
        this.name,
        this.getRoomCount(),
        this.getEarnings());
  }
}

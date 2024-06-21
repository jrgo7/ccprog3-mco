import java.util.ArrayList;

public class Hotel {
  private String name;
  private ArrayList<Room> rooms;
  private ArrayList<Reservation> reservations;
  private double basePrice;
  private int lastRoomNumber;

  public Hotel(String name) {
    /*
     * Validation here is offloaded to the caller because Hotel should be
     * independent of the contents of ReservationSystem... I think
     * - lowy
     * I suppose it's easier to do that than do error handling
     * - wafl
     */
    this.name = name;
    this.rooms = new ArrayList<Room>();
    this.reservations = new ArrayList<Reservation>();
    this.basePrice = 1299;
    this.lastRoomNumber = 0;
  }

  /**
   * {@return the name of the hotel}
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets a room from the list of rooms given an index.
   * 
   * @param index The index of the room
   * @return the {@link Room} instance at the index given
   */
  public Room getRoom(int index) {
    return rooms.get(index);
  }

  /**
   * {@return the number of rooms in the hotel}
   */
  public int getRoomCount() {
    return this.rooms.size();
  }

  /**
   * Gets a reservation from the list of reservations given an index.
   * 
   * @param index The index of the reservation
   * @return the {@link Reservation} instance at the index given
   */
  public Reservation getReservation(int index) {
    return this.reservations.get(index);
  }

  /**
   * {@return the estimated earnings calculated as the base price times the room
   * count}
   */
  public double getEarnings() {
    return this.basePrice * getRoomCount();
  }

  public int countReservations(int date) {
    int retval = 0;

    for (Reservation i : reservations)
      if (date >= i.getCheckIn() && date <= i.getCheckOut())
        retval++;

    return retval;
  }

  public void setName(String name) {
    /* Again, no validation here; see above comment */
    this.name = name;
  }

  public boolean setBasePrice(double basePrice) {
    if (basePrice < 0 || !rooms.isEmpty())
      return false;

    this.basePrice = basePrice;

    for (Room room : rooms)
      room.setBasePrice(basePrice);

    return true;
  }

  public void addRooms(int count) {
    for (int i = 0; i < count && rooms.size() < 50; i++) {
      this.rooms.add(
          new Room("RM" + String.format("%03d", this.lastRoomNumber++),
              basePrice));
    }
  }

  public void removeRoom(Room room) {
    this.rooms.remove(room);
  }

  public boolean addReservation(String guestName, int checkIn, int checkOut,
      Room room) {
    Reservation reservation = new Reservation(
        guestName, checkIn, checkOut, room);
    this.reservations.add(reservation);
    return room.addReservation(reservation);
  }

  public void removeReservation(int index) {
    this.reservations.remove(index);
  }

  public String[] getRoomNames() {
    int i, count = rooms.size();
    String[] retval = new String[count];

    for (i = 0; i < count; i++)
      retval[i] = rooms.get(i).getName();

    return retval;
  }

  public String[] getReservationNames() {
    int i, count = reservations.size();
    String[] retval = new String[count];
    Reservation reservation;

    for (i = 0; i < count; i++) {
      reservation = reservations.get(i);
      retval[i] = "Reservation for " + reservation.getRoom().getName() + " by "
          + reservation.getGuestName();
    }

    return retval;
  }

  public int getAvailableRoomCount(int date) {
    return this.getRoomCount() - this.countReservations(date);
  }

  public String toString() {
    return String.format("""
        Hotel information:
          Name: %s
          Rooms: %d
          Estimated earnings: %f
        """,
        this.getName(),
        this.getRoomCount(),
        this.getEarnings());
  }
}

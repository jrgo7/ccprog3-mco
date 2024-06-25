import java.util.ArrayList;

/** Represents a hotel that may be added to a {@link ReservationSystem}. */
public class Hotel {
  /** The name of the hotel. */
  private String name;

  /** The list of {@link Room}s tied to the hotel. */
  private ArrayList<Room> rooms;

  /** The list of {@link Reservation}s tied to the hotel. */
  private ArrayList<Reservation> reservations;

  /**
   * The base price for the hotel. New {@link Room}s created within the hotel
   * will have this set as their base price.
   */
  private double basePrice;

  /** The "highest room number" currently in the hotel. */
  private int lastRoomNumber;

  /**
   * Initializes a new hotel instance given a name. The created hotel begins
   * with zero reservations, a base price of 1299.00, and one room.
   * 
   * @param name The name of the hotel
   */
  public Hotel(String name) {
    this.name = name;

    /* Create empty lists for rooms and reservations */
    this.rooms = new ArrayList<Room>();
    this.reservations = new ArrayList<Reservation>();

    this.basePrice = 1299;
    this.lastRoomNumber = 0;

    /* Add a single room */
    this.addRooms(1);
  }

  /** {@return the name of the hotel} */
  public String getName() {
    return this.name;
  }

  /** {@return the base price of the hotel} */
  public double getBasePrice() {
    return this.basePrice;
  }

  /**
   * Returns the number of {@link Room}s tied to the hotel.
   * 
   * @return the number of rooms tied to the hotel.
   */
  public int getRoomCount() {
    return this.rooms.size();
  }

  /**
   * Returns the hotel's estimated earnings, calculated as the sum of the total
   * prices of each reservation.
   * 
   * @return the calculated estimated earnings
   * @see Reservation#getTotalPrice()
   */
  public double getEarnings() {
    double earnings = 0.0;
    for (Reservation r : this.reservations)
      earnings += r.getTotalPrice();

    return earnings;
  }

  /**
   * Returns the number of {@link Reservation}s tied to the hotel.
   * 
   * @return the number of reservations tied to the hotel.
   */
  public int getReservationCount() {
    return this.reservations.size();
  }

  /**
   * Returns the number of {@link Reservation}s tied to the hotel for a given
   * day.
   * 
   * @param day             The day to check for reservations
   * @param excludeCheckOut Flag to exclude reservations that check out on the
   *                        day provided. If {@code true}, then a reservation
   *                        whose check-out day is {@code day} will be counted.
   * @return the number of reservations tied to the hotel for a given day
   * @see #getAvailableRoomCount(int)
   */
  public int getReservationCountOnDate(int day, boolean excludeCheckOut) {
    int retval = 0;

    for (Reservation i : this.reservations)
      if (day >= i.getCheckIn() &&
          (excludeCheckOut
              /*
               * Use a different condition when including reservations that
               * check out on the given day
               */
              ? day < i.getCheckOut()
              : day <= i.getCheckOut()))
        retval++;

    return retval;
  }

  /**
   * Returns a list of days during which a {@link Room} at a given index is
   * available.
   * 
   * @param roomIndex The index of the room to inspect
   * @return a list of days on which a room is available
   * @see Room#getAvailableDates()
   */
  public ArrayList<Integer> getAvailableDatesForRoom(int roomIndex) {
    return this.rooms.get(roomIndex).getAvailableDates();
  }

  /**
   * Returns a calendar string indicating the days during which a {@link Room}
   * instance at a given index is available.
   * 
   * @param roomIndex The index of the room to inspect
   * @return a formatted calendar string showing the days on which a room is
   *         available
   */
  public String getCalendarStringForRoom(int roomIndex) {
    return this.rooms.get(roomIndex).getAvailableDatesAsCalendarString();
  }

  /**
   * Returns a string representation of a {@link Room} at a given index.
   * 
   * @param roomIndex The index of the room to inspect
   * @return a string representation of the data stored in a room
   * @see Room#toString()
   */
  public String getRoomString(int roomIndex) {
    return this.rooms.get(roomIndex).toString();
  }

  /**
   * Returns a string representation of a {@link Reservation} at a given index.
   * 
   * @param reservationIndex The index of a reservation to inspect
   * @return a string representation of the data stored in a reservation
   * @see Reservation#toString()
   */
  public String getReservationString(int reservationIndex) {
    return this.reservations.get(reservationIndex).toString();
  }

  /**
   * Sets the hotel name. Accessible from {@link ReservationSystem}.
   * 
   * @param name The name of the hotel
   * @see ReservationSystem#renameHotel(int, String)
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the base price for the hotel and all of its rooms. Fails if attempting
   * to set a base price of lower than 100.00 or if any {@link Reservation}
   * instances are tied to the hotel.
   * 
   * @param basePrice The new base price to set
   * @return {@code true} if the base price was set successfully, {@code false}
   *         otherwise
   * @see Room#setBasePrice(double)
   */
  public boolean setBasePrice(double basePrice) {
    if (basePrice < 100 || !this.reservations.isEmpty())
      return false;

    this.basePrice = basePrice;

    for (Room room : this.rooms)
      room.setBasePrice(basePrice);

    return true;
  }

  /**
   * Adds a given number of {@link Room}s to the hotel. Room names follow a
   * format starting with {@code RM001} for the first room. New rooms will
   * always increment the number by 1, so the next room would be {@code RM002}.
   * This occurs even if the room had previously been removed (i.e., even if
   * {@code RM002} were to be removed, the next room would still be
   * {@code RM003}).
   * 
   * @param count The number of rooms to add
   */
  public void addRooms(int count) {
    for (int i = 0; i < count && this.rooms.size() < 50; i++)
      this.rooms.add(
          new Room("RM" + String.format("%03d", 1 + this.lastRoomNumber++),
              this.basePrice));
  }

  /**
   * Removes a {@link Room} at a given index from the hotel. Fails if the room
   * has any reservations tied to it or if the index is out of range.
   * 
   * @param roomIndex The index of the room to remove
   * @return {@code true} if the room was removed successfully, {@code false}
   *         otherwise
   */
  public boolean removeRoom(int roomIndex) {
    if (this.rooms.get(roomIndex).getReservationCount() > 0
        || roomIndex < 0
        || roomIndex >= this.rooms.size())
      return false;

    this.rooms.remove(roomIndex);
    return true;
  }

  /**
   * Creates a new {@link Reservation} given booking information and the index
   * of the {@link Room} for which a reservation will be made. The reservation
   * will then be tied to both the hotel and the selected room. Fails if the
   * check-in day is outside the valid range ({@code 1} to {@code 30}), the
   * check-out day is before the check-in day, if there are availability
   * conflicts with the room, or if the room index is out of range.
   * <p>
   * Note that a reservation can be made with a check-out day that overlaps with
   * the check-in day of another reservation. This is because a room is marked
   * as available on a day even if there exist reservations that check out on
   * that day.
   * 
   * @param guestName The name of the guest
   * @param checkIn   The check-in day
   * @param checkOut  The check-out day
   * @param roomIndex The index of the room to book a reservation for
   * @return {@code true} if a reservation was made successfully, {@code false}
   *         otherwise
   * @see Room#isAvailableOn(int)
   */
  public boolean addReservation(String guestName, int checkIn, int checkOut,
      int roomIndex) {
    if (checkIn > 30 || checkIn < 1 || checkOut <= checkIn
        || roomIndex >= this.rooms.size() || roomIndex < 0)
      return false;

    Room room = this.rooms.get(roomIndex);
    /* No need to check validity on check-out day */
    for (int day = checkIn; day < checkOut; day++)
      if (!room.isAvailableOn(day))
        return false;

    Reservation reservation = new Reservation(
        guestName, checkIn, checkOut, room);
    this.reservations.add(reservation);

    /* Also add the reservation to the room */
    room.addReservation(reservation);
    return true;
  }

  /**
   * Removes a {@link Reservation} at a given index.
   * 
   * @param index The index of the reservation to remove
   */
  public boolean removeReservation(int index) {
    if (index < 0 || index >= this.reservations.size())
      return false;

    Reservation reservation = this.reservations.get(index);
    /* Also remove the reservation from the room it is tied to */
    reservation.getRoom().removeReservation(reservation);
    this.reservations.remove(index);

    return true;
  }

  /**
   * Returns a primitive string array containing the names of all {@link Room}
   * instances in the hotel in the same order they appear in the hotel's list.
   * 
   * @return An array containing the names of all rooms in the hotel
   */
  public String[] getRoomNames() {
    int i, count = this.rooms.size();
    String[] retval = new String[count];

    for (i = 0; i < count; i++)
      retval[i] = this.rooms.get(i).getName();

    return retval;
  }

  /**
   * Returns a primitive string array containing the names of all
   * {@link Reservation}s in the hotel in the same order they appear in the
   * hotel's list. This name is formatted as
   * {@code RMXXX: Guest}.
   * 
   * @return An array containing the names of all reservations in the hotel
   */
  public String[] getReservationNames() {
    int i, count = this.reservations.size();
    String[] retval = new String[count];
    Reservation reservation;

    for (i = 0; i < count; i++) {
      reservation = this.reservations.get(i);
      retval[i] = reservation.getRoom().getName() + ": "
          + reservation.getGuestName();
    }

    return retval;
  }

  /**
   * Returns the number of available rooms available on a given day. Calculated
   * by subtracting the number of reservations on a given day (excluding those
   * that check out on that day) from the total number of rooms.
   * 
   * @param day The day to inspect
   * @return the number of available rooms on the given day
   */
  public int getAvailableRoomCount(int day) {
    return this.getRoomCount()
        /* Exclude reservations that check out on the day */
        - this.getReservationCountOnDate(day, true);
  }

  /**
   * {@inheritDoc} Includes its name, room count, and estimated earnings.
   * 
   * @see #getEarnings()
   */
  @Override
  public String toString() {
    return String.format("""
        Hotel information:
          Name: %s
          Rooms: %d
          Estimated earnings: %.2f""",
        this.getName(),
        this.getRoomCount(),
        this.getEarnings());
  }
}
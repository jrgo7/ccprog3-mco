/** Represents a reservation tied to a {@link Hotel} and a {@link Room}. */
public class Reservation {
  /** The name of the guest tied to the reservation. */
  private String guestName;

  /** The check-in date of the reservation. */
  private int checkIn;

  /** The check-out date of the reservation. */
  private int checkOut;

  /** The {@link Room} tied to the reservation. */
  private Room room;

  /**
   * Initializes a new reservation instance given booking information.
   * 
   * @param guestName The name of the guest
   * @param checkIn   The check-in day
   * @param checkOut  The check-out day
   * @param room      The room instance to book a reservation for
   * @see Hotel#addReservation(String, int, int, int)
   */
  public Reservation(String guestName, int checkIn, int checkOut, Room room) {
    this.guestName = guestName;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
    this.room = room;
  }

  /** {@return the guest name tied to the reservation} */
  public String getGuestName() {
    return this.guestName;
  }

  /** {@return the check-in day of the reservation} */
  public int getCheckIn() {
    return this.checkIn;
  }

  /** {@return the check-out day of the reservation} */
  public int getCheckOut() {
    return this.checkOut;
  }

  /**
   * Returns the {@link Room} tied to the reservation.
   * 
   * @return the room tied to the reservation
   */
  public Room getRoom() {
    return this.room;
  }

  /**
   * {@return the number of nights the reservation is good for} Calculated as
   * {@code check-out day - check-in day}
   */
  public int getNightCount() {
    return this.checkOut - this.checkIn;
  }

  /**
   * {@return the total price for the reservation} Calculated as
   * {@code number of nights * room base price}
   * 
   * @see #getNightCount()
   */
  public double getTotalPrice() {
    return this.getNightCount() * room.getBasePrice();
  }

  /**
   * {@return a string listing the price breakdown of the reservation} This
   * includes the number of nights, the base price, and the total price of the
   * reservation.
   * 
   * @see #getNightCount()
   * @see #getTotalPrice()
   */
  public String getPriceBreakdown() {
    return String.format("%d nights x %.2f price per night = %.2f",
        this.getNightCount(),
        this.getRoom().getBasePrice(),
        this.getTotalPrice());
  }

  /**
   * {@inheritDoc} This includes the guest name, the room, check-in and
   * check-out days, total price, and a price breakdown.
   * 
   * @see Room#toString()
   * @see #getPriceBreakdown()
   */
  @Override
  public String toString() {
    return String.format("""
        Guest: %s
        %s
        Check-in: %d
        Check-out: %d
        Total price: %f
        Price breakdown: %s""",
        this.getGuestName(),
        this.getRoom().toString(),
        this.getCheckIn(),
        this.getCheckOut(),
        this.getTotalPrice(),
        this.getPriceBreakdown());
  }
}
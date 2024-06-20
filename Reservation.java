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

  public int getCheckIn() {
    return this.checkIn;
  }

  public int getCheckOut() {
    return this.checkOut;
  }

  public double getTotalPrice() {
    double basePrice = room.getBasePrice();
    return (checkIn - checkOut /* no + 1 */) * basePrice;
  }

  public String getGuestName() {
    return guestName;
  }

  public Room getRoom() {
    return this.room;
  }
}

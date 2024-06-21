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

  public String getGuestName() {
    return guestName;
  }

  public int getCheckIn() {
    return this.checkIn;
  }

  public int getCheckOut() {
    return this.checkOut;
  }

  public Room getRoom() {
    return this.room;
  }

  public int getNightCount() {
    return this.checkOut - this.checkIn;
  }

  public double getTotalPrice() {
    return this.getNightCount() * room.getBasePrice();
  }

  // TODO
  public String getPriceBreakdown() {
    return String.format("%d nights x %lf price per night = %lf",
            this.getNightCount(),
            this.getRoom().getBasePrice(),
            this.getTotalPrice());
}

  public String toString() {
    return String.format("""
            Guest: %s
            Room Information:\n%s
            Check-in: %d
            Check-out: %d
            Total price: %lf
            Price breakdown: %s
            """,
            this.getGuestName(),
            this.getRoom().toString(),
            this.getCheckIn(),
            this.getCheckOut(),
            this.getTotalPrice(),
            this.getPriceBreakdown());
  }
}

import java.util.ArrayList;

public class Room {
  private String name;
  private double basePrice;
  private ArrayList<Reservation> reservations;

  public Room(String name, double basePrice) {
    this.name = name;
    this.basePrice = basePrice;
    this.reservations = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public double getBasePrice() {
    return basePrice;
  }

  public void setBasePrice(double basePrice) {
    this.basePrice = basePrice;
  }

  /**
   * Add a reservation to this room.
   * 
   * @param reservation
   * @return true if room was added successfully, false otherwise (due to
   *         availability conflicts)
   */
  public boolean addReservation(Reservation reservation) {
    int checkIn = reservation.getCheckIn();
    int checkOut = reservation.getCheckOut();
    for (int day = checkIn; day <= checkOut; day++) {
      if (!this.isAvailableOn(day)) {
        return false;
      }
    }
    reservations.add(reservation);
    return true;
  }

  public ArrayList<Integer> getAvailableDates() {
    ArrayList<Integer> retval = new ArrayList<>();

    for (int i = 1; i <= 31; i++)
      if (this.isAvailableOn(i))
        retval.add(i);

    return retval;
  }

  public boolean isAvailableOn(int date) {
    for (Reservation i : reservations)
      if (i.getCheckIn() <= date && i.getCheckOut() > date)
        return false;
    return true;
  }

  /**
   * Prints a calendar consisting of 31 days. Date numbers will only be shown if
   * included in the list of available dates
   */
  public String getAvailableDatesAsCalendarString() {
    int day;
    String result = "Available dates:\n";
    for (day = 1; day <= 31; day++) {
      if (day % 7 == 1)
        result += "\n";
      if (isAvailableOn(day))
        result += "[X]  ";
      else
        result += String.format("%-5d", day);
    }

    result += "\n";
    return result;
  }

  public String toString() {
    return String.format("""
        Room information:
            Name: %s
            Price/night: %f
        %s
        """,
        this.getName(),
        this.getBasePrice(),
        this.getAvailableDatesAsCalendarString());
  }
}

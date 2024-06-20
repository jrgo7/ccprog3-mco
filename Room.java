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

  public void addReservation(Reservation reservation) {
    reservations.add(reservation);
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
}

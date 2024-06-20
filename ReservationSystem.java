import java.util.ArrayList;

/**
 * Represents the reservation system managing a list of hotels.
 * 
 * TODO: We can separate the CLI stuff into its own class in charge of
 * printing/input
 */
public class ReservationSystem {
  private ArrayList<Hotel> hotels;

  /**
   * Initializes a reservation system with an {@link ArrayList} containing zero
   * instances of {@link Hotel}. The reservation system starts with no hotels
   * and may have zero or multiple at any given time.
   */
  public ReservationSystem() {
    hotels = new ArrayList<Hotel>();
  }

  public void addHotel(Hotel hotel) {
    this.hotels.add(hotel);
  }

  public Hotel getHotel(int index) {
    return this.hotels.get(index);
  }

  /* TODO: I don't think this is needed, see method above */
  public Hotel getHotel(String name) {
    for (Hotel hotel : this.hotels)
      if (hotel.getName().equals(name))
        return hotel;
    return null;
  }

  public Boolean removeHotel(Hotel hotel) {
    return this.hotels.remove(hotel);
  }

  /* Used when validating name input */
  public boolean hotelNameExists(String name) {
    String[] hotelNames = getHotelNames();

    for (String i : hotelNames)
      if (i.equals(name))
        return true;
    return false;
  }

  public String[] getHotelNames() {
    int i, count = hotels.size();
    String[] retval = new String[count];

    for (i = 0; i < count; i++)
      retval[i] = hotels.get(i).getName();

    return retval;
  }
}
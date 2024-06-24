import java.util.ArrayList;

/**
 * Represents the reservation system managing a list of hotels.
 * 
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

  public Hotel getHotel(int index) {
    return this.hotels.get(index);
  }

  public boolean addHotel(String name) {
    if (hotelNameExists(name))
      return false;

    this.hotels.add(new Hotel(name));
    return true;
  }

  public boolean renameHotel(Hotel hotel, String name) {
    if (hotelNameExists(name))
      return false;

    hotel.setName(name);
    return true;
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
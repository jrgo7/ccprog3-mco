import java.util.ArrayList;
import java.util.Scanner;

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

  /**
   * Prints information about a {@link Hotel} instance, displaying its name,
   * room count, and estimated earnings.
   * 
   * TODO: Should this be in the Hotel class instead?
   * 
   * @param hotel The hotel to display information for
   */
  public void viewHotel(Hotel hotel) {
    System.out.println("Hotel information:");
    System.out.println("Name: " + hotel.getName());
    System.out.println("Rooms: " + hotel.getRoomCount());
    System.out.println("Estimated earnings: " + hotel.getEarnings());

    /*
     * TODO: Include the following options. The methods should probably be
     * relegated to the Hotel class:
     * 
     * - Show total number of available and booked rooms for a selected date
     * 
     * - Information about a selected room (name, price per night, and
     * availability)
     * 
     * - Information about a selected reservation (guest name, room information,
     * check-in and check-out, total price, price per night)
     */
  }

  public void addHotel(Hotel hotel) {
    this.hotels.add(hotel);
  }

  public Hotel getHotel(int index) {
    return this.hotels.get(index);
  }

  public Hotel getHotel(String name) {
    for (Hotel hotel : this.hotels)
      if (hotel.getName().equals(name))
        return hotel;
    return null;
  }

  public Boolean removeHotel(Hotel hotel) {
    return this.hotels.remove(hotel);
  }

  public String[] getHotelNames() {
    int i, count = hotels.size();
    String[] retval = new String[count];
    
    for (i = 0; i < count; i++)
      retval[i] = hotels.get(i).getName();
    
    return retval;
  }


}
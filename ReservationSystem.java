import java.util.ArrayList;

/**
 * Represents the reservation system managing a list of hotels.
 */
public class ReservationSystem {
  /** The list of all hotels in the system. */
  private ArrayList<Hotel> hotels;

  /**
   * Initializes a reservation system containing zero instances of
   * {@link Hotel}. The reservation system starts with no hotels and may have
   * zero or multiple at any given time.
   */
  public ReservationSystem() {
    hotels = new ArrayList<Hotel>();
  }

  /**
   * Checks if the system contains at least one hotel.
   * 
   * @return {@code true} if at least one hotel exists in the system,
   *         {@code false} otherwise
   */
  public boolean hasHotels() {
    return hotels.size() > 0;
  }

  /**
   * Returns the {@link Hotel} instance at a given index.
   * 
   * @param index The index of the hotel to be retrieved from the system
   * @return the hotel instance at the given index, or {@code null} if the index
   *         is out of range
   */
  public Hotel getHotel(int index) {
    if (index < 0 || index >= hotels.size())
      return null;
    return hotels.get(index);
  }

  /**
   * Removes a {@link Hotel} instance at a given index from the list of hotels
   * in the system.
   * 
   * @param index The index of the hotel to be removed from the system
   */
  public boolean removeHotel(int index) {
    if (index < 0 || index >= hotels.size()) {
      return false;
    }
    hotels.remove(index);
    return true;
  }

  /**
   * Checks if a given name is already assigned to a {@link Hotel} in the
   * system. Used when creating a new hotel with {@link #addHotel(String)} or
   * when renaming an existing hotel with {@link #renameHotel(Hotel, String)}
   * 
   * @param name The name to validate
   * @return {@code true} if a hotel with the given name exists in the system,
   *         {@code false} otherwise
   */
  private boolean hotelNameExists(String name) {
    for (Hotel i : hotels)
      if (i.getName().equals(name))
        return true;

    return false;
  }

  /**
   * Creates a new {@link Hotel} instance based on a given name and adds it to
   * the list of hotels in the system. Fails if a hotel with the same name
   * already exists in the system.
   * 
   * @param name The name of the hotel to create
   * @return {@code true} if the hotel was created successfully, {@code false}
   *         otherwise
   */
  public boolean addHotel(String name) {
    if (hotelNameExists(name))
      return false;

    hotels.add(new Hotel(name));
    return true;
  }

  /**
   * Renames a given {@link Hotel} instance at a given index within the list of
   * hotels in the system. Fails if a hotel with the same name as the inputted
   * name already exists in the system.
   * 
   * @param index The index of the hotel to be renamed
   * @param name  The new name of the hotel
   * @return {@code true} if the hotel was renamed successfully, {@code false}
   *         otherwise
   */
  public boolean renameHotel(int index, String name) {
    if (hotelNameExists(name))
      return false;

    getHotel(index).setName(name);
    return true;
  }

  /**
   * Returns a primitive string array containing the names of all {@link Hotel}
   * instances in the system in the same order they appear in the system list
   * (i.e., the name of the hotel at index 3 in the system would be at index 3
   * in the returned array).
   * 
   * @return An array containing the names of all hotels in the system
   * @see Hotel#getName()
   */
  public String[] getHotelNames() {
    int count = hotels.size();
    /* Returned array must be of the same length as the list of hotels */
    String[] names = new String[count];

    for (int i = 0; i < count; i++)
      names[i] = hotels.get(i).getName();

    return names;
  }
}
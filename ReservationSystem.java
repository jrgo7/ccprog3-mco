import java.util.ArrayList;

/** Represents the reservation system managing a list of hotels. */
public class ReservationSystem {
  /** The list of all hotels in the system. */
  private ArrayList<Hotel> hotels;

  /**
   * Initializes a reservation system containing zero instances of
   * {@link Hotel}. The reservation system starts with no hotels and may have
   * zero or multiple at any given time.
   */
  public ReservationSystem() {
    this.hotels = new ArrayList<Hotel>();
  }

  /**
   * Checks if the system contains at least one hotel.
   * 
   * @return {@code true} if at least one hotel exists in the system,
   *         {@code false} otherwise
   */
  public boolean hasHotels() {
    return this.hotels.size() > 0;
  }

  /**
   * Returns the {@link Hotel} at a given index.
   * 
   * @param index The index of the hotel to be retrieved from the system
   * @return the hotel instance at the given index, or {@code null} if the index
   *         is out of range
   */
  public Hotel getHotel(int index) {
    if (index < 0 || index >= this.hotels.size())
      return null;
    return this.hotels.get(index);
  }

  /**
   * Removes a {@link Hotel} at a given index from the list of hotels in the
   * system.
   * 
   * @param index The index of the hotel to be removed from the system
   * @return {@code true} if a hotel was removed successfully, {@code false} if
   *         the index given is out of range
   */
  public boolean removeHotel(int index) {
    if (index < 0 || index >= this.hotels.size()) {
      return false;
    }
    this.hotels.remove(index);
    return true;
  }

  /**
   * Checks if a given name is already assigned to a {@link Hotel} in the
   * system. Used when creating a new hotel with {@link #addHotel(Hotel)} or
   * when renaming an existing hotel with {@link #renameHotel(int, String)}
   * 
   * @param name The name to validate
   * @return {@code true} if a hotel with the given name exists in the system,
   *         {@code false} otherwise
   */
  private boolean hotelNameExists(String name) {
    for (Hotel i : this.hotels)
      if (i.getName().equals(name))
        return true;

    return false;
  }

  /**
   * Adds a given {@link Hotel} to the list of hotels in the system. Fails if a
   * hotel with the same name already exists in the system.
   * 
   * @param hotel The hotel instance to add to the system
   * @return {@code true} if the hotel was created successfully, {@code false}
   *         otherwise
   */
  public boolean addHotel(Hotel hotel) {
    if (hotelNameExists(hotel.getName()))
      return false;

    this.hotels.add(hotel);
    return true;
  }

  /**
   * Renames a given {@link Hotel} at a given index within the list of hotels in
   * the system. Fails if a hotel with the same name as the inputted name
   * already exists in the system.
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
   * instances in the system in the same order they appear in the system's list.
   * 
   * @return An array containing the names of all hotels in the system
   * @see Hotel#getName()
   */
  public String[] getHotelNames() {
    int count = this.hotels.size();
    /* Returned array must be of the same length as the list of hotels */
    String[] names = new String[count];

    for (int i = 0; i < count; i++)
      names[i] = this.hotels.get(i).getName();

    return names;
  }
}
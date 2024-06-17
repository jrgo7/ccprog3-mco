import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the reservation system managing a list of hotels.
 * 
 * TODO: We can separate the CLI stuff into its own class in charge of
 * printing/input
 */
public class ReservationSystem {
  private Scanner inputScanner;
  private ArrayList<Hotel> hotels;

  /**
   * Initializes a reservation system with an {@link ArrayList} containing zero
   * instances of {@link Hotel}. The reservation system starts with no hotels
   * and may have zero or multiple at any given time.
   */
  public ReservationSystem() {
    hotels = new ArrayList<Hotel>();
    inputScanner = new Scanner(System.in);
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

  /**
   * Prints a prompt of choices from which the user must select a number.
   * 
   * TODO: Refactor this method, make it private, or delete completely
   * 
   * @param choices The strings to print for each choice
   * @return the number selected by the user
   */
  public int getChoice(String... choices) {
    int i = 1, retval;

    for (String choice : choices)
      System.out.println("[" + i++ + "] " + choice);

    do {
      System.out.print(" >> ");
      /*
       * TODO: Catch the NumberFormatException thrown by parseInt() or just turn
       * this into a nextInt() call followed by a nextLine() to consume the
       * trailing newline
       */
      retval = Integer.parseInt(inputScanner.nextLine());
      if (retval < 1 || retval >= i)
        System.out.println("Invalid input. Please try again:");
    } while (retval < 1 || retval >= i);

    return retval;
  }

  /**
   * Prompts the user to create a new {@link Hotel} instance. The user inputs a
   * string which is then used as the hotel's name.
   */
  public void displayCreateHotelScreen() {
    System.out.println("Please input a name for the hotel:");
    System.out.print(" >> ");
    hotels.add(new Hotel(inputScanner.nextLine()));
  }

  /**
   * Allows the user to select a hotel from a list of all hotels in the system,
   * then displays the info of the selected hotel.
   * 
   * @see {@link #viewHotel}()
   */
  public void displayViewHotelScreen() {
    System.out.println("Please choose a hotel:");

    /*
     * TODO: I'm sorry this is a mess just rewrite lol, probably as a for loop
     * so we won't have to explain what the heck streams are
     */
    ArrayList<String> hotelNames = new ArrayList<String>(
        hotels.stream()
            .map(Hotel::getName)
            .toList());

    int choice = getChoice(hotelNames.toArray(String[]::new));
    viewHotel(hotels.get(choice - 1));
  }

  /**
   * Displays the main menu from which the user can use the system's features.
   * 
   * @return {@code true} if the user selects an option, {@code false} if the
   *         user exits
   */
  public boolean displayTitleScreen() {
    /* TODO: ANSI screen clearing stuff */
    // System.out.print("\033[H\033[2J");

    int choice = getChoice("Create Hotel", "View Hotel", "Manage Hotel",
        "Simulate Booking");

    switch (choice) {
    case 1:
      displayCreateHotelScreen();
      return true;
    case 2:
      displayViewHotelScreen();
      return true;
    default:
      return false;
    }
  }

  public void closeInputScanner() {
    inputScanner.close();
  }

  public static void main(String[] args) {
    ReservationSystem mainSystem = new ReservationSystem();

    while (mainSystem.displayTitleScreen())
      ;

    mainSystem.closeInputScanner();
  }
}
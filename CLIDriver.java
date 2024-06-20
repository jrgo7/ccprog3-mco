import java.util.ArrayList;
import java.util.Scanner;

public class CLIDriver {
  private ReservationSystem reservationSystem;
  private Scanner sc;

  CLIDriver() {
    reservationSystem = new ReservationSystem();
    sc = new Scanner(System.in);
  }

  /**
   * Prompts the user to select a hotel from the list of hotels in the system.
   * 
   * @return the {@link Hotel} instance selected by the user
   */
  private Hotel promptChooseHotel() {
    String[] hotelNames = reservationSystem.getHotelNames();

    if (hotelNames.length != 0)
      return reservationSystem.getHotel(
          CLIUtility.promptChoiceInput(sc, "Select a hotel:", hotelNames));

    System.out.println("There are currently no hotels in the system.");
    return null;
  }

  /**
   * Prompts the user to select a room within a given hotel.
   * 
   * @param hotel The {@link Hotel} instance to select a room from
   * @return the {@link Room} instance selected by the user
   */
  private Room promptChooseRoomFrom(Hotel hotel) {
    String[] roomNames = hotel.getRoomNames();

    if (roomNames.length != 0)
      return hotel.getRoom(
          CLIUtility.promptChoiceInput(sc, "Select a room:", roomNames));

    System.out.println("There are currently no rooms in the hotel.");
    return null;
  }

  /**
   * Prompts the user to create a new {@link Hotel} instance. The user inputs a
   * string which is then used as the hotel's name. The new hotel is added to
   * the reservation system's hotel list.
   */
  public void displayCreateHotelScreen() {
    String nameInput;
    boolean nameExists;

    CLIUtility.printBorder();

    do {
      nameInput = CLIUtility.promptStringInput(sc,
          "Input a name for the hotel:");

      /* Search through existing hotels if name is taken */
      nameExists = reservationSystem.hotelNameExists(nameInput);

      if (nameExists)
        System.out.println("Name already exists. Please try again:");
    } while (nameExists);

    reservationSystem.addHotel(new Hotel(nameInput));
  }

  /**
   * Allows the user to select a hotel from a list of all hotels in the system,
   * then displays the info of the selected hotel.
   * 
   * @see {@link #viewHotel}()
   */
  public void displayViewHotelScreen() {
    CLIUtility.printBorder();

    Hotel hotel = promptChooseHotel();

    System.out.println("Hotel information:");
    System.out.println("    Name: " + hotel.getName());
    System.out.println("    Rooms: " + hotel.getRoomCount());
    System.out.println("    Estimated earnings: " + hotel.getEarnings());

    int input = CLIUtility.promptChoiceInput(sc, "Select an option",
        "Check room availability",
        "Check room data",
        "Check reservation data",
        "Exit");

    switch (input) {
    /* Total number of available and booked rooms for a selected date */
    case 0:
      /* TODO: Clean up or extract to a private method */
      CLIUtility.printBorder();
      int date = CLIUtility.promptIntegerInput(sc, "Enter a date:", 1, 31);
      int booked = hotel.countReservationsOnDay(date);
      System.out.println("Reservations on day " + date + ": "
          + booked);
      System.out.println(
          "There are " + (hotel.getRoomCount() - booked) + " rooms available.");
      break;
    /* Show room data */
    case 1:
      /* Wafl moment */
      break;
    /* Show reservation data */
    case 2:
      /* Wafl moment */
      break;
    }
  }

  public void displaySimulateBookingScreen() {
    CLIUtility.printBorder();
    Hotel hotel = promptChooseHotel();

    CLIUtility.printBorder();
    Room room = promptChooseRoomFrom(hotel);

    CLIUtility.printBorder();
    String guestName = CLIUtility.promptStringInput(sc, "Enter your name:");

    CLIUtility.printBorder();
    int in = CLIUtility.promptIntegerInput(sc, "Enter a check-in date:", 1, 30);

    CLIUtility.printBorder();
    int out = CLIUtility.promptIntegerInput(sc, "Enter a check-out date:",
        in + 1, 31);

    hotel.addReservation(guestName, in, out, room);
  }

  public void displayManageHotelScreen() {
    CLIUtility.printBorder();
    Hotel hotel = promptChooseHotel();

    CLIUtility.printBorder();
    System.out.println("Currently managing the following hotel:");
    System.out.println(hotel.getName());

    int choice = CLIUtility.promptChoiceInput(sc, "Select an option:",
        "Rename hotel", "Add room(s)", "Remove room(s)",
        "Update base price", "Remove reservation", "Remove hotel");
    CLIUtility.printBorder();

    switch (choice) {
    case 0:
      hotel.setName(CLIUtility.promptStringInput(sc,
          "Please input the new name of the hotel:"));
      break;
    case 1:
      /* TODO: Display a message if rooms have hit limit */
      hotel.addRooms(CLIUtility.promptIntegerInput(sc,
          "Input the number of rooms to add:", 1, 50));
      break;
    case 2:
      /* TODO: Remove multiple rooms at once by adding a new method to Hotel */
      hotel.removeRoom(promptChooseRoomFrom(hotel));
      break;
    case 3:
      if (hotel.getRoomCount() != 0)
        System.out.println(
            "There should be zero rooms in the hotel. Please try again.");
      else
        /* TODO: Catch the NumberFormatException thrown by parseDouble() */
        hotel.setBasePrice(Double
            .parseDouble(CLIUtility.promptStringInput(sc,
                "Please input the new base price:")));
      break;
    case 4:
      /* TODO: Remove reservation */
      break;
    case 5:
      reservationSystem.removeHotel(hotel);
      System.out
          .println("Removed hotel " + hotel.getName() + " from the list.");
      break;
    }
  }

  /**
   * Displays the main menu from which the user can use the system's features.
   * 
   * @return {@code true} if the user selects an option, {@code false} if the
   *         user exits
   */
  public boolean displayTitleScreen() {
    CLIUtility.printBorder();
    int choice = CLIUtility.promptChoiceInput(sc, "Select an option:",
        "Create Hotel", "View Hotel", "Manage Hotel", "Simulate Booking",
        "Exit");

    switch (choice) {
    case 0:
      displayCreateHotelScreen();
      return true;
    case 1:
      displayViewHotelScreen();
      return true;
    case 2:
      displayManageHotelScreen();
      return true;
    case 3:
      displaySimulateBookingScreen();
      return true;
    default:
      return false;
    }
  }

  public void closesc() {
    sc.close();
  }

  public static void main(String[] args) {
    CLIDriver cli = new CLIDriver();

    while (cli.displayTitleScreen())
      ;

    cli.closesc();
  }
}

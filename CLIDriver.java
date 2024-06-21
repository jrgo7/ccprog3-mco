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
          CLIUtility.promptChoice(sc, "Select a hotel:", hotelNames));

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
          CLIUtility.promptChoice(sc, "Select a room:", roomNames));

    System.out.println("There are currently no rooms in the hotel.");
    return null;
  }

  /**
   * Prompts the user to select a reservation within a given hotel.
   * 
   * @param hotel The {@link Hotel} instance to select a room from
   * @return the {@link Reservation} instance selected by the user
   */
  private Reservation promptChooseReservationFrom(Hotel hotel) {
    String[] reservationNames = hotel.getReservationNames();

    if (reservationNames.length != 0)
      return hotel.getReservation(
          CLIUtility.promptChoice(sc, "Select a reservation:",
              reservationNames));

    System.out.println("There are currently no reservations in the hotel.");
    return null;
  }

  /**
   * Prompts the user to input a hotel name. Ensures that the inputted name does
   * not yet exist in the system.
   * 
   * @return the valid name inputted by the user
   */
  private String promptHotelName() {
    String nameInput;
    boolean nameExists;

    do {
      nameInput = CLIUtility.promptString(sc,
          "Input a name for the hotel:");

      /* Search through existing hotels if name is taken */
      nameExists = reservationSystem.hotelNameExists(nameInput);

      if (nameExists)
        System.out
            .println("Name already exists in the system. Please try again:");
    } while (nameExists);

    return nameInput;
  }

  /**
   * Prompts the user to create a new {@link Hotel} instance. The user inputs a
   * string which is then used as the hotel's name. The new hotel is added to
   * the reservation system's hotel list.
   */
  public void displayCreateHotelScreen() {
    CLIUtility.printBorder();
    reservationSystem.addHotel(new Hotel(promptHotelName()));
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

    CLIUtility.printBorder();
    System.out.println(hotel.toString());

    CLIUtility.printBorder();
    int input = CLIUtility.promptChoice(sc, "Select an option:",
        "Check room availability",
        "Check room data",
        "Check reservation data",
        "Exit");

    CLIUtility.printBorder();
    switch (input) {
      /* Total number of available and booked rooms for a selected date */
      case 0:
        int date = CLIUtility.promptInt(sc, "Enter a date:", 1, 31);
        System.out.printf("""
            Reservations on day %d: %d
            There are %d rooms available.
            """,
            date,
            hotel.countReservations(date),
            hotel.getAvailableRoomCount(date));
        break;
      /* Show room data */
      case 1:
        Room room = promptChooseRoomFrom(hotel);
        CLIUtility.printBorder();
        System.out.println(room.toString());
        break;
      /* Show reservation data */
      case 2:
        /* TODO: Yoink from wafl */
        promptChooseReservationFrom(hotel);
        break;
    }
  }

  /**
   * Allows the user to book a reservation for a room in a hotel.
   */
  public void displaySimulateBookingScreen() {
    CLIUtility.printBorder();
    Hotel hotel = promptChooseHotel();

    if (hotel == null) {
      System.out.println("There are no hotels in the system!");
    }

    CLIUtility.printBorder();
    Room room = promptChooseRoomFrom(hotel);

    if (room == null || room.getAvailableDates().size() == 0) {
      System.out.println("There are no (available) rooms in this hotel!");
    }

    CLIUtility.printBorder();
    String guestName = CLIUtility.promptString(sc, "Enter your name:");

    CLIUtility.printBorder();
    System.out.println(room.getAvailableDatesAsCalendarString());

    CLIUtility.printBorder();
    int in = CLIUtility.promptInt(sc, "Enter a check-in date:", 1, 30);

    CLIUtility.printBorder();
    int out = CLIUtility.promptInt(sc, "Enter a check-out date:",
        in + 1, 31);

    // TODO: Extensive testing
    if (hotel.addReservation(guestName, in, out, room)) {
      System.out.println("Reservation success!");
    } else {
      System.out.println("Reservation not successful: room is unavailable.");
    }
    ;
  }

  /**
   * Allows the user to modify a selected hotel.
   */
  public void displayManageHotelScreen() {
    CLIUtility.printBorder();
    Hotel hotel = promptChooseHotel();

    CLIUtility.printBorder();
    System.out.println("Currently managing the following hotel:");
    System.out.println(hotel.getName());

    CLIUtility.printBorder();
    int choice = CLIUtility.promptChoice(sc, "Select an option:",
        "Rename hotel", "Add room(s)", "Remove room(s)",
        "Update base price", "Remove reservation", "Remove hotel");

    CLIUtility.printBorder();
    switch (choice) {
      case 0:
        hotel.setName(promptHotelName());
        break;
      case 1:
        if (hotel.getRoomCount() >= 50) {
          System.out.println("You cannot add anymore rooms.");
        } else {
          int count = CLIUtility.promptInt(sc,
              "Input the number of rooms to add:",
              1, 50 - hotel.getRoomCount());
          hotel.addRooms(count);
        }
        break;
      case 2:
        do {
          hotel.removeRoom(promptChooseRoomFrom(hotel));
          choice = CLIUtility.promptChoice(sc, "Delete another room", "Done");
        } while (choice != 1);
        break;
      case 3:
        /* TODO: Clean up or extract to a private method */
        double newBasePrice = Double.parseDouble(CLIUtility.promptString(sc,
            "Please input the new base price:"));
        if (!hotel.setBasePrice(newBasePrice))
          System.out.println(
              "Invalid input. Please try again with or ensure there are no rooms in the hotel.");
        break;
      case 4:
        choice = CLIUtility.promptChoice(sc, "Remove which reservation?", hotel.getReservationNames());
        hotel.removeReservation(choice);
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
  public boolean doMenu() {
    CLIUtility.printBorder();
    int choice = CLIUtility.promptChoice(sc, "Select an option:",
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

    while (cli.doMenu())
      ;

    cli.closesc();
  }
}

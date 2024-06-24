import java.util.Scanner;

public class CLIDriver {
  private ReservationSystem reservationSystem;
  private Scanner sc;

  CLIDriver() {
    reservationSystem = new ReservationSystem();
    sc = new Scanner(System.in);
  }

  private int promptChooseHotel() {
    String[] hotelNames = reservationSystem.getHotelNames();

    if (hotelNames.length != 0)
      return 
          CLIUtility.promptChoice(sc, "Select a hotel:", hotelNames);

    System.out.println("There are currently no hotels in the system.");
    return -1;
  }

  /**
   * Prompts the user to select a room within a given hotel.
   * 
   * @param hotel The {@link Hotel} instance to select a room from
   * @return the {@link Room} instance selected by the user or {@code null} if
   *         none exist
   */
  private Room promptChooseRoomFrom(Hotel hotel) {
    String[] roomNames = hotel.getRoomNames();
    return hotel.getRoom(
        CLIUtility.promptChoice(sc, "Select a room:", roomNames));
  }

  /**
   * Prompts the user to select a reservation within a given hotel.
   * 
   * @param hotel The {@link Hotel} instance to select a room from
   * @return the {@link Reservation} instance selected by the user or
   *         {@code null} if none exist
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
   * Prompts the user to create a new {@link Hotel} instance. The user inputs a
   * string which is then used as the hotel's name. The new hotel is added to
   * the reservation system's hotel list.
   */
  public void displayCreateHotelScreen() {
    String name, message = "Input a name for the hotel:";
    boolean valid;

    CLIUtility.printBorder();
    do {
      name = CLIUtility.promptString(sc, message);
      valid = reservationSystem.addHotel(name);
      if (!valid)
        message = "Name already exists in the system. Please try again:";
    } while (!valid);
  }

  /**
   * Allows the user to select a hotel from a list of all hotels in the system,
   * then displays the info of the selected hotel.
   */
  public void displayViewHotelScreen() {
    CLIUtility.printBorder();
    int hotelIndex = promptChooseHotel();
    Hotel hotel = reservationSystem.getHotel(hotelIndex);

    /* Safeguard against cases where there are no hotels in the system */
    if (hotel == null)
      return;

    CLIUtility.printBorder();
    System.out.println(hotel.toString());

    CLIUtility.printBorder();
    int input = CLIUtility.promptChoice(sc, "Select an option:",
        "Check availability",
        "Check a room",
        "Check a reservation",
        "Exit menu");

    CLIUtility.printBorder();
    switch (input) {
    /* Number of available and booked rooms on a date */
    case 0:
      int date = CLIUtility.promptInt(sc, "Enter a date (1-31):", 1, 31);
      boolean isOneRoom = hotel.getAvailableRoomCount(date) == 1;
      System.out.printf("""
          Reservations on day %d: %d
          There %s %d room%s available.
          """,
          date,
          hotel.getReservationCountOnDate(date),
          isOneRoom ? "is" : "are",
          hotel.getAvailableRoomCount(date),
          isOneRoom ? "" : "s");
      break;
    /* Room data */
    case 1:
      Room room = promptChooseRoomFrom(hotel);
      CLIUtility.printBorder();
      System.out.println(room.toString());
      break;
    /* Reservation data */
    case 2:
      Reservation reservation = promptChooseReservationFrom(hotel);
      /* Proceed only if a reservation exists */
      if (reservation != null) {
        CLIUtility.printBorder();
        System.out.println(reservation.toString());
      }
      break;
    }
  }

  /**
   * Allows the user to modify a selected hotel.
   */
  public void displayManageHotelScreen() {
    CLIUtility.printBorder();
    int hotelIndex = promptChooseHotel();
    Hotel hotel = reservationSystem.getHotel(hotelIndex);

    /* Safeguard against cases where there are no hotels in the system */
    if (hotel == null)
      return;

    CLIUtility.printBorder();
    System.out.println("Currently managing the following hotel:");
    System.out.println(hotel.getName());

    CLIUtility.printBorder();
    int choice = CLIUtility.promptChoice(sc, "Select an option:",
        "Rename hotel", "Add room(s)", "Remove room(s)",
        "Update base price", "Remove reservation", "Remove hotel");

    CLIUtility.printBorder();
    switch (choice) {
    /* Rename hotel */
    case 0:
      String name;
      boolean valid;
      do {
        name = CLIUtility.promptString(sc,
            "Input a name for the hotel:");
        valid = reservationSystem.renameHotel(hotelIndex, name);
      } while (!valid);
      break;
    /* Add room(s) */
    case 1:
      int roomCount = hotel.getRoomCount();
      if (roomCount >= 50)
        System.out.println("You cannot add anymore rooms.");
      else {
        int addableRooms = 50 - roomCount;
        int count = CLIUtility.promptInt(sc,
            String.format("Input the number of rooms to add (1-%d):",
                addableRooms),
            1, addableRooms);
        hotel.addRooms(count);
      }
      break;
    /* Remove room(s) */
    case 2:
      if (hotel.getRoomCount() <= 1) {
        System.out.println("You cannot remove the only room in the hotel!");
        break;
      }
      int continueChoice = 0;
      while (continueChoice != 1 && hotel.getRoomCount() > 1) {
        Room room = promptChooseRoomFrom(hotel);
        CLIUtility.printBorder();
        if (hotel.removeRoom(room))
          System.out
              .println("Successfully removed room " + room.getName() + ".");
        else
          System.out.println(
              "Cannot remove this room because it still has at least one reservation.");
        if (hotel.getRoomCount() > 1) {
          continueChoice = CLIUtility.promptChoice(sc, "Remove another room?",
              "Yes",
              "No");
          if (continueChoice == 0)
            CLIUtility.printBorder();
        }
      }
      break;
    /* Update base price */
    case 3:
      if (hotel.getReservationCount() > 0) {
        System.out.println(
            "Cannot change the base price while there are reservations present.");
        break;
      }
      double newBasePrice = Double.parseDouble(
          CLIUtility.promptString(sc, "Please input the new base price:"));
      CLIUtility.printBorder();
      if (hotel.setBasePrice(newBasePrice))
        System.out.println("Set the base price to " + newBasePrice + ".");
      else
        System.out.println("You cannot set a base price lower than 100.00!");
      break;
    /* Remove reservation */
    case 4:
      if (hotel.getReservationCount() <= 0) {
        System.out.println(
            "There are no reservations to remove.");
        break;
      }
      choice = CLIUtility.promptChoice(sc, "Remove which reservation?",
          hotel.getReservationNames());
      hotel.removeReservation(choice);
      break;
    /* Remove hotel */
    case 5:
      /* TODO: I changed removeHotel to accept an index within the reservation system */
      reservationSystem.removeHotel(hotelIndex);
      System.out
          .println("Removed hotel " + hotel.getName() + " from the list.");
      break;
    }
  }

  /**
   * Allows the user to book a reservation for a room in a hotel.
   */
  public void displaySimulateBookingScreen() {
    CLIUtility.printBorder();
    int hotelIndex = promptChooseHotel();
    Hotel hotel = reservationSystem.getHotel(hotelIndex);

    if (hotel == null) {
      return;
    }

    CLIUtility.printBorder();
    Room room = promptChooseRoomFrom(hotel);
    /* TODO: Does 31 count as an available date */
    if (room.getAvailableDates().size() == 1 /* Not 0 to account for 31 */) {
      CLIUtility.printBorder();
      System.out.println("This room has no available dates!");
      return;
    }

    CLIUtility.printBorder();
    String guestName = CLIUtility.promptString(sc, "Enter your name:");

    CLIUtility.printBorder();
    System.out.println(room.getAvailableDatesAsCalendarString());
    int in = CLIUtility.promptInt(sc, "Enter a check-in date:", 1, 30);
    int out = CLIUtility.promptInt(sc, "Enter a check-out date:",
        in + 1, 31);

    CLIUtility.printBorder();
    if (hotel.addReservation(guestName, in, out, room)) {
      System.out.println("Reservation success!");
    } else {
      System.out.println(
          "Reservation not successful: room is unavailable at the specified time.");
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
        "Create hotel", "View hotel", "Manage hotel", "Simulate booking",
        "Exit program");
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

  public void closeScanner() {
    sc.close();
  }

  public static void main(String[] args) {
    CLIDriver cli = new CLIDriver();

    while (cli.doMenu())
      ;

    cli.closeScanner();
  }
}

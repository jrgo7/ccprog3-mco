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
   * Prompts the user to create a new {@link Hotel} instance. The user inputs a
   * string which is then used as the hotel's name. The new hotel is added to
   * the reservation system's hotel list.
   */
  public void displayCreateHotelScreen() {
    String[] hotelNames = reservationSystem.getHotelNames();
    String nameInput;
    boolean valid;

    CLIUtility.printBorder();

    do {
      valid = true;
      nameInput = CLIUtility.promptStringInput(sc, "Input a name for the hotel:");

      /* Search through existing hotels if name is taken */
      for (String i : hotelNames)
        if (i.equals(nameInput))
          valid = false;

      if (!valid)
        System.out.println("Name already exists. Please try again:");
    } while (!valid);

    reservationSystem.addHotel(new Hotel(nameInput));
  }

  /**
   * Allows the user to select a hotel from a list of all hotels in the system,
   * then displays the info of the selected hotel.
   * 
   * @see {@link #viewHotel}()
   */
  public void displayViewHotelScreen() {
    String[] hotelNames = reservationSystem.getHotelNames();
    Hotel hotel;
    int input;

    CLIUtility.printBorder();

    if (hotelNames.length == 0) {
      System.out.println("There are currently no hotels in the system.");
      return; /* TODO: Is this illegal D: */
    }

    hotel = reservationSystem.getHotel(
        CLIUtility.promptChoiceInput(sc, "Select a hotel:", hotelNames));

    CLIUtility.printBorder();

    System.out.println("Hotel information:");
    System.out.println("    Name: " + hotel.getName());
    System.out.println("    Rooms: " + hotel.getRoomCount());
    System.out.println("    Estimated earnings: " + hotel.getEarnings());

    input = CLIUtility.promptChoiceInput(sc, "Select an option",
        "Check room availability",
        "Check room data",
        "Check reservation data",
        "Exit");

    switch (input) {
    /* Total number of available and booked rooms for a selected date */
    case 0:
      CLIUtility.printBorder();
      int date = CLIUtility.promptIntegerInput(sc, "Enter a date:", 1, 31);
      System.out.println("Reservations on day " + date + ": " + hotel.countReservationsOnDay(date));
    }
  }

  

  public void displaySimulateBookingScreen() {
    String[] hotelNames = reservationSystem.getHotelNames();
    Hotel hotel;
    int input;

    CLIUtility.printBorder();

    if (hotelNames.length == 0) {
      System.out.println("There are currently no hotels in the system.");
      return; /* TODO: Is this illegal D: */
    }

    hotel = reservationSystem.getHotel(
        CLIUtility.promptChoiceInput(sc, "Select a hotel:", hotelNames));

    String[] roomNames = hotel.getRoomNames();
    Room room;

    CLIUtility.printBorder();

    if (roomNames.length == 0) {
      System.out.println("There are currently no rooms in the hotel.");
      return; /* TODO: Is this illegal D: */
    }

    room = hotel.getRoom(CLIUtility.promptChoiceInput(sc, "Select a room:", roomNames));

    CLIUtility.printBorder();

    String guestName = CLIUtility.promptStringInput(sc, "Enter your name:");

    CLIUtility.printBorder();

    int in = CLIUtility.promptIntegerInput(sc, "Enter a check-in date:", 1, 30);

    CLIUtility.printBorder();

    System.out.println("Enter a check-out date.");
    int out = CLIUtility.promptIntegerInput(sc, "Enter a check-in date:", in + 1, 31);

    hotel.addReservation(guestName, in, out, room);
  }

  /**
   * a
   */
  public void displayManageHotelScreen() {
    CLIUtility.printBorder();

    Hotel hotel = reservationSystem.getHotel(
        CLIUtility.promptChoiceInput(sc, "Select a hotel:",
            reservationSystem.getHotelNames()));

            CLIUtility.printBorder();

    System.out.println("Currently managing the following hotel:");
    System.out.println(hotel.getName());

    String[] options = {
        "Rename hotel", "Add room(s)", "Remove room(s)",
        "Update base price", "Remove reservation", "Remove hotel"
    };

    int choice = CLIUtility.promptChoiceInput(sc, "Select an option:", options);

    CLIUtility.printBorder();
    switch (choice) {
    case 0:
      hotel.setName(
          CLIUtility.promptStringInput(sc, "Please input the new name of the hotel:"));
      break;
    case 1:
      hotel
          .addRooms(Integer.parseInt(CLIUtility.promptStringInput(sc, "Please input the number of rooms to add:")));
      break;
    case 2:
      hotel.removeRoom(
          CLIUtility.promptChoiceInput(sc, "Select a room to remove:", hotel.getRoomNames()));
      break;
    case 3:
      if (hotel.getRoomCount() != 0) {
        System.out.println(
            "There should be zero rooms in the hotel. Please try again.");
        break;
      }
      /* TODO: Catch the NumberFormatException thrown by parseDouble() */
      hotel.setBasePrice(Double
          .parseDouble(CLIUtility.promptStringInput(sc, "Please input the new base price:")));
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
        "Create Hotel",
        "View Hotel",
        "Manage Hotel",
        "Simulate Booking",
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

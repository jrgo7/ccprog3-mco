package src.view.cli;

import java.util.Scanner;

import src.model.Hotel;
import src.model.Reservation;
import src.model.ReservationSystem;
import src.model.Room;

/** Handles business logic and user interaction on the command line. */
public class CLIDriver {
    /** The {@link ReservationSystem} tied to the driver. */
    private ReservationSystem system;

    /** The input scanner tied to the driver. */
    private Scanner sc;

    /**
     * Initializes a new driver instance used to interact with a
     * {@link ReservationSystem}. The input scanner is initialized to read input
     * from the terminal window.
     * 
     * @param system The reservation system to tie to the driver
     */
    CLIDriver(ReservationSystem system) {
        this.system = system;
        this.sc = new Scanner(System.in);
    }

    /**
     * Prompts the user to create a new {@link Hotel}. The user inputs a string
     * which is then used as the hotel's name. The new hotel is added to the
     * {@link ReservationSystem} tied to the driver. Fails if the user aborts.
     */
    private void displayCreateHotelScreen() {
        String name, message = "Input a name for the hotel:";
        boolean valid;
        Hotel hotel;

        do {
            CLIUtility.printBorder();
            name = CLIUtility.promptString(this.sc, message);

            /* Exit if the user aborts */
            if (!CLIUtility.confirm(this.sc,
                    "Creating a new hotel named " + name + ". Proceed?"))
                return;

            valid = this.system.addHotel(name);
            /*
             * Ensure that the new hotel's name does not yet exist in the system
             */
            if (!valid)
                message = "Name already exists in the system. Please input a new name:";
        } while (!valid);
    }

    /**
     * Displays the number of reservations and available rooms for a given
     * {@link Hotel} on a given date.
     * 
     * @param hotel The hotel instance to inspect
     * @see #displayViewHotelScreen()
     */
    private void viewAvailabilityCount(Hotel hotel) {
        int date = CLIUtility.promptInt(this.sc,
                "Enter a date (1-31):", 1, 31);
        boolean isOneRoom = hotel.getAvailableRoomCount(date) == 1;

        CLIUtility.printBorder();
        System.out.printf("""
                Reservations on date %d: %d
                There %s %d room%s available.
                """,
                date,
                hotel.getReservationCountOnDate(date, false),
                isOneRoom ? "is" : "are",
                hotel.getAvailableRoomCount(date),
                isOneRoom ? "" : "s");
    }

    /**
     * Prompts the user to select a {@link Room} from within a given
     * {@link Hotel}, then displays the data for that room through
     * {@link Room#toString()}.
     * 
     * @param hotel The hotel instance containing the room to inspect
     * @see #displayViewHotelScreen()
     */
    private void viewRoomData(Hotel hotel) {
        /* Note that there is guaranteed to always be at least one room */
        int roomIndex = CLIUtility.promptChoice(this.sc,
                "Select a room:", hotel.getRoomNames());

        CLIUtility.printBorder();
        System.out.println(hotel.getRoomString(roomIndex));
    }

    /**
     * Prompts the user to select a {@link Reservation} from within a given
     * {@link Hotel}, then displays the data for that reservation through
     * {@link Reservation#toString()}.
     * 
     * @param hotel The hotel instance containing the reservation to inspect
     * @see #displayViewHotelScreen()
     */
    private void viewReservationData(Hotel hotel) {
        /* Exit if there are no reservations in the hotel */
        if (hotel.getReservationCount() == 0) {
            System.out.println(
                    "No reservations have been booked for the hotel. Please try again.");
            return;
        }

        int reservationIndex = CLIUtility.promptChoice(this.sc,
                "Select a reservation:", hotel.getReservationNames());

        CLIUtility.printBorder();
        System.out.println(hotel.getReservationString(reservationIndex));
    }

    /**
     * Allows the user to select a {@link Hotel} from a list of all hotels in
     * the {@link ReservationSystem}, then displays information relevant to the
     * selected hotel.
     */
    private void displayViewHotelScreen() {
        CLIUtility.printBorder();

        /* Exit if there are no hotels in the system */
        if (!this.system.hasHotels()) {
            System.out.println(
                    "No hotels exist in the system. Please try again.");
            return;
        }

        int hotelIndex = CLIUtility.promptChoice(this.sc,
                "Select a hotel:", this.system.getHotelNames());

        CLIUtility.printBorder();
        System.out.println(system.getHotelString(hotelIndex));

        CLIUtility.printBorder();
        int input = CLIUtility.promptChoice(this.sc, "Select an option:",
                "Check availability",
                "Check a room",
                "Check a reservation",
                "Back");

        CLIUtility.printBorder();
        switch (input) {
        /* Number of available and booked rooms on a date */
        case 0:
            viewAvailabilityCount(hotelIndex);
            break;
        /* Room data */
        case 1:
            viewRoomData(hotelIndex);
            break;
        /* Reservation data */
        case 2:
            viewReservationData(hotelIndex);
            break;
        /* Back */
        case 3:
            System.out.println("Returning to the main menu.");
            break;
        }
    }

    /**
     * Allows the user to rename the {@link Hotel} at a given index within the
     * {@link ReservationSystem}. Fails if the user aborts.
     * 
     * @param hotelIndex The index of the hotel too rename
     * @see ReservationSystem#renameHotel(int, String)
     * @see #displayManageHotelScreen()
     */
    private void renameHotel(int hotelIndex) {
        String name, message = "Input a new name for the hotel:";
        boolean valid;

        /* Get the original name of the hotel */
        String oldName = this.system.getHotelName(hotelIndex);
        do {
            name = CLIUtility.promptString(this.sc, message);

            /* Exit if the user aborts */
            if (!CLIUtility.confirm(this.sc,
                    "Renaming hotel " + oldName + " to " + name + ". Proceed?"))
                return;

            valid = this.system.renameHotel(hotelIndex, name);
            if (!valid) {
                message = "The name already exists in the system. Try again:";
                /* Print the border again when retrying */
                CLIUtility.printBorder();
            }
        } while (!valid);
    }

    /**
     * Allows the user to add rooms to a given {@link Hotel}. Fails if the user
     * aborts, or if there are already 50 rooms in the hotel.
     * 
     * @param hotel The hotel to add rooms to
     * @see Hotel#addRooms(int)
     * @see #displayManageHotelScreen()
     */
    private void addRooms(Hotel hotel) {
        int roomCount = hotel.getRoomCount();
        if (roomCount >= 50) {
            System.out.println("You cannot add anymore rooms.");
            return;
        }

        /*
         * Ensure the user can only input up to a total of 50 rooms in a hotel
         */
        int addableRooms = 50 - roomCount;
        int count = CLIUtility.promptInt(this.sc,
                String.format("Input the number of rooms to add (1-%d):",
                        addableRooms),
                1, addableRooms);

        /* Prompt user to enter the room type */
        int type = CLIUtility.promptChoice(sc, "Select a room type", "Regular",
                "Deluxe", "Executive") + 1;

        /* Only proceed if the user does not abort */
        if (CLIUtility.confirm(this.sc,
                "Adding " + count + " rooms to hotel " + hotel.getName()
                        + ". Proceed?"))
            hotel.addRooms(count, type);
    }

    /**
     * Allows the user to remove rooms from a given {@link Hotel}.
     * 
     * @param hotel The hotel to remove rooms from
     * @return {@code true} if the user opts to remove more rooms, {@code false}
     *         if the user aborts
     * @see Hotel#removeRoom(int)
     * @see #displayManageHotelScreen()
     */
    private boolean removeRooms(Hotel hotel) {
        if (hotel.getRoomCount() <= 1) {
            System.out.println("You cannot remove the only room in a hotel.");
            return false;
        }

        String[] roomNames = hotel.getRoomNames();
        int roomIndex = CLIUtility.promptChoice(this.sc,
                "Select a room to remove:", roomNames);

        /* Exit if the user aborts */
        if (!CLIUtility.confirm(this.sc,
                "Removing the room " + roomNames[roomIndex] + ". Proceed?"))
            return false;

        if (hotel.removeRoom(roomIndex)) {
            System.out.println("Successfully removed room.");
        } else {
            System.out
                    .println(
                            "Couldn't remove room: room has at least one reservation.");
        }

        CLIUtility.printBorder();
        int continueChoice = CLIUtility.promptChoice(this.sc,
                "Remove another room?",
                "Yes", "No");

        return continueChoice == 0;
    }

    /**
     * Allows the user to update the base price for a hotel. The new price must
     * be at least 100.00.
     * 
     * @param hotel The hotel to update the base price of
     * @see Hotel#setBasePrice(double)
     * @see #displayManageHotelScreen()
     */
    private void updateBasePrice(Hotel hotel) {
        /* Exit if there are reservations in the hotel */
        if (hotel.getReservationCount() > 0) {
            System.out.println(
                    "Cannot change the base price while there are reservations present.");
            return;
        }

        double oldBasePrice = hotel.getBasePrice();
        double newBasePrice = Double.parseDouble(
                CLIUtility.promptString(this.sc,
                        "Please input the new base price:"));

        /* Exit if the user aborts */
        if (!CLIUtility.confirm(this.sc,
                String.format(
                        "Updating the base price of hotel %s from %.2f to %.2f. Proceed?",
                        hotel.getName(), oldBasePrice, newBasePrice)))
            return;

        CLIUtility.printBorder();
        if (hotel.setBasePrice(newBasePrice))
            System.out.println("Set the base price to " + newBasePrice + ".");
        else
            System.out
                    .println("You cannot set a base price lower than 100.00!");
    }

    /**
     * Allows the user to remove a {@link Reservation} from a given
     * {@link Hotel}.
     * 
     * @param hotel The hotel containing the reservation to remove
     * @see Hotel#removeReservation(int)
     * @see #displayManageHotelScreen()
     */
    private void removeReservation(Hotel hotel) {
        /* Exit if there are no reservations in the hotel */
        if (hotel.getReservationCount() <= 0) {
            System.out.println(
                    "There are no reservations to remove.");
            return;
        }

        String[] reservationNames = hotel.getReservationNames();
        int reservationIndex = CLIUtility.promptChoice(this.sc,
                "Remove which reservation?", reservationNames);

        /* Only proceed if the user does not abort */
        if (CLIUtility.confirm(this.sc,
                "Removing the reservation " + reservationNames[reservationIndex]
                        + ". Proceed?"))
            hotel.removeReservation(reservationIndex);
    }

    /**
     * Allows the user to select a {@link Hotel} from a list of all hotels in
     * the {@link ReservationSystem}, then allows them to modify properties of
     * the hotel.
     */
    private void displayManageHotelScreen() {
        CLIUtility.printBorder();

        /* Exit if there are no hotels in the system */
        if (!this.system.hasHotels()) {
            System.out.println(
                    "No hotels exist in the system. Please try again.");
            return;
        }

        int hotelIndex = CLIUtility.promptChoice(this.sc,
                "Select a hotel:", this.system.getHotelNames());
        Hotel hotel = this.system.getHotel(hotelIndex);

        CLIUtility.printBorder();
        System.out.println("Currently managing the following hotel:");
        System.out.println(hotel.getName());

        CLIUtility.printBorder();
        int choice = CLIUtility.promptChoice(this.sc, "Select an option:",
                "Rename hotel", "Add room(s)", "Remove room(s)",
                "Update base price", "Remove reservation", "Remove hotel",
                "Back");

        CLIUtility.printBorder();
        switch (choice) {
        /* Rename hotel */
        case 0:
            renameHotel(hotelIndex);
            break;
        /* Add room(s) */
        case 1:
            addRooms(hotel);
            break;
        /* Remove room(s) */
        case 2:
            while (removeRooms(hotel));
            break;
        /* Update base price */
        case 3:
            updateBasePrice(hotel);
            break;
        /* Remove reservation */
        case 4:
            removeReservation(hotel);
            break;
        /* Remove hotel */
        case 5:
            if (CLIUtility.confirm(this.sc,
                    "Removing the hotel " + hotel.getName() + ". Proceed?")) {
                this.system.removeHotel(hotelIndex);
                System.out
                        .println("Removed hotel " + hotel.getName()
                                + " from the list.");
            }
            break;
        /* Back */
        case 6:
            System.out.println("Returning to the main menu.");
            break;
        }
    }

    /**
     * Allows the user to create a new {@link Reservation} by providing booking
     * information, then binds it to a selected {@link Hotel}.
     */
    private void displaySimulateBookingScreen() {
        /* Exit if there are no hotels in the system */
        if (!this.system.hasHotels()) {
            System.out.println("There are currently no hotels in the system.");
            return;
        }

        /* Hotel selection */
        CLIUtility.printBorder();
        Hotel hotel = this.system.getHotel(
                CLIUtility.promptChoice(this.sc, "Select a hotel:",
                        this.system.getHotelNames()));

        /* Room selection */
        CLIUtility.printBorder();
        String[] roomNames = hotel.getRoomNames();
        int roomIndex = CLIUtility.promptChoice(this.sc, "Select a room:",
                roomNames);

        /*
         * Check if 1 instead of 0 to account for 31, which is marked as
         * available but for this program specifically is unselectable.
         */
        if (hotel.getAvailableDatesForRoom(roomIndex).size() <= 1) {
            CLIUtility.printBorder();
            System.out.println("This room has no available dates!");
            return;
        }

        /* Guest data input */
        CLIUtility.printBorder();
        String guestName = CLIUtility.promptString(this.sc, "Enter your name:");

        CLIUtility.printBorder();
        System.out.println(hotel.getCalendarStringForRoom(roomIndex));
        int in = CLIUtility.promptInt(this.sc,
                "Enter a check-in date (you cannot select 31):", 1, 30);
        int out = CLIUtility.promptInt(this.sc,
                "Enter a check-out date (you cannot check out on the same date):",
                in + 1, 31);

        /* Prompt the user to input a discount code */
        String discountCode = null;
        if (CLIUtility.confirm(sc, "Use a discount code?"))
            discountCode = CLIUtility.promptString(sc, "Enter a code:");

        /* Exit if the user aborts */
        if (!CLIUtility.confirm(this.sc,
                String.format(
                        "Booking room %s of hotel %s for guest %s.\nCheck-in: %d / Check-out: %d\nProceed?",
                        roomNames[roomIndex], hotel.getName(), guestName, in,
                        out)))
            return;

        CLIUtility.printBorder();
        if (hotel.addReservation(guestName, in, out, roomIndex, discountCode)) {
            System.out.println("Reservation success!");
        } else {
            System.out.println(
                    "Reservation failed. The selected room is unavailable or the discount code used\nis invalid/unapplicable.");
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
        int choice = CLIUtility.promptChoice(this.sc, "Select an option:",
                "Create hotel", "View hotel", "Manage hotel",
                "Simulate booking",
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

    /** Closes the {@link Scanner} tied to the driver. */
    public void closeScanner() {
        this.sc.close();
    }

    /**
     * Main function.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();
        CLIDriver cli = new CLIDriver(system);

        while (cli.doMenu());

        cli.closeScanner();
    }
}
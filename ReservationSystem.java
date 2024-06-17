import java.util.ArrayList;
import java.util.Scanner;

public class ReservationSystem {
    public ArrayList<Hotel> hotels;

    public ReservationSystem() {
        hotels = new ArrayList<Hotel>();
    }

    public void createHotel(Scanner sc) {
        System.out.print("Please enter a name for the hotel.\n>");
        String name = sc.nextLine();
        hotels.add(new Hotel(name));
    }

    /**
     * Choose among an array of strings, and return the corresponding index.
     * 
     * @param sc
     * @param message
     * @param options
     * @return
     */
    private int menu(Scanner sc, String message, String[] options) {
        int choice;
        do {
            System.out.println(message);
            for (int i = 0, size = options.length; i < size; i++) {
                System.out.printf("[%d] %s\n", i + 1, options[i]);
            }
            choice = sc.nextInt();
        } while (0 > choice || choice > options.length);
        return choice - 1;
    }

    /**
     * Choose among an arraylist of strings, and return the corresponding index.
     * 
     * @param sc
     * @param message
     * @param options
     * @return
     */
    private int menu(Scanner sc, String message, ArrayList<String> options) {
        int choice;
        int size = hotels.size();
        do {
            System.out.println(message);
            for (int i = 0; i < size; i++) {
                System.out.printf("[%d] %s\n", i + 1, options.get(i));
            }
            choice = sc.nextInt();
        } while (0 > choice || choice > size);
        return choice - 1;
    }

    public void viewHotel(Scanner sc) {
        int hotelIndex;
        int roomIndex;
        int reservationIndex;
        int infoIndex;
        int date;
        Hotel chosenHotel;
        String options[] = {
            "Check hotel information",
            "Check room availability on a date",
            "Check a room",
            "Check a reservation"
        };
        hotelIndex = this.menu(sc, "Choose a hotel", this.getHotelsString());
        chosenHotel = this.hotels.get(hotelIndex);
        infoIndex = this.menu(sc, "View which information?", options);

        switch (infoIndex) {
            case 1: // Hotel information
                System.out.printf(
                        "%s Hotel\n%d Rooms\n%d Estimated earnings this month",
                        chosenHotel.getName(),
                        chosenHotel.getRoomCount(),
                        chosenHotel.getEarnings());
                break;
            case 2: // Room availability on a date
                roomIndex = this.menu(sc, "Pick a room", chosenHotel.getRoomsString());
                date = sc.nextInt(); // TODO: Validation -- must be within 1-31
                if (chosenHotel.getRoom(roomIndex).isAvailableOn(date)) {
                    System.out.println("The room is available on that date.");
                } else {
                    System.out.println("The room is not available on that date.");
                }
                break;
            case 3: // Room data
                roomIndex = this.menu(sc, "Pick a room", chosenHotel.getRoomsString());
                String roomData = chosenHotel.getRoom(roomIndex).getDataString();
                System.out.println(roomData);
                break;
            case 4: // Reservation data
                reservationIndex = this.menu(sc, "Pick a reservation", chosenHotel.getReservationsString());
                String reservationData = chosenHotel.getReservation(reservationIndex).getDataString();
                System.out.println(reservationData);
                break;
        }
    }

    public void manageHotel(Scanner sc) {
        int hotelIndex;
        hotelIndex = this.menu(sc, "Choose a hotel", this.getHotelsString());
        Hotel hotel = this.hotels.get(hotelIndex);
    }

    public boolean simulateBooking(Scanner sc) {
        Hotel hotel;
        Date checkIn;
        Date checkOut;
        return true;
    }

    public ArrayList<String> getHotelsString() {
        ArrayList<String> hotelsString = new ArrayList<String>();
        int i = 0;
        for (Hotel hotel : hotels) {
            hotelsString.add(String.format("[%d] %s\n", 1 + i++, hotel.getName()));
        }
        return hotelsString;
    }
}
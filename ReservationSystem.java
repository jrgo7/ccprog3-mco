import java.util.ArrayList;
import java.util.Scanner;

// TODO: Transition from using Scanner sc to passing in parameters

public class ReservationSystem {
    public ArrayList<Hotel> hotels;

    public ReservationSystem() {
        hotels = new ArrayList<Hotel>();
    }

    public void createHotel(String name) {
        hotels.add(new Hotel(name));
    }

    /**
     * Choose among an array of strings, and return the corresponding index.
     * TODO: Move this to Driver?
     * 
     * @param sc
     * @param message
     * @param options
     * @return
     */
    public int menu(Scanner sc, String message, String[] options) {
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
     * TODO: Move this to Driver?
     * 
     * @param sc
     * @param message
     * @param options
     * @return
     */
    public int menu(Scanner sc, String message, ArrayList<String> options) {
        int choice;
        int size = options.size();
        do {
            System.out.println(message);
            for (int i = 0; i < size; i++) {
                System.out.printf("[%d] %s\n", i + 1, options.get(i));
            }
            choice = sc.nextInt();
        } while (0 > choice || choice > size);
        return choice - 1;
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
        for (Hotel hotel : hotels) {
            hotelsString.add(hotel.getName());
        }
        return hotelsString;
    }

    public Hotel getHotel(int hotelIndex) {
        return this.hotels.get(hotelIndex);
    }
}
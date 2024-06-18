import java.util.ArrayList;
import java.util.Scanner;

// TODO: Transition from using Scanner sc to passing in parameters

public class ReservationSystem {
    public ArrayList<Hotel> hotels;

    public ReservationSystem() {
        hotels = new ArrayList<Hotel>();
    }

    public Hotel getHotel(int hotelIndex) {
        return this.hotels.get(hotelIndex);
    }

    public ArrayList<String> getHotelsString() {
        ArrayList<String> hotelsString = new ArrayList<String>();
        for (Hotel hotel : hotels) {
            hotelsString.add(hotel.getName());
        }
        return hotelsString;
    }

    public void createHotel(String name) {
        hotels.add(new Hotel(name));
    }

    // // TODO: Move to UI.java
    // public void manageHotel(Scanner sc) {
    //     int hotelIndex;
    //     hotelIndex = this.menu(sc, "Choose a hotel", this.getHotelsString());
    //     Hotel hotel = this.hotels.get(hotelIndex);
    // }

    // // TODO: Move to UI.java
    // public boolean simulateBooking(Scanner sc) {
    //     Hotel hotel;
    //     Date checkIn;
    //     Date checkOut;
    //     return true;
    // }
}
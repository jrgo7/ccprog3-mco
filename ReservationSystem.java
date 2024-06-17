import java.util.ArrayList;
import java.util.Scanner;

public class ReservationSystem {
    public ArrayList<Hotel> hotels;
    public ReservationSystem() {
        hotels = new ArrayList<Hotel>();
    }

    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();

        System.out.println("Hotel Reservation System\nCreating new hotel. Input name: ");
        Scanner test = new Scanner(System.in);

        Hotel hotel = new Hotel(test.nextLine());
        
        
        system.hotels.add(hotel);
        hotel.setBasePrice(6900);
        for (int i = 1; i <= 50; i++)
            hotel.addRoom("Room #" + String.valueOf(i));

        hotel.addReservation("Misaki Tobisawa", 1, 3, "Room #39");

        test.close();
    }
}
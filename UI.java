import java.util.Scanner;

public class UI {
    private static void displayCreateHotelScreen(Scanner sc, ReservationSystem rs) {
        System.out.print("Please enter a name for the hotel.\n>");
        String name = sc.nextLine();
        System.out.println("You inputted " + name);
        rs.createHotel(name);
    }

    private static int inputDate(Scanner sc) {
        int date;
        do {
            date = sc.nextInt();
        } while (date < 0 || date > 31);
        return date;
    }

    // TODO: I wonder if I've delegated too much work to Driver -- there's no
    // TODO: viewHotel method anymore
    private static void displayViewHotelScreen(Scanner sc, ReservationSystem rs) {
        int hotelIndex;
        int roomIndex;
        int reservationIndex;
        int date;
        int choice;
        Hotel hotel;
        Room room;
        Reservation reservation;
        String options[] = {
                "Check hotel information",
                "Check room availability on a date",
                "Check a room",
                "Check a reservation"
        };
        hotelIndex = rs.menu(sc, "Choose a hotel", rs.getHotelsString());
        hotel = rs.getHotel(hotelIndex);
        choice = rs.menu(sc, "View which information?", options);
        sc.nextLine();
        switch (choice) {
            case 0: // Hotel information
                System.out.println(hotel.getDataString());
                break;
            case 1: // Room availability
                roomIndex = rs.menu(sc, "Choose a room", hotel.getRoomsString());
                room = hotel.getRoom(roomIndex);
                date = inputDate(sc);
                if (room.isAvailableOn(date)) {
                    System.out.println("The room is available on this date.");
                } else {
                    System.out.println("The room is not available on this date.");
                }
                break;
            case 2: // Room data
                roomIndex = rs.menu(sc, "Choose a room", hotel.getRoomsString());
                room = hotel.getRoom(roomIndex);
                String roomData = room.getDataString();
                System.out.println(roomData);
                break;
            case 3: // Reservation data
                reservationIndex = rs.menu(sc, "Pick a reservation", hotel.getReservationsString());
                reservation = hotel.getReservation(reservationIndex);
                String reservationData = reservation.getDataString();
                System.out.println(reservationData);
                break;
        }
    }

    // TODO: Lowy Moment
    private static void displayManageHotelScreen(Scanner sc, ReservationSystem rs) {
        rs.manageHotel(sc);
    }

    // TODO
    private static void displaySimulateBookingScreen(Scanner sc, ReservationSystem rs) {
        rs.simulateBooking(sc);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ReservationSystem rs = new ReservationSystem();
        int input;
        String[] options = {
                "Create hotel",
                "View hotel",
                "Manage hotel",
                "Simulate booking",
                "Exit"
        };
        do {
            input = rs.menu(sc, "Hotel Reservation System", options);
            sc.nextLine();
            switch (input) {
                case 0:
                    displayCreateHotelScreen(sc, rs);
                    break;
                case 1:
                    displayViewHotelScreen(sc, rs);
                    break;
                case 2:
                    displayManageHotelScreen(sc, rs);
                    break;
                case 3:
                    displaySimulateBookingScreen(sc, rs);
                    break;
            }
        } while (input != 4);
        sc.close();
    }
}

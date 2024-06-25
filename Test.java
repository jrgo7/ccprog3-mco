import java.util.Scanner;

public class Test {
  public static void testReservationSystem() {
    System.out.println("Testing ReservationSystem...");
    ReservationSystem rs = new ReservationSystem();
    System.out.println("\tTesting addHotel...");
    System.out.println(rs.addHotel(new Hotel("Test")));
    System.out.println(rs.addHotel(new Hotel("Test")));
    System.out.println("\tTesting getHotel...");
    System.out.println(rs.getHotel(0));
    System.out.println(rs.getHotel(1));
    System.out.println("\tTesting removeHotel...");
    System.out.println(rs.removeHotel(0));
    System.out.println(rs.removeHotel(0));
    System.out.println("\tTesting hotelNameExists...");
    rs.addHotel(new Hotel("Misaki"));
    rs.addHotel(new Hotel("Misaki")); // hotelNameExists("Misaki") returns true
    rs.renameHotel(0, "Tobisawa"); // hotelNameExists("Tobisawa") returns false
    System.out.println("\tTesting getHotelNames...");
    System.out.println("\t\tTobisawa should be printed");
    for (String s : rs.getHotelNames()) {
      System.out.println(s);
    }
    rs.removeHotel(0);
    System.out.println("\t\tNothing should be printed");
    for (String s : rs.getHotelNames()) {
      System.out.println(s);
    }
    System.out.println("\tTesting renameHotel...");
    rs.addHotel(new Hotel("Misaki"));
    System.out.println("\t\ttrue should be printed");
    System.out.println(rs.renameHotel(0, "Tobisawa"));
    rs.renameHotel(0, "Misaki");
    rs.addHotel(new Hotel("Tobisawa"));
    System.out.println("\t\tfalse should be printed");
    System.out.println(rs.renameHotel(0, "Tobisawa"));
  }
  
  public static void testHotel() {
    Hotel hotel = new Hotel("Misaker");
    // hotel.addRooms(49);
    hotel.addReservation("Misaki", 1, 5, 0);
    for (String s: hotel.getReservationNames()) {
        System.out.print(s + ',');
    }
    System.out.println();
    System.out.println(hotel.getRoomString(0));
    System.out.println(hotel.getReservationString(0));
  }

  public static void testRoom() {
    Room room = new Room("RM001", 1299.00);
    Reservation reservation = new Reservation("Misaki", 1, 5, room);
    Reservation reservation2 = new Reservation("Misaki 2", 5, 10, room);
    Reservation reservation3 = new Reservation("Misaki 3", 10, 31, room);

    System.out.println(room);

    System.out.println(room.getName());

    System.out.println(room.getBasePrice());

    System.out.println(room.setBasePrice(500));
    System.out.println(room.setBasePrice(100));
    System.out.println(room.setBasePrice(99));

    System.out.println(room.getReservationCount());

    System.out.println(room.removeReservation(reservation));
    System.out.println(room.removeReservation(reservation2));

    for (int i : room.getAvailableDates()) {
      System.out.print(i + ",");
    }
    System.out.println();
    System.out.println(room.getAvailableDatesAsCalendarString());
    room.removeReservation(reservation2);
    room.addReservation(reservation);
    for (int i : room.getAvailableDates()) {
      System.out.print(i + ",");
    }
    System.out.println();
    System.out.println(room.getAvailableDatesAsCalendarString());
    room.addReservation(reservation3);
    for (int i : room.getAvailableDates()) {
      System.out.print(i + ",");
    }
    System.out.println();
    System.out.println(room.getAvailableDatesAsCalendarString());

    room.removeReservation(reservation);
    room.removeReservation(reservation3);
    System.out.println(room.isAvailableOn(1));
    room.addReservation(reservation);
    System.out.println(room.isAvailableOn(1));

    System.out.println(room.toString());
  }

  public static void testReservation() {
        Room room = new Room("Test Room", 1299);
        Reservation reservation = new Reservation("Misaki", 1, 5, room);
        room.addReservation(reservation);
        System.out.println(reservation.getGuestName());
        System.out.println(reservation.getCheckIn());
        System.out.println(reservation.getCheckOut());
        System.out.println(reservation.getRoom());
        System.out.println(reservation.getNightCount());
        System.out.println(reservation.getTotalPrice());
        System.out.println(reservation.getPriceBreakdown());
        System.out.println(reservation.toString());
  }

    public static void main(String[] args) {
        // testReservationSystem();
        // testRoom();
        testHotel();
    }
}
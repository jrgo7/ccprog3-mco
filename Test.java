public class Test {
    public static void testReservationSystem() {
        System.out.println("Testing ReservationSystem...");
        ReservationSystem rs = new ReservationSystem();
        System.out.println("\tTesting addHotel...");
        System.out.println(rs.addHotel("Test"));
        System.out.println(rs.addHotel("Test"));
        System.out.println("\tTesting getHotel...");
        System.out.println(rs.getHotel(0));
        System.out.println(rs.getHotel(1));
        System.out.println("\tTesting removeHotel...");
        rs.removeHotel(0);
        // rs.removeHotel(0); // causes a crash
        System.out.println("\tTesting hotelNameExists...");
        rs.addHotel("Misaki");
        rs.addHotel("Misaki"); // hotelNameExists("Misaki") returns true
        rs.renameHotel(0, "Tobisawa"); // hotelNameExists("Tobisawa") returns false
        System.out.println("\tTesting getHotelNames...");
        System.out.println("\t\tTobisawa should be printed");
        for (String s: rs.getHotelNames()) {
            System.out.println(s);
        }
        rs.removeHotel(0);
        System.out.println("\t\tNothing should be printed");
        for (String s: rs.getHotelNames()) {
            System.out.println(s);
        }
        System.out.println("\tTesting renameHotel...");
        rs.addHotel("Misaki");
        System.out.println("\t\ttrue should be printed");
        System.out.println(rs.renameHotel(0, "Tobisawa"));
        rs.renameHotel(0, "Misaki");
        rs.addHotel("Tobisawa");
        System.out.println("\t\tfalse should be printed");
        System.out.println(rs.renameHotel(0, "Tobisawa"));
    }
    public static void main(String[] args) {
        testReservationSystem();
    }
}
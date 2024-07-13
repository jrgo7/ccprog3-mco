public class GUIDriver {
    public static void main(String[] args) {
        View view = new View();
        ReservationSystem reservationSystem = new ReservationSystem();
        Controller controller = new Controller(view, reservationSystem);
    }
}
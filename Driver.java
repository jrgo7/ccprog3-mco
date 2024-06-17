import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ReservationSystem system = new ReservationSystem();
        int input;
        do {
            input = sc.nextInt();
            switch (input) {
                case 1:
                    system.createHotel(sc);
                    break;
                case 2:
                    system.viewHotel(sc);
                case 3:
                    system.manageHotel(sc);
                case 4:
                    system.simulateBooking(sc);
            }
        } while (input != 0);
        sc.close();
    }
}

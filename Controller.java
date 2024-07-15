import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private View view;
    private ReservationSystem reservationSystem;

    public Controller(View view, ReservationSystem reservationSystem) {
        this.view = view;
        this.reservationSystem = reservationSystem;
        updateView();
        view.setActionListener(this);
    }

    public void updateView() {
        view.setHotelList(reservationSystem.getHotelNames());
        view.setHotelConfigButtonsEnabled(reservationSystem.getHotelCount() > 0);
    }

    private Hotel getSelectedHotel() {
        return reservationSystem.getHotel(view.getSelectedHotelIndex());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals("Add hotel")) {
            String name = view.promptHotelName();
            if (name != null) {
                reservationSystem.addHotel(new Hotel(name));
                updateView();
                view.setSelectedHotelIndex(reservationSystem.getHotelCount()-1);
            }
        } else if (e.getActionCommand().equals("View hotel")) {
            int option = view.showHotelInfo(getSelectedHotel());
            switch (option) {
                case 0: // Check availability
                    view.showHotelAvailability(getSelectedHotel());
                    break;
                case 1: // Check a room
                    break;
                case 2: // Check a reservation
                    break;
            }
        } else if (e.getActionCommand().equals("Manage hotel")) {

        } else if (e.getActionCommand().equals("Simulate booking")) {

        }
    }
}

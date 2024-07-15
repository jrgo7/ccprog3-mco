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
        if (reservationSystem.getHotelCount() > 0) {
            view.setHotelConfigButtonsEnabled(true);
        } else {
            view.setHotelConfigButtonsEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals("Add hotel")) {
            reservationSystem.addHotel(new Hotel(view.promptHotelName()));
            updateView();
        } else if (e.getActionCommand().equals("View hotel")) {
            view.showHotelInfo(
                reservationSystem.getHotel(
                    view.getCurrentHotelIndex(
                        reservationSystem.getHotelCount())));
        } else if (e.getActionCommand().equals("Manage hotel")) {

        } else if (e.getActionCommand().equals("Simulate booking")) {

        }
    }
}

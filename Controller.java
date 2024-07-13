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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals("Add hotel")) {
            reservationSystem.addHotel(new Hotel(view.promptHotelName()));
            updateView();
        }
    }
}

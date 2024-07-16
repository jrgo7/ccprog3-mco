import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private View view;
    private ViewChooseRoom viewChooseRoom;
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
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Add hotel")) {
            String name = view.promptHotelName();
            if (name != null) {
                reservationSystem.addHotel(new Hotel(name));
                updateView();
                view.setSelectedHotelIndex(reservationSystem.getHotelCount() - 1);
            }
        } else if (actionCommand.equals("View hotel")) {
            int option = view.showHotelInfo(getSelectedHotel());
            switch (option) {
                case 0: // Check availability
                    view.showHotelAvailability(getSelectedHotel());
                    break;
                case 1: // Check a room
                    createViewChooseRoom(
                            "Select a room to check", "Check room");
                    break;
                case 2: // Check a reservation
                    break;
            }
        } else if (actionCommand.equals("Manage hotel")) {

        } else if (actionCommand.equals("Simulate booking")) {

        } else if (actionCommand.equals("Check room")) {
            view.showInfo(
                    getSelectedHotel().getRoomString(
                            viewChooseRoom.getSelectedRoomIndex()));
        }
    }

    /**
     * Creates a pop-up dialog for the user to choose the room.
     * 
     * @param context A string specifying the context to be checked in
     *                `handleViewRoomCommand`
     */
    public void createViewChooseRoom(String title, String buttonText) {
        viewChooseRoom = new ViewChooseRoom(
                this, view, title, buttonText, getSelectedHotel());
        viewChooseRoom.setActionListener(this);
        viewChooseRoom.setVisible(true);
    }
}

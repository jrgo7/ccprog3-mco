package src.view.gui;

import java.awt.event.*;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import src.model.Hotel;
import src.model.ReservationSystem;

public class Controller implements ActionListener, ListSelectionListener, MouseListener {
    ReservationSystem reservationSystem;
    TopView view;

    public Controller(ReservationSystem reservationSystem, TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
        this.view.setListeners(this);
        this.update();
    }

    public void update() {
        view.setHotelListData(reservationSystem.getHotelNamesAsList());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return; // repeated selection; ignore
        }
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        int selectedIndex = lsm.getMinSelectionIndex();
        int hotelCount = reservationSystem.getHotelCount();
        System.out.print(selectedIndex + " ");
        if (selectedIndex == hotelCount) {
            // Selected "Add hotel" button
            if (reservationSystem.addHotel(view.promptAddHotel())) {
                view.setTabIndex(TopView.VIEW_HOTEL_SCREEN);
                this.update();
                view.setHotelListSelectedIndex(selectedIndex); // redundant but gives a nice blue selection
                                                               // highlight
            } else if (reservationSystem.getHotelCount() > 0) {
                view.setHotelListSelectedIndex(
                        view.getHotelListPrevSelectedIndex());
            } else {
                view.removeHotelListSelection();
            }
        }
        if (selectedIndex >= 0 && selectedIndex < hotelCount) {
            view.setHotelNameLabelText(
                    reservationSystem.getHotelNames()[selectedIndex]);
            view.setHotelDataText(
                    reservationSystem.getHotel(selectedIndex).toString());
            view.setHotelListPrevSelectedIndex(selectedIndex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int row = view.getAvailabilityCalendarRowFromMouse(e.getPoint());
        int col = view.getAvailabilityCalendarColFromMouse(e.getPoint());
        int day = row * 7 + col % 7 + 1;
        if (day > 31) {
            return; // Block invalid input
        }
        System.out.printf("Selected day %d\n", day);
        // Get the context of the event then do the appropriate action
        switch (view.getContext()) {
            case (TopView.VIEW_HOTEL_SCREEN):
                switch (view.getSubcontext()) {
                    case (TopView.CHECK_AVAILABILITY_SCREEN):
                        handleCheckAvailability(day);
                        break;
                }
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void handleCheckAvailability(int day) {
        Hotel hotel = reservationSystem.getHotel(view.getHotelListSelectedIndex());
        if (hotel == null) {
            return;
        }
        boolean isOneRoom = hotel.getAvailableRoomCount(day) == 1;
        view.setHotelAvailabilityDataText(
                String.format("""
                        Reservations on day %d: %d
                        There %s %d room%s available.
                        """,
                        day,
                        hotel.getReservationCountOnDate(day, false),
                        isOneRoom ? "is" : "are",
                        hotel.getAvailableRoomCount(day),
                        isOneRoom ? "" : "s"));
    }
}

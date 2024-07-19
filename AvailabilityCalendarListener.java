import java.awt.List;
import java.awt.event.*;

import javax.swing.ListSelectionModel;
import javax.swing.event.*;


public class AvailabilityCalendarListener implements ListSelectionListener, MouseListener, KeyListener {
    ReservationSystem reservationSystem;
    TopView view;
    int receivedIndex;
    int row;
    int col;

    public AvailabilityCalendarListener(ReservationSystem reservationSystem, TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
    }

    public void handleCheckAvailability(int row, int col) {
        System.out.printf("Row %d and Col %d: \n", row, col);
        Hotel hotel = reservationSystem.getHotel(view.getHotelListSelectedIndex());
        if (hotel == null) {
            return;
        }
        int day = row * 7 + col % 7 + 1;
        if (day > 31) {
            return; // Block invalid input
        }
        System.out.printf("Selected day %d\n", day);

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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        row = view.getAvailabilityCalendarRowFromMouse(e.getPoint());
        col = view.getAvailabilityCalendarColFromMouse(e.getPoint());

        // Get the context of the event then do the appropriate action
        switch (view.getContext()) {
            case (TopView.VIEW_HOTEL_SCREEN):
                switch (view.getSubcontext()) {
                    case (TopView.CHECK_AVAILABILITY_SCREEN):
                        handleCheckAvailability(row, col);
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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        receivedIndex = lsm.getLeadSelectionIndex();
        System.out.println("received index " + receivedIndex);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("key press event\n");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_KP_LEFT:
                col = Math.max(col - 1, 0);
                handleCheckAvailability(row, col);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_KP_RIGHT:
                col = Math.min(col + 1, 6);
                handleCheckAvailability(row, col);
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_KP_UP:
                row = Math.max(row - 1, 0);
                handleCheckAvailability(row, col);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_KP_DOWN:
                row = Math.min(row + 1, 4);
                handleCheckAvailability(row, col);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

package src.controller.gui;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import src.model.ReservationSystem;
import src.view.gui.TopView;

public class TopMenuPaneListener implements ChangeListener {
    private ReservationSystem reservationSystem;
    private TopView view;

    public TopMenuPaneListener(ReservationSystem reservationSystem, TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        switch (view.getTabIndex()) {
            case TopView.VIEW_HOTEL_TAB:
                view.resetAvailabilityCalendarSelection();
                view.resetViewRoomListSelection();
                break;
            case TopView.MANAGE_HOTEL_TAB:
                break;
            case TopView.SIMULATE_BOOKING_TAB:
                break;
        }
    }
    
}

package src.controller.gui;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.panel.ViewHotelPanel;

/**
 * Represents an event listener used to refresh the {@link ViewHotelPanel}
 * inside {@link TopView}.
 */
public class TopMenuPaneListener implements ChangeListener {
    /** The view tied to this listener. */
    private TopView view;

    /**
     * Construct a new {@link TopMenuPaneListener} instance.
     * 
     * Unlike the other listeners, {@link TopMenuPaneListener} does not interact
     * with the underlying {@link ReservationSystem} model, and is simply used
     * to refresh the {@link ViewHotelPanel} inside {@link TopView}.
     * 
     * @param view The {@link TopView} tied to this listener
     */
    public TopMenuPaneListener(TopView view) {
        this.view = view;
    }

    /** {@inheritDoc} */
    @Override
    public void stateChanged(ChangeEvent e) {
        switch (view.getTabIndex()) {
            case TopView.VIEW_HOTEL_TAB:
                view.getViewHotelDelegate()
                        .clearAvailabilityCalendarSelection();
                view.getViewHotelDelegate().clearRoomSelectedIndex();
                view.getViewHotelDelegate().clearReservationListSelection();
                view.getViewHotelDelegate().setHotelAvailability("<p></p>");
                break;
            case TopView.MANAGE_HOTEL_TAB:
                view.getManageHotelDelegate()
                        .clearManagePricesCalendarSelection();
                view.getManageHotelDelegate().clearSelectedRoomIndex();
                view.getManageHotelDelegate().clearReservationListSelection();
                break;
            case TopView.SIMULATE_BOOKING_TAB:
                view.getSimulateBookingDelegate().resetBookingScreen();
                break;
        }
    }
}

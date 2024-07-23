package src.controller.gui;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.panel.ViewHotelPanel;

public class TopMenuPaneListener implements ChangeListener {
    private TopView view;

    /**
     * Construct a new {@link TopMenuPaneListener} instance.
     * 
     * Unlike the other listeners, {@link TopMenuPaneListener} does not interact
     * with the underlying {@link ReservationSystem} model, and is simply used
     * to refresh the {@link ViewHotelPanel} inside {@link TopView}.
     * 
     * @param view
     */
    public TopMenuPaneListener(TopView view) {
        this.view = view;
    }

    /** {@inheritDoc} */
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

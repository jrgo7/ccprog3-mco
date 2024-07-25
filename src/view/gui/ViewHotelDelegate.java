package src.view.gui;

import src.view.gui.panel.ViewHotelPanel;

import java.awt.*;
import java.util.ArrayList;

public class ViewHotelDelegate {
    private final ViewHotelPanel viewHotelPanel;

    public ViewHotelDelegate(ViewHotelPanel viewHotelPanel) {
        this.viewHotelPanel = viewHotelPanel;
    }

    /* Hotel data panel */

    public void setHotelData(String data) {
        this.viewHotelPanel.updateHotelData(data);
    }

    public void setRoomDataVisible(boolean visible) {
        this.viewHotelPanel.setRoomDataVisible(visible);
    }

    public void setHotelAvailability(String data) {
        this.viewHotelPanel.updateAvailability(data);
    }

    /* View rooms subpanel */

    public void setRoomData(String data, ArrayList<Integer> availableDates) {
        this.viewHotelPanel.updateRoomData(data, availableDates);
    }

    public void setSelectedRoomIndex(int index) {
        this.viewHotelPanel.setSelectedRoomIndex(index);
    }

    public void clearRoomSelectedIndex() {
        this.viewHotelPanel.resetRoomListSelection();
    }

    /* TODO */
    public int getAvailabilityCalendarRowFromMouse(Point point) {
        return this.viewHotelPanel.getCalendarRowAtPoint(point);
    }

    /* TODO */
    public int getAvailabilityCalendarColFromMouse(Point point) {
        return this.viewHotelPanel.getCalendarColAtPoint(point);
    }

    public void resetAvailabilityCalendarSelection() {
        this.viewHotelPanel.resetCalendarSelection();
    }

    /* View reservations subpanel */

    public void setReservationData(String data) {
        this.viewHotelPanel.updateReservationData(data);
    }

    public void setSelectedReservationIndex(int index) {
        this.viewHotelPanel.setSelectedReservationIndex(index);
    }

    public void setReservationDataVisible(boolean visible) {
        this.viewHotelPanel.setReservationDataVisibile(visible);
    }
}

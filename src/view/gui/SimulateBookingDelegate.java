package src.view.gui;

import src.controller.gui.SimulateBookingCalendarListener;
import src.controller.gui.SimulateBookingRoomListListener;
import src.view.gui.component.BookingCalendarRenderer;
import src.view.gui.panel.SimulateBookingPanel;

import java.awt.*;
import java.util.ArrayList;

public class SimulateBookingDelegate {
    private SimulateBookingPanel simulateBookingPanel;

    public SimulateBookingDelegate(SimulateBookingPanel simulateBookingPanel) {
        this.simulateBookingPanel = simulateBookingPanel;
    }

    // Simulate booking delegations
    public void setBookingCalendarAvailability(ArrayList<Integer> dates) {
        this.simulateBookingPanel.setCalendarAvailability(dates);
    }

    public void setBookingCalendarCheckIn(int date) {
        this.simulateBookingPanel.setCalendarCheckIn(date);
    }

    public void setBookingCalendarCheckOut(int date) {
        this.simulateBookingPanel.setCalendarCheckOut(date);
    }

    public String getBookingGuestName() {
        return this.simulateBookingPanel.getGuestNameFieldText();
    }

    public int getBookingRoomIndex() {
        return this.simulateBookingPanel.getRoomIndex();
    }

    public void resetBookingRoomListSelection() {
        this.simulateBookingPanel.clearRoomListSelection();
    }

    public String getBookingDiscountCode() {
        return this.simulateBookingPanel.getDiscountCodeFieldText();
    }

    public void updateSimulateBookingReservationPreview(String text) {
        this.simulateBookingPanel.setPreview(text);
    }

    public boolean getIsBookingCalendarFocused() {
        return this.simulateBookingPanel.getIsCalendarFocused();
    }

    public int getBookingCalendarRowFromMouse(Point point) {
        return this.simulateBookingPanel.getBookingCalendarRowFromMouse(point);
    }

    public int getBookingCalendarColFromMouse(Point point) {
        return this.simulateBookingPanel.getBookingCalendarColFromMouse(point);
    }

    public void resetBookingFields() {
        this.simulateBookingPanel.setGuestNameFieldText("");
        this.simulateBookingPanel.setDiscountCodeFieldText("");
        this.simulateBookingPanel.enableCheckInButton();
    }

    public void resetBookingCalendarSelection() {
        this.simulateBookingPanel.clearCalendarSelection();
    }

    public void setBookingDetailsVisible(boolean visible) {
        this.simulateBookingPanel.setDetailsVisible(visible);
    }

    public void resetBookingScreen() {
        this.resetBookingFields();
        this.resetBookingRoomListSelection();
        this.updateSimulateBookingReservationPreview(
                SimulateBookingPanel.RESERVATION_PREVIEW_INITIAL_TEXT);
        this.setBookingCalendarCheckIn(BookingCalendarRenderer.NONE);
        this.setBookingCalendarCheckOut(BookingCalendarRenderer.NONE);
    }
}

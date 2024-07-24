package src.view.gui.subpanel;

import java.awt.BorderLayout;
import java.awt.Point;

import src.view.gui.component.StyledHTMLPane;

import src.view.gui.component.StyledPanel;
import javax.swing.JTable;

import src.controller.gui.HotelAvailabilityCalendarListener;
import src.view.gui.component.RoomAvailabilityCalendar;
import src.view.gui.component.Calendar;

/** Represents the View Availability subpanel under View Hotel. */
public class ViewAvailabilityPanel extends StyledPanel {
    /** The {@link Calendar} showing available dates. */
    private RoomAvailabilityCalendar calendarComponent;

    /**
     * The {@link StyledHTMLPane} containing availability information for the
     * hotel.
     */
    private StyledHTMLPane availabilityComponent;

    /** Initializes the panel to contain availability information. */
    public ViewAvailabilityPanel() {
        this.setLayout(new BorderLayout());

        calendarComponent = new RoomAvailabilityCalendar();
        availabilityComponent = new StyledHTMLPane();

        this.add(calendarComponent, BorderLayout.NORTH);
        this.add(availabilityComponent, BorderLayout.CENTER);
    }

    /**
     * Sets the text contained in the availability data for the hotel.
     * 
     * @param text The availability data string to set
     */
    public void updateAvailability(String text) {
        this.availabilityComponent.setText(text);
    }

    /**
     * {@return the index of the row on where a given point lies on the
     * availability calendar}
     * 
     * @param point The point to capture in the table
     * @see JTable#rowAtPoint(Point)
     */
    public int getCalendarRowAtPoint(Point point) {
        return this.calendarComponent.rowAtPoint(point);
    }

    /**
     * {@return the index of the column on where a given point lies on the
     * availability calendar}
     * 
     * @param point The point to capture in the table
     * @see JTable#columnAtPoint(Point)
     */
    public int getCalendarColAtPoint(Point point) {
        return this.calendarComponent.columnAtPoint(point);
    }

    public void resetCalendarSelection() {
        this.calendarComponent.resetSelection();
    }

    /**
     * Calls {@link Calendar#setListener(HotelAvailabilityCalendarListener)}.
     * 
     * @param listener The listener to assign to the calendar
     */
    public void setListener(HotelAvailabilityCalendarListener listener) {
        this.calendarComponent.setListener(listener);
    }
}

package src.view.gui.subpanel;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import src.controller.gui.AvailabilityCalendarListener;
import src.view.gui.component.AvailabilityCalendar;
import src.view.gui.component.Calendar;

/** Represents the View Availability subpanel under View Hotel. */
public class ViewAvailabilityPanel extends JPanel {
    /** The {@link Calendar} showing available dates. */
    private AvailabilityCalendar calendarComponent;

    /**
     * The {@link JEditorPane} containing availability information for the
     * hotel.
     */
    private JEditorPane availabilityComponent;

    /** Initializes the panel to contain availability information. */
    public ViewAvailabilityPanel() {
        this.setLayout(new BorderLayout());

        calendarComponent = new AvailabilityCalendar();
        availabilityComponent = new JEditorPane("text/html", "");
        availabilityComponent.setEditable(false);

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
     * Calls {@link Calendar#setListener(AvailabilityCalendarListener)}.
     * 
     * @param listener The listener to assign to the calendar
     */
    public void setListener(AvailabilityCalendarListener listener) {
        this.calendarComponent.setListener(listener);
    }
}

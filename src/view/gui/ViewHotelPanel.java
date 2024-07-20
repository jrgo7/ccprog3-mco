package src.view.gui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JEditorPane;

import src.controller.gui.AvailabilityCalendarListener;
import src.model.Hotel;

/** Represents the View Hotel panel. */
public class ViewHotelPanel extends JPanel {
    /** The {@link JEditorPane} containing {@link Hotel} data. */
    private JEditorPane hotelDataComponent;

    /** A {@link JTabbedPane} containing subpanels. */
    private JTabbedPane subpanels;

    /** The View Availability subpanel tied to this panel. */
    private ViewAvailabilityPanel viewAvailabilitySubpanel;

    private ViewRoomPanel viewRoomSubPanel;

    public ViewHotelPanel() {
        this.setLayout(new BorderLayout());

        this.hotelDataComponent = new JEditorPane("text/html", "<h1 style=\"font-family: sans-serif\">Hotels</h1>");
        this.hotelDataComponent.setEditable(false);
        this.add(this.hotelDataComponent, BorderLayout.NORTH);

        this.subpanels = new JTabbedPane();

        this.viewAvailabilitySubpanel = new ViewAvailabilityPanel();
        this.subpanels.add("Availability", this.viewAvailabilitySubpanel);

        /* TODO: Subpanels for viewing rooms and reservations */

        this.viewRoomSubPanel = new ViewRoomPanel();
        this.subpanels.add("Rooms", viewRoomSubPanel);

        this.subpanels.add("Reservations", new JPanel());

        subpanels.setFont(TopView.ARIAL_PLAIN_FONT);
        this.add(subpanels, BorderLayout.CENTER);
    }

    /**
     * Updates the {@link JEditorPane} containing the hotel data.
     * 
     * @param text The hotel data string to use
     */
    public void updateHotelData(String text) {
        this.hotelDataComponent.setText(text);
    }

    /* Cover methods for the View Availability panel */

    /**
     * Sets the text contained in the availability data for the hotel.
     * 
     * @param text The availability data string to set
     * @see ViewAvailabilityPanel#updateAvailability(String)
     */
    public void updateAvailability(String text) {
        this.viewAvailabilitySubpanel.updateAvailability(text);
    }

    /**
     * {@return the index of the row on where a given point lies on the availability
     * calendar}
     * 
     * @param point The point to capture in the table
     * @see JTable#rowAtPoint(Point)
     * @see ViewAvailabilityPanel#getCalendarRowAtPoint(Point)
     */
    public int getCalendarRowAtPoint(Point point) {
        return this.viewAvailabilitySubpanel.getCalendarRowAtPoint(point);
    }

    /**
     * {@return the index of the column on where a given point lies on the
     * availability calendar}
     * 
     * @param point The point to capture in the table
     * @see JTable#columnAtPoint(Point)
     * @see ViewAvailabilityPanel#getCalendarRowAtPoint(Point)
     */
    public int getCalendarColAtPoint(Point point) {
        return this.viewAvailabilitySubpanel.getCalendarColAtPoint(point);
    }

    public void resetCalendarSelection() {
        this.viewAvailabilitySubpanel.resetCalendarSelection();
    }

    /**
     * Sets a listener for the calendar in the View Availability subpanel.
     * 
     * @param availabilityCalendarListener The listener to assign to the calendar
     * @see ViewAvailabilityPanel#setListener(AvailabilityCalendarListener)
     */
    public void setCalendarListener(AvailabilityCalendarListener availabilityCalendarListener) {
        this.viewAvailabilitySubpanel.setListener(availabilityCalendarListener);
    }

    /** {@return the index of the selected subpanel} */
    public int getSelectedSubpanelIndex() {
        return this.subpanels.getSelectedIndex();
    }

    public void updateRoomList(ArrayList<String> data) {
        this.viewRoomSubPanel.updateRoomList(data);
    }
}

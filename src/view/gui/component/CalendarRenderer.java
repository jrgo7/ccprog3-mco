package src.view.gui.component;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * A custom renderer for Calendar objects that allows for per-cell rendering
 * based on (un)available dates and currently-selected check-in and check-out
 * parameters.
 * TODO: Rename to BookingCalendarRenderer?
 * TODO: Abstract to RoomAvailabilityCalendarRenderer (and CalendarRenderer)?
 * TODO: A potential HotelAvailabilityCalendarRenderer that shades in (availableRooms/totalRooms) red?
 */
public class CalendarRenderer extends DefaultTableCellRenderer {

    private static final int NONE = -1;

    ArrayList<Integer> availableDates;
    private int checkIn = NONE;
    private int checkOut = NONE;

    public CalendarRenderer() {
        super();
    }

    public void setAvailableDates(ArrayList<Integer> availableDates) {
        this.availableDates = availableDates;
    }

    public void setCheckOut(int checkOut) {
        this.checkOut = checkOut;
    }

    public void setCheckIn(int checkIn) {
        this.checkIn = checkIn;
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(
            table, value, isSelected, hasFocus, row, column);
        int date = Calendar.toDate(row, column);

        super.setForeground(Color.BLACK);
        if (date > 31) {
            super.setBackground(Color.WHITE);
        } else if (availableDates != null && !availableDates.contains(date)) {
            super.setBackground(Color.PINK);
        } else if ((checkIn == NONE && checkOut == NONE && isSelected) ||
                (date >= checkIn && date <= checkOut)) {
            super.setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        } else if (date % 2 == 0) {
            super.setBackground(Color.decode("#e1e1e1"));
        } else {
            super.setBackground(Color.WHITE);
        }
        return this;
    }
}

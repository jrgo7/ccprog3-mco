package src.view.gui.component;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;

/**
 * An extended {@link CalendarRenderer} that adds highlighting rules with
 * respect to a list of available dates of a certain room, wherein
 * unavailable dates are colored red.
 */
public class RoomAvailabilityCalendarRenderer extends CalendarRenderer {
    protected ArrayList<Integer> availableDates;

    /** Initializes the renderer. */
    public RoomAvailabilityCalendarRenderer() {
        super();
    }

    /**
     * Set the available dates for this {@link RoomAvailabilityCalendarRenderer}
     * to reference when highlighting cells corresponding to unavailable dates.
     * 
     * @param availableDates an {@link ArrayList} of {@link Integer} values
     *                       representing available dates
     */
    public void setAvailableDates(ArrayList<Integer> availableDates) {
        this.availableDates = availableDates;
    }

    /** {@inheritDoc} */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
        int date = Calendar.toDate(row, column);

        if (availableDates != null && !availableDates.contains(date) && date <= 31) {
            super.setForeground(Color.WHITE);
            super.setBackground(ColorCollection.INVALID);
        }
        return this;
    }
}

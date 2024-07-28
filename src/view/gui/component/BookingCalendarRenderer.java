package src.view.gui.component;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;

/**
 * An extended {@link RoomAvailabilityCalendarRenderer} that adds more
 * highlighting rules with respect to a check-in and check-out date, wherein the
 * range encompassed therein are highlighted with a selection background.
 */
public class BookingCalendarRenderer extends RoomAvailabilityCalendarRenderer {
    public static final int NONE = -1;
    private int checkIn = NONE;
    private int checkOut = NONE;

    /** Initializes the renderer. */
    public BookingCalendarRenderer() {
        super();
    }

    /** Sets the check-out date. */
    public void setCheckOut(int checkOut) {
        this.checkOut = checkOut;
    }

    /** Sets the check-in date. */
    public void setCheckIn(int checkIn) {
        this.checkIn = checkIn;
    }

    /** {@inheritDoc} */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
        int date = Calendar.toDate(row, column);

        if (!isSelected && date <= 31 && checkIn != NONE && checkOut != NONE &&
                date >= checkIn && date <= checkOut) {
            if (!availableDates.contains(date)) {
                super.setForeground(Color.WHITE);
                super.setBackground(ColorCollection.SELECTION_INVALID);
            } else if (date == checkIn || date == checkOut) {
                super.setForeground(Color.WHITE);
                super.setBackground(ColorCollection.SELECTION_BORDER);
            } else {
                super.setBackground(ColorCollection.SELECTION_COMPLEMENT);
            }
        }

        return this;
    }
}

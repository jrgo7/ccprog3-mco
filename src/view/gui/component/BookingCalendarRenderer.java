package src.view.gui.component;

import java.awt.Component;
import javax.swing.JTable;

/**
 * An extended {@link AvailabilityCalendarRenderer} that adds more highlighting
 * rules with respect to a check-in and check-out date, wherein the range
 * encompassed therein are highlighted with a selection background.
 */
public class BookingCalendarRenderer extends AvailabilityCalendarRenderer {
    public static final int NONE = -1;
    private int checkIn = NONE;
    private int checkOut = NONE;

    public BookingCalendarRenderer() {
        super();
    }

    public void setCheckOut(int checkOut) {
        this.checkOut = checkOut;
    }

    public void setCheckIn(int checkIn) {
        this.checkIn = checkIn;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
        int date = Calendar.toDate(row, column);

        if ((checkIn == NONE && checkOut == NONE && isSelected) ||
                (date >= checkIn && date <= checkOut && date <= 31)) {
            super.setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        }
        return this;
    }
}

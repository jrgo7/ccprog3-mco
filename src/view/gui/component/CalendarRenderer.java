package src.view.gui.component;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * A custom renderer for Calendar objects that allows for per-cell rendering
 * based on (un)available dates and currently-selected check-in and check-out
 * parameters.
 * 
 * TODO: A potential HotelAvailabilityCalendarRenderer
 * TODO: that shades in (availableRooms/totalRooms) red?
 * 
 */
public class CalendarRenderer extends DefaultTableCellRenderer {

    public CalendarRenderer() {
        super();
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
        } else if (date % 2 == 0) {
            super.setBackground(Color.decode("#e1e1e1"));
        } else {
            super.setBackground(Color.WHITE);
        }
        return this;
    }
}

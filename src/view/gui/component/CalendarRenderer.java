package src.view.gui.component;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * A custom renderer for Calendar objects. On its own, it produces a 
 * checkerboard pattern, and sets invalid cells past day 31 with a white
 * background.
 * 
 * TODO: A potential HotelAvailabilityCalendarRenderer
 * TODO: that shades in (availableRooms/totalRooms) red?
 * 
 */
public class CalendarRenderer extends DefaultTableCellRenderer {

    /**
     * Creates a default instance of this class.
     */
    public CalendarRenderer() {
        super();
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

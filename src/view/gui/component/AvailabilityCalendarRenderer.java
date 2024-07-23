package src.view.gui.component;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;

public class AvailabilityCalendarRenderer extends CalendarRenderer {
    ArrayList<Integer> availableDates;

    public AvailabilityCalendarRenderer() {
        super();
    }

    public void setAvailableDates(ArrayList<Integer> availableDates) {
        this.availableDates = availableDates;
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

        System.out.printf("""
                date: %d
                availableDates != null: %s
                ^ and !availableDates.contains(date): %s
                date <= 31: %s
                """,
                date,
                availableDates != null,
                availableDates != null && !availableDates.contains(date),
                date <= 31);
        if (availableDates != null && !availableDates.contains(date) && date <= 31) {
            System.out.println("\tSetting " + date + " cell to pink.\n");
            super.setBackground(Color.PINK);
        }
        return this;
    }
}

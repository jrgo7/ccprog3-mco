package src.view.gui;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

public class Calendar extends JTable {
    public final static int MAX_COLS = 7;
    public final static int MAX_ROWS = 5;
    
    public Calendar() {
        super(MAX_ROWS, MAX_COLS);
        this.setCellSelectionEnabled(true);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableModel model = getModel();
        for (int i = 1; i <= 31; i++) {
            model.setValueAt(i, (i - 1) / MAX_COLS, (i - 1) % MAX_COLS);
        }
        this.setFont(TopView.ARIAL_PLAIN_FONT);
        this.setRowHeight(28);
    }

    public static int toDay(int row, int col) {
        if (row == -1 || col == -1) {
            return -1;
        }
        return row * 7 + col % 7 + 1;
    }

    // Prevents all cells from being edited
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    // Mark the cell corresponding to a certain `day` as unavailable,
    // i.e., set its backgronud color to red
    public void setUnavailable(int day) {

    }

    // Mark the cell corresponding to a certain `day` as available,
    // i.e., set its background color to the default
    public void setAvailable(int day) {

    }

    public void colorByAvailability(int[] availableDates) {
        // Set everything as unavailable then set the `availableDates`
    }
}
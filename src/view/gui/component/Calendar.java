package src.view.gui.component;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

import src.view.gui.TopView;

public class Calendar extends JTable {
    public final static int MAX_COLS = 7;
    public final static int MAX_ROWS = 5;

    public Calendar() {
        super(MAX_ROWS, MAX_COLS);
        this.setCellSelectionEnabled(true);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableModel model = this.getModel();
        for (int i = 1; i <= 31; i++) {
            model.setValueAt(i, (i - 1) / MAX_COLS, (i - 1) % MAX_COLS);
        }
        this.setFont(TopView.ARIAL_PLAIN_FONT);
        this.setRowHeight(64);
    }

    public static int toDate(int row, int col) {
        if (row == -1 || col == -1) {
            return -1;
        }
        return row * 7 + col % 7 + 1;
    }

    public int getRowFromDate(int date) {
        return (date - 1) / MAX_COLS;
    }

    public int getColFromDate(int date) {
        return (date - 1) % MAX_COLS;
    }

    public void setCalendarText(int date, String text) {
        System.out.printf("Setting row %d, col %d with %s\n", getRowFromDate(date), getColFromDate(date), text);
        this.getModel().setValueAt(text, getRowFromDate(date), getColFromDate(date));
    }

    public void setCalendarText(int row, int col, String text) {
        this.getModel().setValueAt(text, row, col);
    }

    // "White-out" unavailable dates, showing only the available dates
    public void setAvailability(ArrayList<Integer> availableDates) {
        for (int date = 1; date <= 31; date++) {
            this.setCalendarText(
                    date,
                    (availableDates.contains(date)) ? String.valueOf(date) : "");
        }
    }

    // Prevents all cells from being edited
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public void selectDate(int date) {
        int row = getRowFromDate(date);
        int col = getColFromDate(date);
        this.setRowSelectionInterval(row, row);
        this.setColumnSelectionInterval(col, col);
        this.requestFocus();
    }

    public void resetSelection() {
        this.removeRowSelectionInterval(Calendar.MAX_ROWS - 1, 0);
        this.removeColumnSelectionInterval(Calendar.MAX_COLS - 1, 0);
    }
}
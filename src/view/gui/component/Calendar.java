package src.view.gui.component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

import org.w3c.dom.events.MouseEvent;

import src.controller.gui.CalendarListener;
import src.view.gui.TopView;

/**
 * A custom component representing a set of dates.
 */
public class Calendar extends JTable {
    /** Columns in {@link Calendar} objects */
    public final static int MAX_COLS = 7;

    /** Rows in all {@link Calendar} objects */
    public final static int MAX_ROWS = 5;

    /** Associated {@link CalendarRenderer} object to customize styling */
    protected CalendarRenderer renderer;

    /**
     * Initialize this component with the default renderer and values.
     */
    public Calendar() {
        super(MAX_ROWS, MAX_COLS);

        // Instantiate a renderer, set it as the default for all classes.
        this.renderer = new CalendarRenderer();
        this.setDefaultRenderer(Object.class, renderer);

        this.setCellSelectionEnabled(true);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TableModel model = this.getModel();
        for (int i = 1; i <= 31; i++) {
            model.setValueAt(i, (i - 1) / MAX_COLS, (i - 1) % MAX_COLS);
        }

        this.setFont(TopView.ARIAL_PLAIN_FONT);
        this.setRowHeight(64);
    }

    /**
     * A static method to return the date represented by a {@code (row, col)}
     * pair in a default {@link Calendar} object.
     * 
     * @param row
     * @param col
     * @return aforementioned date
     */
    public static int toDate(int row, int col) {
        if (row == -1 || col == -1) {
            return -1;
        }
        return row * 7 + col % 7 + 1;
    }

    /**
     * Get the row in a object that contains the specified date.
     * 
     * @param date
     * @return aforementioned row index
     */
    public int getRowFromDate(int date) {
        return (date - 1) / MAX_COLS;
    }

    /**
     * Get the column in this object that contains the specified date.
     * 
     * @param date
     * @return aforementioned column index
     */
    public int getColFromDate(int date) {
        return (date - 1) % MAX_COLS;
    }

    /**
     * Place text on a cell corresponding to a certain date in this object.
     * This wraps {@link #setCalendarText(row, col, text)}.
     * 
     * @param date
     * @param text
     */
    public void setCalendarText(int date, String text) {
        this.setCalendarText(getRowFromDate(date), getColFromDate(date), text);
    }

    /**
     * Place text on a cell in the specified location.
     * 
     * @param row
     * @param col
     * @param text
     */
    public void setCalendarText(int row, int col, String text) {
        this.getModel().setValueAt(text, row, col);
    }

    /**
     * This method has been overridden to prevent any cells from being editable.
     * 
     * @param row
     * @param column
     * @return false
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    /**
     * Select a specified date in this object.
     * 
     * @param date
     */
    public void selectDate(int date) {
        int row = getRowFromDate(date);
        int col = getColFromDate(date);
        this.setRowSelectionInterval(row, row);
        this.setColumnSelectionInterval(col, col);
        this.requestFocus();
    }

    /**
     * Remove any selections made in this object.
     */
    public void resetSelection() {
        this.removeRowSelectionInterval(Calendar.MAX_ROWS - 1, 0);
        this.removeColumnSelectionInterval(Calendar.MAX_COLS - 1, 0);
    }

    /**
     * Add a {@link CalendarListener} instance as a listener to receive
     * associated events defined therein.
     * 
     * @param listener The listener to assign to the calendar
     */
    public void setListener(CalendarListener listener) {
        this.addMouseListener(listener);
        this.addKeyListener(listener);
        this.addMouseMotionListener(listener);
    }
}
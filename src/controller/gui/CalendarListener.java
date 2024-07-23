package src.controller.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JTable;

import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.component.Calendar;

/** An abstract listener for the {@link Calendar} class and its descendants. */
public abstract class CalendarListener
        implements MouseListener, MouseMotionListener, KeyListener {
    /** The {@link ReservationSystem} tied to the listener. */
    protected ReservationSystem reservationSystem;

    /** The {@link TopView} used to communicate with the GUI. */
    protected TopView view;

    /** The last selected row as heard by this instance. */
    private int row;

    /** The last selected column as heard by this instance. */
    private int col;

    /** Initialize this listener. */
    public CalendarListener(ReservationSystem reservationSystem, TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
    }

    /**
     * Set the r
     * 
     * @param row
     */
    protected void setRow(int row) {
        this.row = row;
    }

    protected void setCol(int col) {
        this.col = col;
    }

    protected int getRow() {
        return row;
    }

    protected int getCol() {
        return col;
    }

    protected int getDate() {
        return Calendar.toDate(row, col);
    }

    /**
     * Set the row and column based on a mouse event. Standard implementation
     * for this method is to call {@link JTable#rowAtPoint()} and
     * {@link JTable#columnAtPoint()} (perhaps by delegation), passing in
     * {@link MouseEvent#getPoint()}, and setting appropriate attributes via
     * {@link #setRow()} and {@link #CalendarListener.setCol()}.
     * 
     * @param e
     */
    protected abstract void setRowAndCol(MouseEvent e);

    /**
     * Handle selecting a cell in (`row`, `col`) of the Calendar
     * via a keyboard event (arrow key selection)
     * 
     * @param row
     * @param col
     */
    protected abstract void handleSelected(int row, int col);

    /**
     * Handle selecting a cell in (`row`, `col`) of the Calendar
     * via a mouse click.
     * 
     * @param row
     * @param col
     */
    protected abstract void handleClicked(int row, int col);

    /**
     * Handle selecting a cell in (`row`, `col`) of the Calendar
     * via a mouse release.
     * 
     * @param row
     * @param col
     */
    protected abstract void handleReleased(int row, int col);

    /**
     * Handle selecting a cell in (`row`, `col`) of the Calendar
     * via a mouse drag.
     * 
     * @param row
     * @param col
     */
    protected abstract void handleDragged(int row, int col);

    /**
     * Handle selecting a cell in (`row`, `col`) of the Calendar
     * via a mouse move.
     * 
     * @param row
     * @param col
     */
    protected abstract void handleMoved(int row, int col);

    /**
     * Handle what happens when a mouse drags from the Calendar
     * to outside it.
     * 
     * @param row
     * @param col
     */
    protected abstract void handleReleasedOutsideComponent();

    /**
     * When a key is pressed, adjust the current selection position.
     * Enter and Tab keys are ignored for simplicity.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER ||
                e.getKeyCode() == KeyEvent.VK_TAB) {
            e.consume(); // prevent ENTER, TAB keys
        } else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_KP_LEFT:
                    col = Math.max(col - 1, 0);
                    handleSelected(row, col);
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_KP_RIGHT:
                    col = Math.min(col + 1, Calendar.MAX_COLS - 1);
                    handleSelected(row, col);
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_KP_UP:
                    row = Math.max(row - 1, 0);
                    handleSelected(row, col);
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_KP_DOWN:
                    row = Math.min(row + 1, Calendar.MAX_ROWS - 1);
                    handleSelected(row, col);
                    break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setRowAndCol(e);
        handleClicked(row, col);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setRowAndCol(e);
        handleClicked(row, col);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setRowAndCol(e);
        handleReleased(row, col);
        if (row == -1 || col == -1) {
            row = 0;
            col = 0;
            handleReleasedOutsideComponent();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setRowAndCol(e);
        handleDragged(row, col);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

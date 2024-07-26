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
     * Set the row to handle for a certain event related to this object.
     * 
     * @param row The index of the row to handle
     */
    protected void setRow(int row) {
        this.row = row;
    }

    /**
     * Set the column to handle for a certain event related to this object.
     * 
     * @param col The index of the column to handle
     */
    protected void setCol(int col) {
        this.col = col;
    }

    /**
     * Get the row last selected by the user.
     * 
     * @return The index of the aforementioned
     */
    protected int getRow() {
        return row;
    }

    /**
     * Get the column last selected by the user.
     * 
     * @return The index of the aforementioned
     */
    protected int getCol() {
        return col;
    }

    /**
     * Get the date associated with the last selected row and column
     * 
     * @return The aformentioned
     */
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
     * @param e the mouse event
     */
    protected abstract void setRowAndCol(MouseEvent e);

    /**
     * Handle selecting a cell in {@code (row, col)} of the {@link Calendar} via
     * a keyboard event (arrow key selection)
     * 
     * @param row the {@link Calendar} row associated with the current action
     * @param col the {@link Calendar} column associated with the current action
     */
    protected abstract void handleSelected(int row, int col);

    /**
     * Handle selecting a cell in {@code (row, col)} of the {@link Calendar} via
     * a mouse click.
     * 
     * @param row the {@link Calendar} row associated with the current action
     * @param col the {@link Calendar} column associated with the current action
     */
    protected abstract void handleClicked(int row, int col);

    /**
     * Handle selecting a cell in {@code (row, col)} of the {@link Calendar} via
     * a mouse release
     * 
     * @param row the {@link Calendar} row associated with the current action
     * @param col the {@link Calendar} column associated with the current action
     */
    protected abstract void handleReleased(int row, int col);

    /**
     * Handle selecting a cell in {@code (row, col)} of the {@link Calendar} via
     * a mouse drag.
     * 
     * @param row the {@link Calendar} row associated with the current action
     * @param col the {@link Calendar} column associated with the current action
     */
    protected abstract void handleDragged(int row, int col);

    /**
     * Handle selecting a cell in {@code (row, col)} of the {@link Calendar} via
     * a mouse move.
     * 
     * @param row the {@link Calendar} row associated with the current action
     * @param col the {@link Calendar} column associated with the current action
     */
    protected abstract void handleMoved(int row, int col);

    /**
     * Handle what happens when a mouse drags from the Calendar to outside it.
     * 
     * @param row the {@link Calendar} row associated with the current action
     * @param col the {@link Calendar} column associated with the current action
     */
    protected abstract void handleReleasedOutsideComponent();

    /**
     * {@inheritDoc} When a key is pressed, adjust the current selection
     * position. Enter and tab keys are ignored for simplicity.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER ||
                e.getKeyCode() == KeyEvent.VK_TAB) {
            /* Prevent the use of ENTER and TAB */
            e.consume();
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

    /** No behavior is defined for this implementation. {@inheritDoc} */
    @Override
    public void keyTyped(KeyEvent e) {
        /* Implementation left blank */
    }

    /** No behavior is defined for this implementation. {@inheritDoc} */
    @Override
    public void keyReleased(KeyEvent e) {
        /* Implementation left blank */
    }

    /** {@inheritDoc} Sets the calendar selection to where the mouse clicked. */
    @Override
    public void mouseClicked(MouseEvent e) {
        setRowAndCol(e);
        handleClicked(row, col);
    }

    /** {@inheritDoc} Treated as if the mouse was clicked. */
    @Override
    public void mousePressed(MouseEvent e) {
        this.mouseClicked(e);
    }

    /** {@inheritDoc} Removes the calendar selection. */
    @Override
    public void mouseReleased(MouseEvent e) {
        setRowAndCol(e);
        handleReleased(row, col);
        /*
         * Ensure the selection cannot be out of bounds by forcing it to the
         * first index
         */
        if (row == -1 || col == -1) {
            row = 0;
            col = 0;
            handleReleasedOutsideComponent();
        }
    }

    /** No behavior is defined for this implementation. {@inheritDoc} */
    @Override
    public void mouseEntered(MouseEvent e) {
        /* Implementation left blank */
    }

    /** No behavior is defined for this implementation. {@inheritDoc} */
    @Override
    public void mouseExited(MouseEvent e) {
        /* Implementation left blank */
    }

    /** {@inheritDoc} Treated as if the mouse was clicked. */
    @Override
    public void mouseDragged(MouseEvent e) {
        this.mouseClicked(e);
    }

    /** No behavior is defined for this implementation. {@inheritDoc} */
    @Override
    public void mouseMoved(MouseEvent e) {
        /* Implementation left blank */
    }
}

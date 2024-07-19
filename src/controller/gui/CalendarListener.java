package src.controller.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import src.model.ReservationSystem;
import src.view.gui.Calendar;
import src.view.gui.TopView;

abstract class CalendarListener implements ListSelectionListener, MouseListener, MouseMotionListener, KeyListener {
    ReservationSystem reservationSystem;
    TopView view;
    int receivedIndex;
    int row;
    int col;

    public CalendarListener(ReservationSystem reservationSystem, TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
    }

    // Selected from a keyboard input
    protected abstract void handleSelected(int row, int col);

    // Selected from a keyboard input and then pressed enter
    protected abstract void handleEntered(int row, int col);

    // Seleected from a mouse input
    protected abstract void handleClicked(int row, int col);

    protected abstract void handleExited(int row, int col);

    protected abstract void handleReleased(int row, int col);
    
    protected abstract void handleDragged(int row, int col);

    protected abstract void handleMoved(int row, int col);

    protected abstract void handleReleasedOutsideComponent();

    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        receivedIndex = lsm.getLeadSelectionIndex();
    }

    @Override
    public void keyPressed(KeyEvent e) {
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
            case KeyEvent.VK_ENTER:
                handleEntered(row, col);
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

    }

    @Override
    public void mousePressed(MouseEvent e) {
        row = view.getAvailabilityCalendarRowFromMouse(e.getPoint());
        col = view.getAvailabilityCalendarColFromMouse(e.getPoint());
        handleClicked(row, col);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        row = view.getAvailabilityCalendarRowFromMouse(e.getPoint());
        col = view.getAvailabilityCalendarColFromMouse(e.getPoint());
        handleReleased(row, col);
        if (row == -1 || col == -1) {
            row = 0;
            col = 0;
            handleReleasedOutsideComponent();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        row = view.getAvailabilityCalendarRowFromMouse(e.getPoint());
        col = view.getAvailabilityCalendarColFromMouse(e.getPoint());
        handleEntered(row, col);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        row = view.getAvailabilityCalendarRowFromMouse(e.getPoint());
        col = view.getAvailabilityCalendarColFromMouse(e.getPoint());
        handleExited(row, col);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        row = view.getAvailabilityCalendarRowFromMouse(e.getPoint());
        col = view.getAvailabilityCalendarColFromMouse(e.getPoint());
        handleDragged(row, col);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        row = view.getAvailabilityCalendarRowFromMouse(e.getPoint());
        col = view.getAvailabilityCalendarColFromMouse(e.getPoint());
        handleMoved(row, col);
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}

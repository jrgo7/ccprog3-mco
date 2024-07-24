package src.view.gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import src.controller.gui.SimulateBookingCalendarListener;
import src.controller.gui.SimulateBookingRoomListListener;
import src.view.gui.component.BookingCalendar;
import src.view.gui.component.BookingCalendarRenderer;
import src.view.gui.component.Calendar;
import src.view.gui.component.RoomListPanel;
import src.view.gui.component.StyledButton;
import src.view.gui.component.StyledHTMLPane;
import src.view.gui.component.StyledLabel;
import src.view.gui.component.StyledPanel;
import src.view.gui.component.StyledRadioButton;
import src.view.gui.component.StyledScrollPane;

public class SimulateBookingPanel extends StyledPanel {
    private JTextField guestNameField;
    private JTextField discountCodeField;
    private RoomListPanel roomListPanel;
    private BookingCalendar bookingCalendar;
    private StyledHTMLPane reservationPreview;
    private StyledButton bookButton;
    private StyledButton resetButton;
    private ButtonGroup checkInOutGroup;
    private StyledRadioButton checkInButton;
    private StyledRadioButton checkOutButton;
    private StyledPanel detailsPanel;

    static public final String RESERVATION_PREVIEW_INITIAL_TEXT = """
            Input your booking details to update this preview.""";

    public SimulateBookingPanel() {
        this.setLayout(new BorderLayout());

        roomListPanel = new RoomListPanel(200, false);
        this.add(roomListPanel, BorderLayout.WEST);

        detailsPanel = new StyledPanel();
        detailsPanel.setLayout(
                new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

        StyledPanel textFieldPanel = new StyledPanel();
        textFieldPanel.setLayout(new GridLayout(2, 2));

        StyledLabel guestNameLabel = new StyledLabel("Guest name:");
        textFieldPanel.add(guestNameLabel);

        guestNameField = new JTextField();
        textFieldPanel.add(guestNameField);

        StyledLabel discountCodeLabel = new StyledLabel("Discount code:");
        textFieldPanel.add(discountCodeLabel);

        discountCodeField = new JTextField();
        textFieldPanel.add(discountCodeField);
        textFieldPanel.setSize(WIDTH, HEIGHT);
        detailsPanel.add(textFieldPanel);

        detailsPanel.add(new JSeparator());

        StyledPanel checkInOutPanel = new StyledPanel();
        checkInOutPanel.setLayout(
                new BoxLayout(checkInOutPanel, BoxLayout.X_AXIS));
        checkInOutGroup = new ButtonGroup();

        checkInOutPanel.add(Box.createHorizontalGlue());

        checkInButton = new StyledRadioButton("Set check-in date");
        checkInButton.setSelected(true);
        checkInOutPanel.add(checkInButton);
        checkInOutGroup.add(checkInButton);

        checkInOutPanel.add(Box.createHorizontalGlue());

        checkOutButton = new StyledRadioButton("Set check-out date");
        checkInOutPanel.add(checkOutButton);
        checkInOutGroup.add(checkOutButton);

        checkInOutPanel.add(Box.createHorizontalGlue());
        detailsPanel.add(checkInOutPanel);

        bookingCalendar = new BookingCalendar();
        detailsPanel.add(bookingCalendar);

        StyledPanel reservationPreviewPanel = new StyledPanel();
        reservationPreviewPanel.setLayout(new BorderLayout());
        reservationPreview = new StyledHTMLPane(RESERVATION_PREVIEW_INITIAL_TEXT);
        StyledScrollPane reservationScrollPane = new StyledScrollPane(
                reservationPreview,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        reservationPreviewPanel.add(reservationScrollPane, BorderLayout.CENTER);
        detailsPanel.add(reservationPreviewPanel);

        StyledPanel southPanel = new StyledPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));

        bookButton = new StyledButton("Book");
        southPanel.add(bookButton);

        resetButton = new StyledButton("Reset");
        southPanel.add(resetButton);

        detailsPanel.add(southPanel);

        this.add(detailsPanel, BorderLayout.CENTER);

        detailsPanel.setVisible(false);
    }

    public void updateRoomList(ArrayList<String> data) {
        this.roomListPanel.setList(data);
    }

    public void setCalendarAvailability(ArrayList<Integer> dates) {
        this.bookingCalendar.setAvailability(dates);
    }

    public void setCalendarCheckIn(int date) {
        if (date != BookingCalendarRenderer.NONE) {
            this.bookingCalendar.setCalendarText(
                    date, String.format("%d: Start", date));
        }
        this.bookingCalendar.setCalendarCheckIn(date);
    }

    public void setCalendarCheckOut(int date) {
        if (date != BookingCalendarRenderer.NONE) {
            this.bookingCalendar.setCalendarText(
                    date, String.format("%d: End", date));
        }
        this.bookingCalendar.setCalendarCheckOut(date);
    }

    public String getGuestNameFieldText() {
        return this.guestNameField.getText();
    }

    public String getDiscountCodeFieldText() {
        return this.discountCodeField.getText();
    }

    public boolean getIsCalendarFocused() {
        return this.bookingCalendar.isFocusOwner();
    }

    public int getBookingCalendarDay() {
        return Calendar.toDate(
                this.bookingCalendar.getSelectedRow(),
                this.bookingCalendar.getSelectedColumn());
    }

    public int getRoomIndex() {
        return this.roomListPanel.getSelectedIndex();
    }

    public void setPreview(String text) {
        this.reservationPreview.setText(text);
    }

    public int getBookingCalendarRowFromMouse(Point point) {
        return this.bookingCalendar.rowAtPoint(point);
    }

    public int getBookingCalendarColFromMouse(Point point) {
        return this.bookingCalendar.columnAtPoint(point);
    }

    public void resetRoomListSelection() {
        this.roomListPanel.clearSelection();
        this.detailsPanel.setVisible(false);
    }

    public void setDiscountCodeFieldText(String discountCode) {
        this.discountCodeField.setText(discountCode);
    }

    public void setGuestNameFieldText(String guestName) {
        this.guestNameField.setText(guestName);
    }

    public void setDetailsVisible(boolean visible) {
        this.detailsPanel.setVisible(visible);
    }

    public void enableCheckInButton() {
        this.checkInButton.setSelected(true);
    }

    public void resetCalendarSelection() {
        this.bookingCalendar.resetSelection();
    }

    public void setListeners(
            SimulateBookingRoomListListener simulateBookingRoomListListener,
            SimulateBookingCalendarListener bookingCalendarListener) {

        this.roomListPanel.setListener(simulateBookingRoomListListener);

        this.guestNameField.addKeyListener(bookingCalendarListener);
        this.discountCodeField.addKeyListener(bookingCalendarListener);

        this.bookingCalendar.setListener(bookingCalendarListener);

        this.checkInButton.addActionListener(bookingCalendarListener);
        this.checkOutButton.addActionListener(bookingCalendarListener);

        this.bookButton.addActionListener(bookingCalendarListener);
        this.resetButton.addActionListener(bookingCalendarListener);
    }

}

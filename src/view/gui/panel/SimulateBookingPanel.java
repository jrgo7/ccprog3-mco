package src.view.gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import src.controller.gui.BookingCalendarListener;
import src.controller.gui.SimulateBookingRoomListListener;
import src.view.gui.TopView;
import src.view.gui.component.Calendar;
import src.view.gui.component.RoomListPanel;

public class SimulateBookingPanel extends JPanel {
    private JTextField guestNameField;
    private JTextField discountCodeField;
    private RoomListPanel roomListPanel;
    private Calendar bookingCalendar;
    private JEditorPane reservationPreview;
    private JButton bookButton;
    private JButton previewButton; // TODO Testing
    private ButtonGroup checkInOutGroup;
    private JRadioButton checkInButton;
    private JRadioButton checkOutButton;
    private JPanel calendarPanel;

    public SimulateBookingPanel() {
        this.setLayout(new BorderLayout());

        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(
                new BoxLayout(centerPanel, BoxLayout.X_AXIS));
                
        roomListPanel = new RoomListPanel(200, false);
        centerPanel.add(roomListPanel);

        calendarPanel = new JPanel();
        calendarPanel.setLayout(
                new BoxLayout(calendarPanel, BoxLayout.Y_AXIS));

        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridLayout(1, 4));

        JLabel guestNameLabel = new JLabel("Guest name:");
        guestNameLabel.setFont(TopView.ARIAL_PLAIN_FONT);
        textFieldPanel.add(guestNameLabel);

        guestNameField = new JTextField();
        textFieldPanel.add(guestNameField);

        JLabel discountCodeLabel = new JLabel("Discount code:");
        discountCodeLabel.setFont(TopView.ARIAL_PLAIN_FONT);
        textFieldPanel.add(discountCodeLabel);

        discountCodeField = new JTextField();
        textFieldPanel.add(discountCodeField);

        calendarPanel.add(textFieldPanel, BorderLayout.NORTH);


        JPanel checkInOutPanel = new JPanel();
        checkInOutPanel.setLayout(
                new BoxLayout(checkInOutPanel, BoxLayout.X_AXIS));

        checkInOutGroup = new ButtonGroup();

        checkInButton = new JRadioButton("Set check-in date");
        checkInButton.setFont(TopView.ARIAL_PLAIN_FONT);
        checkInButton.setEnabled(true);
        checkInOutPanel.add(checkInButton);
        checkInOutGroup.add(checkInButton);

        checkOutButton = new JRadioButton("Set check-out date");
        checkOutButton.setFont(TopView.ARIAL_PLAIN_FONT);
        checkInOutPanel.add(checkOutButton);
        checkInOutGroup.add(checkOutButton);

        calendarPanel.add(checkInOutPanel);

        bookingCalendar = new Calendar();
        calendarPanel.add(bookingCalendar);

        reservationPreview = new JEditorPane(
                "text/html",
                "<h3 style=\"font-family: sans-serif\">Reservation data</h3>");
        calendarPanel.add(reservationPreview);


        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));

        bookButton = new JButton("Book");
        southPanel.add(bookButton);

        previewButton = new JButton("Reset");
        southPanel.add(previewButton);

        calendarPanel.add(southPanel);

        centerPanel.add(calendarPanel);

        this.add(centerPanel, BorderLayout.CENTER);

        calendarPanel.setVisible(false);
    }

    public void updateRoomList(ArrayList<String> data) {
        this.roomListPanel.setList(data);
    }

    public void setCalendarAvailability(ArrayList<Integer> dates) {
        this.bookingCalendar.setAvailability(dates);
    }

    public void setCalendarCheckIn(int date) {
        if (date >= 1 && date <= 31) {
            this.bookingCalendar.setCalendarText(
                date, String.format("%d: START", date));
        }
    }

    public void setCalendarCheckOut(int date) {
        if (date >= 1 && date <= 31) {
            this.bookingCalendar.setCalendarText(
                date, String.format("%d: END", date));
        }
    }

    public String getGuestNameFieldText() {
        return this.guestNameField.getText();
    }

    public String getDiscountCodeFieldText() {
        return this.discountCodeField.getText();
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
        this.roomListPanel.setSelectedIndex(-1);
    }

	public void setDiscountCodeFieldText(String discountCode) {
        this.discountCodeField.setText(discountCode);
    }

	public void setGuestNameFieldText(String guestName) {
        this.guestNameField.setText(guestName);
	}

    public void setDetailsVisible(boolean visible) {
        this.calendarPanel.setVisible(visible);
    }

    public void setListeners(
            SimulateBookingRoomListListener simulateBookingRoomListListener,
            BookingCalendarListener bookingCalendarListener) {

        this.roomListPanel.setListener(simulateBookingRoomListListener);

        this.guestNameField.addKeyListener(bookingCalendarListener);
        this.discountCodeField.addKeyListener(bookingCalendarListener);

        // Following methods could be defined as Calendar.setListener() method
        this.bookingCalendar.addMouseListener(bookingCalendarListener);
        this.bookingCalendar.addKeyListener(bookingCalendarListener);
        this.bookingCalendar.addMouseMotionListener(bookingCalendarListener);
        // --

        this.checkInButton.addActionListener(bookingCalendarListener);
        this.checkOutButton.addActionListener(bookingCalendarListener);

        this.bookButton.addActionListener(bookingCalendarListener);
        this.previewButton.addActionListener(bookingCalendarListener);
    }


}

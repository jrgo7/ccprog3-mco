package src.view.gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import src.controller.gui.BookingCalendarListener;
import src.controller.gui.SimulateBookingRoomListListener;
import src.view.gui.TopView;
import src.view.gui.component.BookingCalendar;
import src.view.gui.component.Calendar;
import src.view.gui.component.RoomListPanel;

public class SimulateBookingPanel extends JPanel {
    private JTextField guestNameField;
    private JTextField discountCodeField;
    private RoomListPanel roomListPanel;
    private BookingCalendar bookingCalendar;
    private JEditorPane reservationPreview;
    private JButton bookButton;
    private JButton resetButton;
    private ButtonGroup checkInOutGroup;
    private JRadioButton checkInButton;
    private JRadioButton checkOutButton;
    private JPanel detailsPanel;

    static public final String RESERVATION_PREVIEW_INITIAL_TEXT = """
            <p style=\"font-family: sans-serif\">
            As you input your booking details, this panel will update
            automatically.
            </p>""";

    public SimulateBookingPanel() {
        this.setLayout(new BorderLayout());

        roomListPanel = new RoomListPanel(200, false);
        this.add(roomListPanel, BorderLayout.WEST);

        detailsPanel = new JPanel();
        detailsPanel.setLayout(
                new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

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
        textFieldPanel.setSize(WIDTH, HEIGHT);
        detailsPanel.add(textFieldPanel);

        detailsPanel.add(Box.createVerticalStrut(8));
        detailsPanel.add(new JSeparator());
        detailsPanel.add(Box.createVerticalStrut(8));

        JPanel checkInOutPanel = new JPanel();
        checkInOutPanel.setLayout(
                new BoxLayout(checkInOutPanel, BoxLayout.X_AXIS));
        checkInOutGroup = new ButtonGroup();
        checkInOutPanel.add(Box.createHorizontalGlue());
        checkInButton = new JRadioButton("Set check-in date");
        checkInButton.setFont(TopView.ARIAL_PLAIN_FONT);
        checkInButton.setSelected(true);
        checkInOutPanel.add(checkInButton);
        checkInOutGroup.add(checkInButton);
        checkInOutPanel.add(Box.createHorizontalGlue());
        checkOutButton = new JRadioButton("Set check-out date");
        checkOutButton.setFont(TopView.ARIAL_PLAIN_FONT);
        checkInOutPanel.add(checkOutButton);
        checkInOutGroup.add(checkOutButton);
        checkInOutPanel.add(Box.createHorizontalGlue());
        detailsPanel.add(checkInOutPanel);

        bookingCalendar = new BookingCalendar();
        detailsPanel.add(bookingCalendar);

        JPanel reservationPreviewPanel = new JPanel();
        reservationPreviewPanel.setLayout(new BorderLayout());
        reservationPreview = new JEditorPane(
                "text/html", RESERVATION_PREVIEW_INITIAL_TEXT);
        reservationPreview.setEditable(false);
        JScrollPane reservationScrollPane = new JScrollPane(
                reservationPreview,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        reservationPreviewPanel.add(reservationScrollPane, BorderLayout.CENTER);
        detailsPanel.add(reservationPreviewPanel);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));

        bookButton = new JButton("Book");
        bookButton.setFont(TopView.ARIAL_PLAIN_FONT);
        southPanel.add(bookButton);

        resetButton = new JButton("Reset");
        resetButton.setFont(TopView.ARIAL_PLAIN_FONT);
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
        if (date >= 1 && date <= 31) {
            this.bookingCalendar.setCalendarText(
                    date, String.format("%d: START", date));
        }
        this.bookingCalendar.setCalendarCheckIn(date);
    }

    public void setCalendarCheckOut(int date) {
        if (date >= 1 && date <= 31) {
            this.bookingCalendar.setCalendarText(
                    date, String.format("%d: END", date));
        }
        this.bookingCalendar.setCalendarCheckOut(date);
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
            BookingCalendarListener bookingCalendarListener) {

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

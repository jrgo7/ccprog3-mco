package src.view.gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import src.view.gui.component.StyledTextField;

import src.controller.gui.SimulateBookingCalendarListener;
import src.controller.gui.SimulateBookingRoomListListener;
import src.model.Room;
import src.view.gui.component.BookingCalendar;
import src.view.gui.component.BookingCalendarRenderer;
import src.view.gui.component.GUIFactory;
import src.view.gui.component.RoomListPanel;
import src.view.gui.component.StyledButton;
import src.view.gui.component.StyledHTMLPane;
import src.view.gui.component.StyledLabel;
import src.view.gui.component.StyledPanel;
import src.view.gui.component.StyledRadioButton;

/** Represents the Simulate Booking panel. */
public class SimulateBookingPanel extends StyledPanel {
    /** Field for inputting the guest name. */
    private StyledTextField guestNameField;

    /** Field for inputting the discount code. */
    private StyledTextField discountCodeField;

    /** The panel containing the list of rooms. */
    private RoomListPanel roomListPanel;

    /** A calendar from which the user selects a date to book. */
    private BookingCalendar bookingCalendar;

    /** Panel for displaying a preview of the reservation. */
    private StyledHTMLPane reservationPreview;

    /** Button for confirming the reservation. */
    private StyledButton bookButton;

    /** Button for resetting all fields in the panel. */
    private StyledButton resetButton;

    /** Group for the check-in and check-out radio buttons. */
    private ButtonGroup checkInOutGroup;

    /** Radio button for making a check-in selection. */
    private StyledRadioButton checkInButton;

    /** Radio button for making a check-out selection. */
    private StyledRadioButton checkOutButton;

    /** Panel for displaying the reservation details. */
    private StyledPanel detailsPanel;

    /** Represents the initial message displayed in the booking preview. */
    static public final String RESERVATION_PREVIEW_INITIAL_TEXT = """
            Input your booking details to update this preview.""";

    /** Initializes the Simulate Booking panel */
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

        guestNameField = new StyledTextField();
        textFieldPanel.add(guestNameField);

        StyledLabel discountCodeLabel = new StyledLabel("Discount code:");
        textFieldPanel.add(discountCodeLabel);

        discountCodeField = new StyledTextField();
        textFieldPanel.add(discountCodeField);
        detailsPanel.add(textFieldPanel);

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

        reservationPreview = new StyledHTMLPane(
                RESERVATION_PREVIEW_INITIAL_TEXT);
        StyledPanel reservationPreviewPanel = GUIFactory
                .createStyledHTMLPaneContainer(reservationPreview);
        detailsPanel.add(reservationPreviewPanel);

        StyledPanel buttonsPanel = new StyledPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        buttonsPanel.add(Box.createHorizontalGlue());

        bookButton = GUIFactory.createButton("Confirm booking");
        buttonsPanel.add(bookButton);

        buttonsPanel.add(Box.createHorizontalGlue());

        resetButton = GUIFactory
                .createDestructiveButton("Reset booking fields");
        buttonsPanel.add(resetButton);

        buttonsPanel.add(Box.createHorizontalGlue());

        detailsPanel.add(buttonsPanel);

        this.add(detailsPanel, BorderLayout.CENTER);

        detailsPanel.setVisible(false);
    }

    /**
     * Sets the room list with the given data.
     *
     * @param data the room data to display
     */
    public void setRoomList(ArrayList<String> data) {
        this.roomListPanel.setList(data);
    }

    /**
     * Sets the calendar availability with the given dates.
     *
     * @param dates an {@link ArrayList} of available dates. Usually obtained
     *              with {@link Room#getAvailableDates()}
     */
    public void setCalendarAvailability(ArrayList<Integer> dates) {
        this.bookingCalendar.setAvailabilityData(dates);
    }

    /**
     * Sets the calendar check-in date.
     *
     * @param date the check-in date
     */
    public void setCalendarCheckIn(int date) {
        if (date != BookingCalendarRenderer.NONE) {
            this.bookingCalendar.setCalendarText(
                    date, String.format("%d: Start", date));
        }
        this.bookingCalendar.setCalendarCheckIn(date);
    }

    /**
     * Sets the calendar check-out date.
     *
     * @param date the check-out date
     */
    public void setCalendarCheckOut(int date) {
        if (date != BookingCalendarRenderer.NONE) {
            this.bookingCalendar.setCalendarText(
                    date, String.format("%d: End", date));
        }
        this.bookingCalendar.setCalendarCheckOut(date);
    }

    /** {@return the guest name currently set} */
    public String getGuestNameFieldText() {
        return this.guestNameField.getText();
    }

    /** {@return the discount code string currently set} */
    public String getDiscountCodeFieldText() {
        return this.discountCodeField.getText();
    }

    /**
     * Checks if the calendar is under mouse focus.
     *
     * @return {@code true} if the calendar is under mouse focus, {@code false}
     *         otherwise
     */
    public boolean getIsCalendarFocused() {
        return this.bookingCalendar.isFocusOwner();
    }

    /** {@return the selected room index} */
    public int getRoomIndex() {
        return this.roomListPanel.getSelectedIndex();
    }

    /**
     * Sets the preview text.
     *
     * @param text the text to set
     */
    public void setPreview(String text) {
        this.reservationPreview.setText(text);
    }

    /**
     * {@return the row index within the booking calendar given a point
     * position}
     *
     * @param point the point to check, usually representing the mouse location
     */
    public int getBookingCalendarRowFromMouse(Point point) {
        return this.bookingCalendar.rowAtPoint(point);
    }

    /**
     * {@return the column index within the booking calendar given a point
     * position}
     *
     * @param point the point to check, usually representing the mouse location
     */
    public int getBookingCalendarColFromMouse(Point point) {
        return this.bookingCalendar.columnAtPoint(point);
    }

    /** Clears the room list selection and hides the details panel. */
    public void clearRoomListSelection() {
        this.roomListPanel.clearSelection();
        this.detailsPanel.setVisible(false);
    }

    /**
     * Sets the discount code input field text.
     *
     * @param discountCode the discount code to set
     */
    public void setDiscountCodeFieldText(String discountCode) {
        this.discountCodeField.setText(discountCode);
    }

    /**
     * Sets the guest name input field text.
     *
     * @param guestName the guest name to set
     */
    public void setGuestNameFieldText(String guestName) {
        this.guestNameField.setText(guestName);
    }

    /**
     * Sets the visibility of the details panel.
     *
     * @param visible {@code true} to display the panel, {@code false} otherwise
     */
    public void setDetailsVisible(boolean visible) {
        this.detailsPanel.setVisible(visible);
    }

    /** Enables the check-in button. */
    public void enableCheckInButton() {
        this.checkInButton.setSelected(true);
    }

    /** Clears the calendar selection. */
    public void clearCalendarSelection() {
        this.bookingCalendar.resetSelection();
    }

    /**
     * Sets the listeners for the booking simulation.
     *
     * @param simulateBookingRoomListListener the listener for the room list
     * @param bookingCalendarListener         the listener for the booking
     *                                        calendar
     */
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

package src.view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import src.controller.gui.HotelAvailabilityCalendarListener;
import src.controller.gui.HotelListListener;
import src.controller.gui.ManagePricesListener;
import src.controller.gui.ManageReservationListener;
import src.controller.gui.ManageRoomListener;
import src.controller.gui.RenameHotelListener;
import src.controller.gui.ReservationListListener;
import src.controller.gui.RoomListListener;
import src.controller.gui.SimulateBookingCalendarListener;
import src.controller.gui.SimulateBookingRoomListListener;
import src.controller.gui.TopMenuPaneListener;
import src.model.Hotel;
import src.view.gui.component.ColorCollection;
import src.view.gui.component.FontCollection;
import src.view.gui.component.HotelListPanel;
import src.view.gui.component.StyledLabel;
import src.view.gui.component.StyledPanel;
import src.view.gui.component.StyledTabbedPane;
import src.view.gui.delegate.ManageHotelDelegate;
import src.view.gui.delegate.SimulateBookingDelegate;
import src.view.gui.delegate.ViewHotelDelegate;
import src.view.gui.panel.ManageHotelPanel;
import src.view.gui.panel.SimulateBookingPanel;
import src.view.gui.panel.ViewHotelPanel;

/** Represents the top menu in the application's GUI. */
public class TopView extends JFrame {
    /** Represents the View Hotel tab. */
    static public final int VIEW_HOTEL_TAB = 0;

    /** Represents the Manage Hotel tab. */
    static public final int MANAGE_HOTEL_TAB = 1;

    /** Represents the Simulate Booking tab. */
    static public final int SIMULATE_BOOKING_TAB = 2;

    /** Panel for displaying the list of hotels. */
    private final HotelListPanel hotelListPanel;

    /** Container for the tabs containing each subpanel. */
    private final StyledTabbedPane topMenuPane;

    /** Panel for viewing hotel information. */
    private final ViewHotelPanel viewHotelPanel;

    /** Panel for managing hotels, including its rooms and reservations. */
    private final ManageHotelPanel manageHotelPanel;

    /** Panel for making reservations. */
    private final SimulateBookingPanel simulateBookingPanel;

    /**
     * Delegate class instance containing methods that handle the Manage Hotel
     * panel.
     */
    private ManageHotelDelegate manageHotelDelegate;

    /**
     * Delegate class instance containing methods that handle the Simulate
     * Booking panel.
     */
    private SimulateBookingDelegate simulateBookingDelegate;

    /**
     * Delegate class instance containing methods that handle the View Hotel
     * panel.
     */
    private ViewHotelDelegate viewHotelDelegate;

    /** Initializes the top view. */
    public TopView() {
        super("Hotel Reservation System");
        this.initializeWindow();

        this.setLayout(new BorderLayout());

        StyledPanel paddingPanel = new StyledPanel();
        paddingPanel.setLayout(new BorderLayout());
        paddingPanel.setBorder(BorderFactory.createEmptyBorder());

        /* Hotel list panel */
        hotelListPanel = new HotelListPanel(this.getWidth() / 4);
        hotelListPanel.setBackground(ColorCollection.BACKGROUND_COMPLEMENT);
        hotelListPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        28, 28, 28, 28));

        paddingPanel.add(hotelListPanel, BorderLayout.WEST);

        /* Subpanels */
        topMenuPane = new StyledTabbedPane();

        /* View hotel subpanel */
        viewHotelPanel = new ViewHotelPanel();
        topMenuPane.addTab("View hotel", viewHotelPanel);

        /* Manage hotel subpanel */
        manageHotelPanel = new ManageHotelPanel();
        topMenuPane.addTab("Manage hotel", manageHotelPanel);

        /* Simulate booking subpanel */
        simulateBookingPanel = new SimulateBookingPanel();
        topMenuPane.addTab("Simulate booking", simulateBookingPanel);

        topMenuPane.setBorder(
                BorderFactory.createEmptyBorder(
                        28, 28, 28, 28));
        topMenuPane.setVisible(false);

        paddingPanel.add(topMenuPane, BorderLayout.CENTER);
        this.add(paddingPanel, BorderLayout.CENTER);

        this.setVisible(true);

        manageHotelDelegate = new ManageHotelDelegate(manageHotelPanel);
        viewHotelDelegate = new ViewHotelDelegate(viewHotelPanel);
        simulateBookingDelegate = new SimulateBookingDelegate(
                simulateBookingPanel);
    }

    /** Sets the look and feel across the entire application. */
    public void setGlobalLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | UnsupportedLookAndFeelException e) {
            System.err.println("Unable to configure look and feel.");
        }
        UIManager.put(
                "TabbedPane.contentBorderInsets",
                new Insets(0, 0, 0, 0));
    }

    /** Initializes the application window. */
    public void initializeWindow() {
        this.setGlobalLookAndFeel();
        this.setLocationByPlatform(true);

        Dimension systemResolution = Toolkit.getDefaultToolkit()
                .getScreenSize();
        this.setMinimumSize(new Dimension(
                (int) (systemResolution.getWidth() / 2),
                (int) (systemResolution.getHeight() / 1.2)));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /** {@return the index of the selected tab} */
    public int getTabIndex() {
        return topMenuPane.getSelectedIndex();
    }

    /** {@return the delegate class instance for the Manage Hotel panel} */
    public ManageHotelDelegate getManageHotelDelegate() {
        return manageHotelDelegate;
    }

    /** {@return the delegate class instance for the Simulate Booking panel} */
    public SimulateBookingDelegate getSimulateBookingDelegate() {
        return simulateBookingDelegate;
    }

    /**
     * {@return the delegate class instance for View Hotel panel}
     */
    public ViewHotelDelegate getViewHotelDelegate() {
        return viewHotelDelegate;
    }

    /**
     * Sets the listener for the hotel list, including the button for removing a
     * hotel.
     *
     * @param hotelListListener the listener to set
     */
    public void setTopViewHotelListListener(
            HotelListListener hotelListListener) {
        this.hotelListPanel.setListener(hotelListListener);
        this.manageHotelPanel.setRemoveHotelButtonListener(hotelListListener);
    }

    /**
     * Sets the listener for the tabs pane.
     *
     * @param listener the listener to set
     */
    public void setTopMenuPaneListener(TopMenuPaneListener listener) {
        topMenuPane.addChangeListener(listener);
    }

    /**
     * Sets the listeners for the View Hotel panel.
     *
     * @param availabilityCalendarListener the listener for the availability
     *                                     calendar
     * @param viewRoomListListener         the listener for the room list
     * @param viewReservationListener      the listener for the reservation list
     */
    public void setViewHotelListeners(
            HotelAvailabilityCalendarListener availabilityCalendarListener,
            RoomListListener viewRoomListListener,
            ReservationListListener viewReservationListener) {
        this.viewHotelPanel.setCalendarListener(availabilityCalendarListener);
        this.viewHotelPanel.setRoomListListener(viewRoomListListener);
        this.viewHotelPanel.setReservationListener(viewReservationListener);
    }

    /**
     * Sets the listeners for the Manage Hotel panel.
     *
     * @param renameHotelListener       the listener for renaming the hotel
     * @param managePricesListener      the listener for managing prices and
     *                                  price modifiers
     * @param manageRoomListener        the listener for managing rooms
     * @param manageReservationListener the listener for managing reservations
     */
    public void setManageHotelListeners(
            RenameHotelListener renameHotelListener,
            ManagePricesListener managePricesListener,
            ManageRoomListener manageRoomListener,
            ManageReservationListener manageReservationListener) {
        this.manageHotelPanel.setRenameHotelListener(renameHotelListener);
        this.manageHotelPanel.setManagePricesListener(managePricesListener);
        this.manageHotelPanel.setManageRoomsListener(manageRoomListener);
        this.manageHotelPanel
                .setManageReservationsListener(manageReservationListener);
    }

    /**
     * Sets the listeners for the Simulate Booking panel.
     *
     * @param simulateBookingRoomListListener the listener for the room list
     * @param bookingCalendarListener         the listener for the booking
     *                                        calendar
     */
    public void setSimulateBookingListeners(
            SimulateBookingRoomListListener simulateBookingRoomListListener,
            SimulateBookingCalendarListener bookingCalendarListener) {
        this.simulateBookingPanel.setListeners(
                simulateBookingRoomListListener, bookingCalendarListener);
    }

    /**
     * Sets the hotel list with the given data.
     *
     * @param data the list of hotel names to set
     */
    public void setList(ArrayList<String> data) {
        this.hotelListPanel.setList(data);
    }

    /** {@return the index of the selected hotel} */
    public int getSelectedIndex() {
        return this.hotelListPanel.getSelectedIndex();
    }

    /**
     * Sets the selected hotel index.
     *
     * @param index the index of the hotel to select
     */
    public void setSelectedIndex(int index) {
        this.hotelListPanel.setSelectedIndex(index);
        this.topMenuPane.setVisible(true);
        this.topMenuPane.setSelectedIndex(VIEW_HOTEL_TAB);
    }

    /** Clears the hotel list selection and hides the right-hand panels. */
    public void clearSelectedIndex() {
        this.hotelListPanel.clearSelection();
        this.topMenuPane.setVisible(false);
    }

    /**
     * Sets the room list with the given data in all three subpanels.
     *
     * @param data the list of room names
     */
    public void setRoomList(String[] data) {
        ArrayList<String> dataAsList = new ArrayList<>(Arrays.asList(data));
        this.viewHotelPanel.setRoomList(dataAsList);
        this.manageHotelPanel.setRoomList(dataAsList);
        this.simulateBookingPanel.setRoomList(dataAsList);
    }

    /**
     * Sets the reservation list with the given data in all three subpanels.
     *
     * @param data the list of reservation names
     */
    public void setReservationList(String[] data) {
        ArrayList<String> dataAsList = new ArrayList<>(Arrays.asList(data));
        this.viewHotelPanel.setReservationList(dataAsList);
        this.manageHotelPanel.setReservationList(dataAsList);
    }

    /**
     * Prompts the user to add a hotel.
     *
     * @return the name of the hotel entered by the user
     * @see JOptionPane#showInputDialog(Object)
     */
    public String promptAddHotel() {
        return JOptionPane.showInputDialog("Hotel name");
    }

    /**
     * Prompts the user to add rooms.
     *
     * @param limit the maximum number of rooms that can be added. A hotel can
     *              only have up to 50 rooms.
     * @return an array with two elements. The first element contains the type
     *         of the room to add (or {@code -1} if the input is invalid or if
     *         the user cancelled). The second element contains the number of
     *         rooms to add.
     * @see Hotel#addRooms(int, int)
     */
    public int[] promptAddRoom(int limit) {
        JPanel promptPanel = new JPanel();
        promptPanel.setLayout(new BorderLayout());

        JLabel prompt = new StyledLabel(
                "Select the number and type of rooms to add:");
        promptPanel.add(prompt, BorderLayout.NORTH);

        JSpinner spinner = new JSpinner(
                new SpinnerNumberModel(1, null, null, 1));
        promptPanel.add(spinner, BorderLayout.WEST);
        spinner.setFont(FontCollection.SEGOE_UI_BODY);
        ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField()
                .setColumns(2);

        JComboBox<String> options = new JComboBox<>(new String[] {
                "Regular", "Deluxe", "Executive"
        });
        options.setFont(FontCollection.SEGOE_UI_BODY);
        promptPanel.add(options, BorderLayout.CENTER);

        int response = JOptionPane.showConfirmDialog(null, promptPanel,
                "Add rooms", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if ((Integer) spinner.getValue() > limit) {
            this.showRoomCountFullError();
            return new int[] {
                    -1, 0
            };
        } else if ((Integer) spinner.getValue() < 0) {
            return new int[] {
                    -1, 0
            };
        }

        return new int[] {
                (response == 0) ? options.getSelectedIndex() : -1,
                (Integer) spinner.getValue()
        };
    }

    /** Shows error message for duplicate hotel names. */
    public void showHotelNameExistsError() {
        JOptionPane.showMessageDialog(this,
                "A hotel with this name already exists!",
                "Hotel name conflict error", JOptionPane.ERROR_MESSAGE);
    }

    /** Shows error message for base prices less than 100. */
    public void basePriceUpdateLessThanMinimumError() {
        JOptionPane.showMessageDialog(this,
                "The base price must be at least 100.",
                "Invalid base price update error: less than minimum",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows error message when updating the base price for a hotel with
     * reservations.
     */
    public void basePriceUpdateReservationsExistError() {
        JOptionPane.showMessageDialog(this,
                "The base price cannot be updated while reservations exist in the current hotel.",
                "Invalid base price update error: reservations exist",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows error message for price modifier lower than {@code 0.5} or higher
     * than {@code 1.5}.
     */
    public void showPriceModifierOutOfBoundsError() {
        JOptionPane.showMessageDialog(this,
                "The price modifier must be within 0.50-1.50.",
                "Invalid price modifier update error: out of bounds",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows error message when updating the price modifier for a hotel with
     * reservations.
     */
    public void showPriceModifierReservationsExistError() {
        JOptionPane.showMessageDialog(this,
                "The base price cannot be updated while reservations exist.",
                "Invalid price modifier update error: out of bounds",
                JOptionPane.ERROR_MESSAGE);
    }

    /** Shows error message when adding rooms that exceed the limit of 50. */
    public void showRoomCountFullError() {
        JOptionPane.showMessageDialog(this,
                "A room may only have up to 50 rooms.",
                "Room limit reached error", JOptionPane.ERROR_MESSAGE);
    }

    /** Shows success message for adding a reservation. */
    public void showReservationSuccess() {
        JOptionPane.showMessageDialog(this,
                "Your reservation was made successfully.",
                "Reservation success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows an error message when a reservation fails.
     *
     * @param error the error message to display
     */
    public void showReservationError(String error) {
        JOptionPane.showMessageDialog(this,
                "Your reservation was not made successfully.\n" + error,
                "Invalid reservation error", JOptionPane.ERROR_MESSAGE);
    }

    /** Shows an error message when removing a room with reservations. */
    public void showCantRemoveRoomWithReservationsError() {
        JOptionPane.showMessageDialog(this,
                "A room cannot be removed if it has reservations.\n",
                "Cannot remove room error: reservations exist",
                JOptionPane.ERROR_MESSAGE);
    }

    /** Shows an error message when removing the only room in a hotel. */
    public void showCantRemoveOnlyRoomError() {
        JOptionPane.showMessageDialog(this,
                "A room cannot be removed if it is the only room in the hotel.\n",
                "Cannot remove room error: no other rooms exist",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Confirm a certain action.
     * 
     * @param prompt the dialogue prompt to display
     * @param title  the title of the pop-up window
     * 
     * @return {@code true} if the user chose "Yes", else returns {@code false}
     */
    public boolean confirmAction(String prompt, String title) {
        return JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to " + prompt,
                title,
                JOptionPane.YES_NO_OPTION) == 0;
    }
}
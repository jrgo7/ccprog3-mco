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
import javax.swing.JOptionPane;
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
    static public final int VIEW_HOTEL_TAB = 0;
    static public final int MANAGE_HOTEL_TAB = 1;
    static public final int SIMULATE_BOOKING_TAB = 2;

    private final HotelListPanel hotelListPanel;
    private final StyledTabbedPane topMenuPane;
    private final ViewHotelPanel viewHotelPanel;
    private final ManageHotelPanel manageHotelPanel;
    private final SimulateBookingPanel simulateBookingPanel;

    private ManageHotelDelegate manageHotelDelegate;
    private SimulateBookingDelegate simulateBookingDelegate;
    private ViewHotelDelegate viewHotelDelegate;

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
        paddingPanel.add(topMenuPane, BorderLayout.CENTER);

        this.add(paddingPanel, BorderLayout.CENTER);
        this.setVisible(true);

        manageHotelDelegate = new ManageHotelDelegate(manageHotelPanel);
        viewHotelDelegate = new ViewHotelDelegate(viewHotelPanel);
        simulateBookingDelegate = new SimulateBookingDelegate(
                simulateBookingPanel);
    }

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

    public int getTabIndex() {
        return topMenuPane.getSelectedIndex();
    }

    public ManageHotelDelegate getManageHotelDelegate() {
        return manageHotelDelegate;
    }

    public SimulateBookingDelegate getSimulateBookingDelegate() {
        return simulateBookingDelegate;
    }

    public ViewHotelDelegate getViewHotelDelegate() {
        return viewHotelDelegate;
    }

    public void setTopViewHotelListListener(
            HotelListListener hotelListListener) {
        this.hotelListPanel.setListener(hotelListListener);
        this.manageHotelPanel.setRemoveHotelButtonListener(hotelListListener);
    }

    public void setTopMenuPaneListener(TopMenuPaneListener listener) {
        topMenuPane.addChangeListener(listener);
    }

    public void setViewHotelListeners(
            HotelAvailabilityCalendarListener availabilityCalendarListener,
            RoomListListener viewRoomListListener,
            ReservationListListener viewReservationListener) {
        this.viewHotelPanel.setCalendarListener(availabilityCalendarListener);
        this.viewHotelPanel.setRoomListListener(viewRoomListListener);
        this.viewHotelPanel.setReservationListener(viewReservationListener);
    }

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

    public void setSimulateBookingListeners(
            SimulateBookingRoomListListener simulateBookingRoomListListener,
            SimulateBookingCalendarListener bookingCalendarListener) {
        this.simulateBookingPanel.setListeners(
                simulateBookingRoomListListener, bookingCalendarListener);
    }

    public void setTabIndex(int index) {
        topMenuPane.setSelectedIndex(index);
    }

    public void setList(ArrayList<String> data) {
        this.hotelListPanel.setList(data);
    }

    public int getSelectedIndex() {
        return this.hotelListPanel.getSelectedIndex();
    }

    public void setSelectedIndex(int index) {
        this.hotelListPanel.setSelectedIndex(index);
        this.topMenuPane.setVisible(true);
        this.topMenuPane.setSelectedIndex(VIEW_HOTEL_TAB);
    }

    public void clearSelectedIndex() {
        this.hotelListPanel.clearSelection();
        this.topMenuPane.setVisible(false);
    }

    public void setTopMenuPaneVisible(boolean enable) {
        this.topMenuPane.setVisible(enable);
    }

    public void setRoomList(String[] data) {
        ArrayList<String> dataAsList = new ArrayList<>(Arrays.asList(data));
        this.viewHotelPanel.setRoomList(dataAsList);
        this.manageHotelPanel.setRoomList(dataAsList);
        this.simulateBookingPanel.setRoomList(dataAsList);
    }

    public void setReservationList(String[] data) {
        ArrayList<String> dataAsList = new ArrayList<>(Arrays.asList(data));
        this.viewHotelPanel.setReservationList(dataAsList);
        this.manageHotelPanel.setReservationList(dataAsList);
    }

    public String promptAddHotel() {
        return JOptionPane.showInputDialog("Hotel name");
    }

    public int[] promptAddRoom(int limit) {
        StyledPanel promptPanel = new StyledPanel();

        promptPanel.setLayout(new BorderLayout());

        StyledLabel prompt = new StyledLabel(
                "Select the number and type of rooms to add:");
        promptPanel.add(prompt, BorderLayout.NORTH);

        JSpinner spinner = new JSpinner(
                new SpinnerNumberModel(1, 1, limit, 1));
        promptPanel.add(spinner, BorderLayout.WEST);
        spinner.setFont(FontCollection.SEGOE_UI_BODY);

        JComboBox<String> options = new JComboBox<>(new String[] {
                "Regular", "Deluxe", "Executive"
        });
        options.setFont(FontCollection.SEGOE_UI_BODY);
        promptPanel.add(options, BorderLayout.CENTER);

        int response = JOptionPane.showConfirmDialog(null,
                promptPanel, "Add rooms", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        return new int[] {
                (response == 0) ? options.getSelectedIndex() : -1,
                (Integer) spinner.getValue()
        };
    }

    public void showHotelNameExistsError() {
        JOptionPane.showMessageDialog(
                this,
                "A hotel with this name already exists!",
                "Hotel name conflict error",
                JOptionPane.ERROR_MESSAGE);
    }

    public void basePriceUpdateLessThanMinimumError() {
        JOptionPane.showMessageDialog(
                this,
                "The base price must be at least 100.",
                "Invalid base price update error: less than minimum",
                JOptionPane.ERROR_MESSAGE);
    }

    public void basePriceUpdateReservationsExistError() {
        JOptionPane.showMessageDialog(
                this,
                "The base price cannot be updated while reservations exist "
                        + "in the current hotel.",
                "Invalid base price update error: reservations exist",
                JOptionPane.ERROR_MESSAGE);
    }

    public void showPriceModifierError() {
        JOptionPane.showMessageDialog(
                this,
                "The price modifier must be within 0.50-1.50.",
                "Invalid price modifier error",
                JOptionPane.ERROR_MESSAGE);
    }

    public void showRoomCountFullError() {
        JOptionPane.showMessageDialog(
                this,
                "A room may only have up to 50 rooms.",
                "Room limit reached error",
                JOptionPane.ERROR_MESSAGE);
    }

    public void showReservationSuccess() {
        JOptionPane.showMessageDialog(
                this,
                "Your reservation was made successfully.",
                "Reservation success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void showReservationError(String error) {
        JOptionPane.showMessageDialog(
                this,
                "Your reservation was not made successfully.\n" + error,
                "Invalid reservation error",
                JOptionPane.ERROR_MESSAGE);
    }

    public void showCantRemoveRoomError() {
        JOptionPane.showMessageDialog(
                this,
                "A hotel must have at least one room.\n",
                "Cannot remove room",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Confirm a certain action.
     * 
     * @param prompt
     * @param title
     * 
     * @returns {@code true} if the user chose "Yes", else returns {@code false}
     */
    public boolean confirmAction(String prompt, String title) {
        return JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to " + prompt,
                title,
                JOptionPane.YES_NO_OPTION) == 0;
    }
}
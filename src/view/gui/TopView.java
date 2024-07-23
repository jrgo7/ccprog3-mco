package src.view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

import src.controller.gui.HotelAvailabilityCalendarListener;
import src.controller.gui.SimulateBookingCalendarListener;
import src.controller.gui.HotelListListener;
import src.controller.gui.ManagePricesListener;
import src.controller.gui.ManageRoomListener;
import src.controller.gui.RenameHotelListener;
import src.controller.gui.ReservationListListener;
import src.controller.gui.RoomListListener;
import src.controller.gui.SimulateBookingRoomListListener;
import src.controller.gui.TopMenuPaneListener;
import src.view.gui.component.HotelListPanel;
import src.view.gui.panel.ManageHotelPanel;
import src.view.gui.panel.SimulateBookingPanel;
import src.view.gui.panel.ViewHotelPanel;

/** Represents the top menu in the application's GUI. */
public class TopView extends JFrame {
    static public final Font ARIAL_PLAIN_FONT = new Font("Arial", Font.PLAIN, 14);
    static public final int VIEW_HOTEL_TAB = 0;
    static public final int MANAGE_HOTEL_TAB = 1;
    static public final int SIMULATE_BOOKING_TAB = 2;

    private HotelListPanel hotelListPanel;

    private JTabbedPane topMenuPane;
    private ViewHotelPanel viewHotelPanel;
    private ManageHotelPanel manageHotelPanel;
    private SimulateBookingPanel simulateBookingPanel;

    public TopView() {
        super("Hotel Reservation System");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Unable to configure look and feel.");
        }
        this.setLocationByPlatform(true);
        this.setLayout(new BorderLayout());
        Dimension systemResolution = Toolkit.getDefaultToolkit()
                .getScreenSize();
        this.setMinimumSize(new Dimension(
                (int) (systemResolution.getWidth() / 1.5),
                (int) (systemResolution.getHeight() / 1.5)));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.init();
        this.setVisible(true);

    }

    public void init() {
        topMenuPane = new JTabbedPane();
        // Hotel list panel
        hotelListPanel = new HotelListPanel(this.getWidth() / 3);

        this.add(hotelListPanel, BorderLayout.WEST);

        viewHotelPanel = new ViewHotelPanel();
        topMenuPane.addTab("View", viewHotelPanel);

        manageHotelPanel = new ManageHotelPanel();
        topMenuPane.addTab("Manage", manageHotelPanel);

        simulateBookingPanel = new SimulateBookingPanel();
        topMenuPane.addTab("Book", simulateBookingPanel);

        topMenuPane.setFont(ARIAL_PLAIN_FONT);
        this.add(topMenuPane, BorderLayout.CENTER);
    }

    // Global / "Top menu" methods

    public void setTopViewHotelListListener(
            HotelListListener hotelListListener) {
        this.hotelListPanel.setListener(hotelListListener);
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
            ManageRoomListener manageRoomListener) {
        this.manageHotelPanel.setRenameHotelListener(renameHotelListener);
        this.manageHotelPanel.setUpdateBasePriceListener(managePricesListener);
        this.manageHotelPanel.setManagePricesListener(managePricesListener);
        this.manageHotelPanel.setManageRoomListener(manageRoomListener);
    }

    public void setTabIndex(int index) {
        topMenuPane.setSelectedIndex(index);
    }

    public int getTabIndex() {
        return topMenuPane.getSelectedIndex();
    }

    public void setHotelListData(ArrayList<String> data) {
        this.hotelListPanel.setList(data);
    }

    public int getHotelListPrevSelectedIndex() {
        return this.hotelListPanel.getFallbackIndex();
    }

    public void setHotelListPrevSelectedIndex(int index) {
        this.hotelListPanel.setFallbackIndex(index);
    }

    public int getHotelListSelectedIndex() {
        return this.hotelListPanel.getSelectedIndex();
    }

    public void setHotelListSelectedIndex(int index) {
        this.hotelListPanel.setSelectedIndex(index);
    }

    public void removeHotelListSelection() {
        this.hotelListPanel.clearSelection();
    }

    public void setTopMenuPaneVisible(boolean enable) {
        this.topMenuPane.setVisible(enable);
    }

    /**
     * To be triggered when a hotel gets added
     */
    public void resetState() {
        this.resetAvailabilityCalendarSelection();
        this.setHotelAvailabilityDataText("<p></p>");
    }

    // View hotel delegations

    public void setHotelDataText(String text) {
        this.viewHotelPanel.updateHotelData(text);
    }

    public void setHotelAvailabilityDataText(String text) {
        this.viewHotelPanel.updateAvailability(text);
    }

    public void updateRoomList(String[] data) {
        ArrayList<String> dataAsList = new ArrayList<>(Arrays.asList(data));
        this.viewHotelPanel.updateRoomList(dataAsList);
        this.manageHotelPanel.updateRoomList(dataAsList);
        this.simulateBookingPanel.updateRoomList(dataAsList);
    }

    public void updateRoomData(String data, ArrayList<Integer> availableDates) {
        this.viewHotelPanel.updateRoomData(data, availableDates);
    }

    public int getAvailabilityCalendarRowFromMouse(Point point) {
        return this.viewHotelPanel.getCalendarRowAtPoint(point);
    }

    public int getAvailabilityCalendarColFromMouse(Point point) {
        return this.viewHotelPanel.getCalendarColAtPoint(point);
    }

    public void resetAvailabilityCalendarSelection() {
        this.viewHotelPanel.resetCalendarSelection();
    }

    public void resetViewRoomListSelection() {
        this.viewHotelPanel.resetRoomListSelection();
    }

    public int getViewRoomSelectedIndex() {
        return this.viewHotelPanel.getViewRoomSelectedIndex();
    }

    public void updateReservationList(String[] data) {
        ArrayList<String> dataAsList = new ArrayList<>(Arrays.asList(data));
        this.viewHotelPanel.updateReservationList(dataAsList);
    }

    public int getViewReservationSelectedIndex() {
        return this.viewHotelPanel.getViewReservationSelectedIndex();
    }

    public void setReservationData(String data) {
        this.viewHotelPanel.updateReservationData(data);
    }

    // Manage hotel delegations

    public String getRenameHotelText() {
        return this.manageHotelPanel.getRenameHotelText();
    }

    public void setRenameHotelText(String name) {
        this.manageHotelPanel.setRenameHotelText(name);
    }

    public String getUpdateBasePriceText() {
        return this.manageHotelPanel.getUpdateBasePriceText();
    }

    public void setUpdateBasePriceText(String basePrice) {
        this.manageHotelPanel.setUpdateBasePriceText(basePrice);
    }

    public void setManagePricesCalendarText(int date, String text) {
        this.manageHotelPanel.setManagePricesCalendarText(date, text);
    }

    public String getPriceModifierField() {
        return this.manageHotelPanel.getPriceModifierField();
    }

    public void setPriceModifierField(String text) {
        this.manageHotelPanel.setPriceModifierField(text);
    }

    public void setModifiedPriceText(String text) {
        this.manageHotelPanel.setModifiedPriceText(text);
    }

    public void setPriceModiferCalendarDate(int date) {
        this.manageHotelPanel.setPriceModiferCalendarDate(date);
    }

    public int getPriceModifierCalendarRowFromMouse(Point point) {
        return this.manageHotelPanel.getCalendarRowAtPoint(point);
    }

    public int getPriceModifierCalendarColFromMouse(Point point) {
        return this.manageHotelPanel.getCalendarColAtPoint(point);
    }

    public boolean getIsPriceModifierCalendarFocused() {
        return this.manageHotelPanel.getIsPriceModifierCalendarFocused();
    }

    public void resetPriceModifierCalendarSelection() {
        this.manageHotelPanel.resetPriceModifierCalendarSelection();
    }

    public boolean getIsUpdatePriceModifierFieldFocused() {
        return this.manageHotelPanel.getIsUpdatePriceModifierFieldFocused();
    }

    public boolean getIsUpdateBasePriceFieldFocused() {
        return this.manageHotelPanel.getIsUpdateBasePriceFieldFocused();
    }

    // Simulate booking delegations

    public void setBookingCalendarAvailability(ArrayList<Integer> dates) {
        this.simulateBookingPanel.setCalendarAvailability(dates);
    }

    public void setBookingCalendarCheckIn(int date) {
        this.simulateBookingPanel.setCalendarCheckIn(date);
    }

    public void setBookingCalendarCheckOut(int date) {
        this.simulateBookingPanel.setCalendarCheckOut(date);
    }

    public String getBookingGuestName() {
        return this.simulateBookingPanel.getGuestNameFieldText();
    }

    public int getBookingCalendarDay() {
        return this.simulateBookingPanel.getBookingCalendarDay();
    }

    public int getBookingRoomIndex() {
        return this.simulateBookingPanel.getRoomIndex();
    }

    public void resetBookingRoomListSelection() {
        this.simulateBookingPanel.resetRoomListSelection();
    }

    public String getBookingDiscountCode() {
        return this.simulateBookingPanel.getDiscountCodeFieldText();
    }

    public void setBookingDiscountCode(String discountCode) {
        this.simulateBookingPanel.setDiscountCodeFieldText(discountCode);
    }

    public void setBookingGuestName(String guestName) {
        this.simulateBookingPanel.setGuestNameFieldText(guestName);
    }

    public void updateSimulateBookingReservationPreview(String text) {
        this.simulateBookingPanel.setPreview(text);
    }

    public boolean getIsBookingCalendarFocused() {
        return this.simulateBookingPanel.getIsCalendarFocused();
    }

    public int getBookingCalendarRowFromMouse(Point point) {
        return this.simulateBookingPanel.getBookingCalendarRowFromMouse(point);
    }

    public int getBookingCalendarColFromMouse(Point point) {
        return this.simulateBookingPanel.getBookingCalendarColFromMouse(point);
    }

    public void setSimulateBookingListeners(
            SimulateBookingRoomListListener simulateBookingRoomListListener,
            SimulateBookingCalendarListener bookingCalendarListener) {
        this.simulateBookingPanel.setListeners(
                simulateBookingRoomListListener, bookingCalendarListener);
    }

    public void resetBookingFields() {
        this.simulateBookingPanel.setGuestNameFieldText("");
        this.simulateBookingPanel.setDiscountCodeFieldText("");
        this.simulateBookingPanel.enableCheckInButton();
    }

    public void resetBookingCalendarSelection() {
        this.simulateBookingPanel.resetCalendarSelection();
    }

    public void setBookingDetailsVisible(boolean visible) {
        this.simulateBookingPanel.setDetailsVisible(visible);
    }

    // Prompts and dialogs

    public String promptAddHotel() {
        return JOptionPane.showInputDialog("Hotel name");
    }

    public int[] promptAddRoom(int limit) {
        JPanel promptPanel = new JPanel();

        promptPanel.setLayout(new BorderLayout());

        promptPanel.add(
                new JLabel("Select the number and type of rooms to add:"),
                BorderLayout.NORTH);

        JSpinner spinner = new JSpinner(
            new SpinnerNumberModel(1, 1, limit, 1));
        promptPanel.add(spinner, BorderLayout.WEST);

        JComboBox<String> options = new JComboBox<>(new String[] {
                "Regular", "Deluxe", "Executive"
        });
        promptPanel.add(options, BorderLayout.CENTER);

        int response = JOptionPane.showConfirmDialog(null,
                promptPanel, "Add rooms", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        return new int[] {
                (response == 0) ? options.getSelectedIndex() : -1,
                (Integer) spinner.getValue()
        };
    }

    public void noHotelNameProvidedError() {
        JOptionPane.showMessageDialog(
                this,
                "Please provide a non-empty hotel name.",
                "No hotel name provided error",
                JOptionPane.ERROR_MESSAGE);
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
                "The base price cannot be updated while reservations exist " +
                        "in the current hotel.",
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
}
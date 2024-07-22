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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

import src.controller.gui.AvailabilityCalendarListener;
import src.controller.gui.HotelListListener;
import src.controller.gui.ManagePricesListener;
import src.controller.gui.ManageRoomListener;
import src.controller.gui.RenameHotelListener;
import src.controller.gui.RoomListListener;
import src.controller.gui.SimulateBookingRoomListListener;
import src.view.gui.component.HotelListPanel;
import src.view.gui.panel.ManageHotelPanel;
import src.view.gui.panel.SimulateBookingPanel;
import src.view.gui.panel.ViewHotelPanel;

/** Represents the top menu in the application's GUI. */
/* TODO: Maybe have a JFrame as a field in TopView instead of inheritance */
public class TopView extends JFrame {
    static public final Font ARIAL_PLAIN_FONT = new Font("Arial", Font.PLAIN, 14);
    private HotelListPanel hotelListPanel;
    private ViewHotelPanel viewHotelPanel;
    private ManageHotelPanel manageHotelPanel;
    private SimulateBookingPanel simulateBookingPanel;

    JList<String> hotelList;
    JTabbedPane topMenuPane;

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
                (int) systemResolution.getWidth() / 2,
                (int) systemResolution.getHeight() / 2));
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

    // Global / "Top menu"

    public String promptAddHotel() {
        return JOptionPane.showInputDialog("Hotel name");
    }

    public int[] promptAddRoom(int limit) {
        JPanel promptPanel = new JPanel();

        promptPanel.setLayout(new BorderLayout());

        promptPanel.add(
                new JLabel("Select the number and type of rooms to add:"),
                BorderLayout.NORTH);

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, limit, 1));
        promptPanel.add(spinner, BorderLayout.WEST);

        JComboBox<String> options = new JComboBox<>(new String[] {
                "Regular", "Deluxe", "Executive"
        });
        promptPanel.add(options, BorderLayout.CENTER);

        int response = JOptionPane.showConfirmDialog(null,
                promptPanel, "Add rooms", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        return new int[] {
                response == 0 ? options.getSelectedIndex() : -1, (Integer) spinner.getValue()
        };
    }

    public void setTopViewHotelListListener(
            HotelListListener hotelListListener) {
        this.hotelListPanel.setListener(hotelListListener);
    }

    public void setViewHotelListeners(
            AvailabilityCalendarListener availabilityCalendarListener,
            RoomListListener viewRoomListListener) {
        this.viewHotelPanel.setCalendarListener(availabilityCalendarListener);
        this.viewHotelPanel.setRoomListListener(viewRoomListListener);
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

    public void updateRoomData(String data) {
        this.viewHotelPanel.updateRoomData(data);
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

    public int getViewRoomSelectedIndex() {
        return this.viewHotelPanel.getViewRoomSelectedIndex();
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

    // Simulate booking
    
    public void setSimulateBookingCalendarAvailability(ArrayList<Integer> dates) {
        this.simulateBookingPanel.setCalendarAvailability(dates);
    }

    public String getBookingGuestName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBookingGuestName'");
    }

    public int getBookingCheckIn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBookingCheckIn'");
    }

    public int getBookingCheckOut() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBookingCheckOut'");
    }

    public int getBookingRoomIndex() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBookingRoomIndex'");
    }

    public String getBookingDiscountCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBookingDiscountCode'");
    }

    public void setSimulateBookingListeners(SimulateBookingRoomListListener listener) {
        this.simulateBookingPanel.setListener(listener);
    }

    // Error dialogs
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

    public void invalidBasePriceUpdateError() {
        JOptionPane.showMessageDialog(
                this,
                "The base price cannot be updated while reservations exist " +
                        "in the current hotel. The base price must also be at least 100.",
                "Invalid base price update error",
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

    /**
     * To be triggered when a hotel gets added
     */
    public void resetState() {
        this.resetAvailabilityCalendarSelection();
        this.setHotelAvailabilityDataText("<p></p>");
    }
}
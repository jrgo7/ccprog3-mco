package src.view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
// import javax.swing.UIManager;

import src.controller.gui.AvailabilityCalendarListener;
import src.controller.gui.HotelListListener;
import src.controller.gui.ManagePricesListener;
import src.controller.gui.RenameHotelListener;
import src.controller.gui.RoomListListener;
import src.controller.gui.UpdateBasePriceListener;
import src.view.gui.component.HotelListPanel;
import src.view.gui.panel.ManageHotelPanel;
import src.view.gui.panel.ViewHotelPanel;

/** Represents the top menu in the application's GUI. */
/* TODO: Maybe have a JFrame as a field in TopView instead of inheritance */
public class TopView extends JFrame {
    static public final int VIEW_HOTEL_SCREEN = 0;
    static public final int CHECK_AVAILABILITY_SCREEN = 101;
    static public final int MANAGE_HOTEL_SCREEN = 1;
    static public final int SIMULATE_BOOKING_SCREEN = 2;
    static public final Font ARIAL_PLAIN_FONT = new Font("Arial", Font.PLAIN, 14);
    private HotelListPanel hotelListPanel;
    private ViewHotelPanel viewHotelPanel;
    private ManageHotelPanel manageHotelPanel;

    JList<String> hotelList;
    JTabbedPane topMenuPane = new JTabbedPane();

    public TopView() {
        super("Hotel Reservation System");
        // try {
        // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // } catch (Exception e) {
        // System.err.println("Unable to configure look and feel.");
        // }
        this.setLocationByPlatform(true);
        this.setLayout(new BorderLayout());
        Dimension systemResolution = Toolkit.getDefaultToolkit().getScreenSize();
        this.setMinimumSize(new Dimension(
                (int) systemResolution.getWidth() / 2,
                (int) systemResolution.getHeight() / 2));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.init();
        this.setVisible(true);
    }

    public void init() {
        // Hotel list panel
        hotelListPanel = new HotelListPanel(this.getWidth() / 3);

        this.add(hotelListPanel, BorderLayout.WEST);

        // View Hotel
        viewHotelPanel = new ViewHotelPanel();

        topMenuPane.addTab("View", viewHotelPanel);

        // Manage hotel
        manageHotelPanel = new ManageHotelPanel();

        topMenuPane.addTab("Manage", manageHotelPanel);

        // Simulate booking
        JPanel bookHotelPanel = new JPanel();
        JLabel bookHotelPanelLabel = new JLabel("Simulate booking");
        bookHotelPanel.add(bookHotelPanelLabel);
        topMenuPane.addTab("Book", bookHotelPanel);

        topMenuPane.setFont(ARIAL_PLAIN_FONT);
        this.add(topMenuPane, BorderLayout.CENTER);
    }

    public String promptAddHotel() {
        return JOptionPane.showInputDialog("Hotel name");
    }

    public void setListeners(
            HotelListListener hotelListListener,
            AvailabilityCalendarListener availabilityCalendarListener,
            RenameHotelListener renameHotelListener,
            UpdateBasePriceListener updateBasePriceListener,
            RoomListListener viewRoomListListener,
            ManagePricesListener managePricesListener) {
        this.hotelListPanel.setListener(hotelListListener);
        this.viewHotelPanel.setCalendarListener(availabilityCalendarListener);
        this.manageHotelPanel.setRenameHotelListener(renameHotelListener);
        this.manageHotelPanel.setUpdateBasePriceListener(updateBasePriceListener);
        this.viewHotelPanel.setRoomListListener(viewRoomListListener);
        this.manageHotelPanel.setManagePricesListener(managePricesListener);
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

    public void setManagePricesCalendarText(int day, String text) {
        this.manageHotelPanel.setManagePricesCalendarText(day, text);
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

    public void setPriceModiferCalendarDay(int day) {
        this.manageHotelPanel.setPriceModiferCalendarDay(day);
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

    /**
     * To be triggered when a hotel gets added
     */
    public void resetState() {
        this.resetAvailabilityCalendarSelection();
        this.setHotelAvailabilityDataText("<p></p>");
    }

    public int getContext() {
        return topMenuPane.getSelectedIndex();
    }

    public int getSubcontext() {
        int retval = (this.getContext() + 1) * 100;
        switch (getContext()) {
            case TopView.VIEW_HOTEL_SCREEN:
                return retval + this.viewHotelPanel.getSelectedSubpanelIndex() + 1;
        }
        return -1;
    }
}
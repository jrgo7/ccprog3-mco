package src.view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

import src.controller.gui.AvailabilityCalendarListener;
import src.controller.gui.HotelListListener;

/** Represents the top menu in the application's GUI. */
/* TODO: Maybe have a JFrame as a field in TopView instead of inheritance */
public class TopView extends JFrame {
    static public final int VIEW_HOTEL_SCREEN = 0;
    static public final int CHECK_AVAILABILITY_SCREEN = 101;
    static public final int MANAGE_HOTEL_SCREEN = 1;
    static public final int SIMULATE_BOOKING_SCREEN = 2;

    private HotelListPanel hotelListPanel;
    private ViewHotelPanel viewHotelPanel;

    JList<String> hotelList;
    JTabbedPane topMenuPane = new JTabbedPane();
    JLabel manageHotelNameLabel = new JLabel();

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
        JPanel manageHotelPanel = new JPanel();
        manageHotelPanel.setLayout(new BorderLayout());
        manageHotelPanel.add(manageHotelNameLabel, BorderLayout.NORTH);

        topMenuPane.addTab("Manage", manageHotelPanel);

        // Simulate booking
        JPanel bookHotelPanel = new JPanel();
        JLabel bookHotelPanelLabel = new JLabel("Simulate booking");
        bookHotelPanel.add(bookHotelPanelLabel);
        topMenuPane.addTab("Book", bookHotelPanel);

        this.add(topMenuPane, BorderLayout.CENTER);
    }

    public String promptAddHotel() {
        return JOptionPane.showInputDialog("Hotel name");
    }

    public void setListeners(HotelListListener hotelListListener,
            AvailabilityCalendarListener availabilityCalendarListener) {
        this.hotelListPanel.setListener(hotelListListener);
        this.viewHotelPanel.setCalendarListener(availabilityCalendarListener);
    }

    public void setTabIndex(int index) {
        topMenuPane.setSelectedIndex(index);
    }

    public void setHotelListData(ArrayList<String> data) {
        this.hotelListPanel.updateList(data);
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

    public void setHotelNameLabelText(String text) {
        manageHotelNameLabel.setText(text);
        System.out.println(text);
    }

    public void setHotelDataText(String text) {
        this.viewHotelPanel.updateHotelData(text);
    }

    public void setHotelAvailabilityDataText(String text) {
        this.viewHotelPanel.updateAvailability(text);
    }

    public void updateRoomList(String[] data) {
       this.viewHotelPanel.updateRoomList(new ArrayList<>(Arrays.asList(data)));
    }

    public int getAvailabilityCalendarRowFromMouse(Point point) {
        return this.viewHotelPanel.getCalendarRowAtPoint(point);
    }

    public int getAvailabilityCalendarColFromMouse(Point point) {
        return this.viewHotelPanel.getCalendarColAtPoint(point);
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
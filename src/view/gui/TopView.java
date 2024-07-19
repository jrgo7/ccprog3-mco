package src.view.gui;

import javax.swing.*;

import src.controller.gui.AvailabilityCalendarListener;
import src.controller.gui.HotelListListener;

import java.awt.*;
import java.util.ArrayList;

public class TopView extends JFrame {
    JList<String> hotelList;
    int hotelListPrevSelectedIndex = 0; // when cancelling add hotel
    JTabbedPane topMenuPane = new JTabbedPane();

    static public final int VIEW_HOTEL_SCREEN = 0;
    JTextArea hotelHighLevelData = new JTextArea();
    JTabbedPane viewHotelSubMenuPane = new JTabbedPane();
    static public final int CHECK_AVAILABILITY_SCREEN = 101;
    JTextArea hotelAvailabilityData = new JTextArea();

    static public final int MANAGE_HOTEL_SCREEN = 1;
    JLabel manageHotelNameLabel = new JLabel();

    static public final int SIMULATE_BOOKING_SCREEN = 2;

    Calendar availabilityCalendar = new Calendar();

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
        JPanel hotelListPanel = new JPanel();
        hotelListPanel.setLayout(new BorderLayout());

        JLabel hotelListPanelLabel = new JLabel("Hotels");
        hotelListPanel.setFont(new Font("Arial", 0, 32));
        hotelListPanel.add(hotelListPanelLabel, BorderLayout.NORTH);

        hotelList = new JList<String>();
        hotelList.setFixedCellWidth(this.getWidth() / 3);
        hotelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hotelListPanel.add(hotelList, BorderLayout.CENTER);

        this.add(hotelListPanel, BorderLayout.WEST);

        // View Hotel
        JPanel viewHotelPanel = new JPanel();
        viewHotelPanel.setLayout(new BorderLayout());
        hotelHighLevelData.setEditable(false);
        viewHotelPanel.add(hotelHighLevelData, BorderLayout.NORTH);

        // View Hotel - Submenu - Availability
        JPanel checkAvailabilityPanel = new JPanel();
        checkAvailabilityPanel.setLayout(new BorderLayout());
        checkAvailabilityPanel.add(availabilityCalendar, BorderLayout.NORTH);
        checkAvailabilityPanel.add(hotelAvailabilityData, BorderLayout.CENTER);
        viewHotelSubMenuPane.add("Availability", checkAvailabilityPanel);

        // View Hotel - Submenu - Rooms
        JPanel checkRoomsPanel = new JPanel();
        viewHotelSubMenuPane.add("Rooms", checkRoomsPanel);

        // View Hotel - Submenu - Reservations
        JPanel checkReservationsPanel = new JPanel();
        viewHotelSubMenuPane.add("Reservations", checkReservationsPanel);

        viewHotelPanel.add(viewHotelSubMenuPane, BorderLayout.CENTER);

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
        hotelList.getSelectionModel()
                .addListSelectionListener(hotelListListener);
        availabilityCalendar
                .addMouseListener(availabilityCalendarListener);
        availabilityCalendar
                .getSelectionModel()
                .addListSelectionListener(availabilityCalendarListener);
        availabilityCalendar
                .getColumnModel()
                .getSelectionModel()
                .addListSelectionListener(availabilityCalendarListener);
        availabilityCalendar
                .addKeyListener(availabilityCalendarListener);
    }

    public void setTabIndex(int index) {
        topMenuPane.setSelectedIndex(index);
    }

    public void setHotelListData(ArrayList<String> data) {
        String[] arr = new String[data.size() + 1];
        data.add("Add hotel...");
        hotelList.setListData(data.toArray(arr));
    }

    public int getHotelListPrevSelectedIndex() {
        return this.hotelListPrevSelectedIndex;
    }

    public void setHotelListPrevSelectedIndex(int index) {
        this.hotelListPrevSelectedIndex = index;
    }

    public int getHotelListSelectedIndex() {
        return hotelList.getSelectedIndex();
    }

    public void setHotelListSelectedIndex(int index) {
        hotelList.setSelectionInterval(index, index);
    }

    public void removeHotelListSelection() {
        hotelList.removeSelectionInterval(
                0, hotelList.getComponentCount());
    }

    public void setHotelNameLabelText(String text) {
        manageHotelNameLabel.setText(text);
        System.out.println(text);
    }

    public void setHotelDataText(String text) {
        hotelHighLevelData.setText(text);
    }

    public void setHotelAvailabilityDataText(String text) {
        hotelAvailabilityData.setText(text);
    }

    public int getAvailabilityCalendarRowFromMouse(Point point) {
        return availabilityCalendar.rowAtPoint(point);
    }

    public int getAvailabilityCalendarColFromMouse(Point point) {
        return availabilityCalendar.columnAtPoint(point);
    }

    public int getContext() {
        return topMenuPane.getSelectedIndex();
    }

    public int getSubcontext() {
        int retval = (this.getContext() + 1) * 100;
        switch (getContext()) {
            case TopView.VIEW_HOTEL_SCREEN:
                return retval + viewHotelSubMenuPane.getSelectedIndex() + 1;
        }
        return -1;
    }
}
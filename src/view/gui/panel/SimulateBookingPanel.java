package src.view.gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import src.controller.gui.SimulateBookingRoomListListener;
import src.view.gui.TopView;
import src.view.gui.component.Calendar;
import src.view.gui.component.RoomListPanel;

public class SimulateBookingPanel extends JPanel {
    private JTextField guestNameField;
    private JTextField discountCodeField;
    private RoomListPanel roomListPanel;
    private Calendar durationPickerCalendar;
    private JEditorPane priceBreakdownComponent;
    private JButton bookButton;
    private ButtonGroup checkInOutGroup;
    private JRadioButton checkInButton;
    private JRadioButton checkOutButton;


    public SimulateBookingPanel() {
        this.setLayout(new BorderLayout());

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

        this.add(textFieldPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));

        roomListPanel = new RoomListPanel(200, false);
        centerPanel.add(roomListPanel);

        JPanel calendarPanel = new JPanel();
        calendarPanel.setLayout(new BoxLayout(calendarPanel, BoxLayout.Y_AXIS));
        
        JPanel checkInOutPanel = new JPanel();
        checkInOutPanel.setLayout(new BoxLayout(checkInOutPanel, BoxLayout.X_AXIS));
        
        checkInOutGroup = new ButtonGroup();

        checkInButton = new JRadioButton("Set check-in date");
        checkInButton.setFont(TopView.ARIAL_PLAIN_FONT);
        checkInOutPanel.add(checkInButton);
        checkInOutGroup.add(checkInButton);
        
        checkOutButton = new JRadioButton("Set check-out date");
        checkOutButton.setFont(TopView.ARIAL_PLAIN_FONT);
        checkInOutPanel.add(checkOutButton);
        checkInOutGroup.add(checkOutButton);

        calendarPanel.add(checkInOutPanel);

        durationPickerCalendar = new Calendar();
        calendarPanel.add(durationPickerCalendar);
        
        priceBreakdownComponent = new JEditorPane(
            "text/html",
            "<h3 style=\"font-family: sans-serif\">Reservation data goes here</h3>");
        calendarPanel.add(priceBreakdownComponent);

        centerPanel.add(calendarPanel);

        this.add(centerPanel, BorderLayout.CENTER);

        bookButton = new JButton("Book");

        this.add(bookButton, BorderLayout.SOUTH);
    }

    public void updateRoomList(ArrayList<String> data) {
        this.roomListPanel.setList(data);
    }

    public void setCalendarAvailability(ArrayList<Integer> dates) {
        this.durationPickerCalendar.setAvailability(dates);
    }

    public void setListener(SimulateBookingRoomListListener listener) {
        this.roomListPanel.setListener(listener);
    }
}

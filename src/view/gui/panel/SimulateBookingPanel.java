package src.view.gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
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
    private JTable priceBreakdownTable;
    private JButton bookButton;

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

        durationPickerCalendar = new Calendar();
        centerPanel.add(durationPickerCalendar);

        priceBreakdownTable = new JTable(3, 2);
        // TODO for testing only
        TableModel model = priceBreakdownTable.getModel();
        for (int i = 0; i < 3; i++) {
            model.setValueAt("Dates", i, 0);
            model.setValueAt("Price", i, 1);
        }
        // TODO END
        centerPanel.add(priceBreakdownTable);

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

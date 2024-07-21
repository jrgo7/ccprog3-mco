package src.view.gui.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.TableModel;

import src.view.gui.component.Calendar;
import src.view.gui.component.RoomListPanel;

public class SimulateBookingPanel extends JPanel {
    private JTextField guestNameField;
    private JTextField discountCodeField;
    private RoomListPanel roomListComponent;
    private Calendar durationPickerCalendar;
    private JTable priceBreakdownTable;
    private JButton bookButton;

    public SimulateBookingPanel() {
        this.setLayout(new BorderLayout());

        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridLayout(2, 2));

        JLabel guestNameLabel = new JLabel("Guest name:");
        textFieldPanel.add(guestNameLabel);

        JLabel discountCodeLabel = new JLabel("Discount code:");
        textFieldPanel.add(discountCodeLabel);

        discountCodeField = new JTextField();
        textFieldPanel.add(discountCodeField);

        guestNameField = new JTextField();
        textFieldPanel.add(guestNameField);

        this.add(textFieldPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));

        roomListComponent = new RoomListPanel(100);
        centerPanel.add(roomListComponent);

        durationPickerCalendar = new Calendar();
        centerPanel.add(durationPickerCalendar);

        priceBreakdownTable = new JTable(3, 2);
        // TODO for testing only
        TableModel model = priceBreakdownTable.getModel();
        for (int i = 0; i < 3; i++) {
            model.setValueAt("Value", i, 0);
            model.setValueAt("Value", i, 1);
        }
        // TODO END
        centerPanel.add(priceBreakdownTable);

        this.add(centerPanel, BorderLayout.CENTER);

        bookButton = new JButton("Book");

        this.add(bookButton, BorderLayout.SOUTH);
    }
}

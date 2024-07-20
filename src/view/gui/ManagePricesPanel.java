package src.view.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import src.controller.gui.ManagePricesListener;

public class ManagePricesPanel extends JPanel {
    private Calendar calendarComponent;
    private JTextField priceField;
    private JButton priceUpdateButton;

    public ManagePricesPanel() {
        this.setLayout(new BorderLayout());

        calendarComponent = new Calendar();
        this.add(calendarComponent, BorderLayout.CENTER);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(1, 2));

        priceField = new JTextField();
        priceField.setDocument(new DecimalDocument());
        fieldPanel.add(priceField, 0);

        priceUpdateButton = new JButton("Update price modifier");
        fieldPanel.add(priceUpdateButton, 1);
        
        this.add(fieldPanel, BorderLayout.SOUTH);
    }

    public void setCalendarText(int day, String text) {
        calendarComponent.setCalendarText(day, text);
    }

    public void setListener(ManagePricesListener listener) {
        this.calendarComponent.addMouseListener(listener);
        this.calendarComponent.addKeyListener(listener);
        this.calendarComponent.addMouseMotionListener(listener);
        this.priceField.addKeyListener(listener);
        this.priceUpdateButton.addActionListener(listener);
        this.priceUpdateButton.addKeyListener(listener);
    }
}

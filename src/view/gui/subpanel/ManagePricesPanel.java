package src.view.gui.subpanel;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import src.controller.gui.ManagePricesListener;
import src.view.gui.component.Calendar;
import src.view.gui.component.DecimalDocument;

public class ManagePricesPanel extends JPanel {
    private Calendar calendarComponent;
    private JTextField priceField;
    private JEditorPane modifiedPriceData;
    private JButton priceUpdateButton;

    public ManagePricesPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        calendarComponent = new Calendar();
        this.add(calendarComponent);
        
        modifiedPriceData = new JEditorPane("text/html", "<p></p>");
        modifiedPriceData.setEditable(false);
        this.add(modifiedPriceData);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(1, 2));

        
        priceField = new JTextField();
        priceField.setDocument(new DecimalDocument());
        fieldPanel.add(priceField, 0);

        priceUpdateButton = new JButton("Update price modifier");
        fieldPanel.add(priceUpdateButton, 1);
        
        this.add(fieldPanel);
    }

    public void setCalendarText(int day, String text) {
        calendarComponent.setCalendarText(day, text);
    }

    /**
     * "Day X: basePrice * priceModifier = newPrice"
     * @param text
     */
    public void setModifiedPriceText(String text) {
        this.modifiedPriceData.setText(text);
    }

    public String getPriceModifierField() {
        return this.priceField.getText();
    }

    public void setPriceModifierField(String text) {
        this.priceField.setText(text);
    }

    public void selectCalendarDay(int day) {
        this.calendarComponent.selectDay(day);
    }

    public boolean getIsCalendarFocused() {
        return this.calendarComponent.isFocusOwner();
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

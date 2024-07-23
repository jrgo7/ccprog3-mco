package src.view.gui.subpanel;

import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.controller.gui.ManagePricesListener;
import src.view.gui.TopView;
import src.view.gui.component.Calendar;
import src.view.gui.component.DecimalDocument;

public class ManagePricesPanel extends JPanel {
    private Calendar calendarComponent;
    private JTextField priceModifierField;
    private JEditorPane modifiedPriceData;
    private JButton priceUpdateButton;

    public ManagePricesPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        calendarComponent = new Calendar();
        this.add(calendarComponent);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(1, 3));

        JLabel priceModifierLabel = new JLabel("Update price modifier:");
        priceModifierLabel.setFont(TopView.ARIAL_PLAIN_FONT);
        fieldPanel.add(priceModifierLabel, 0);

        priceModifierField = new JTextField();
        priceModifierField.setDocument(new DecimalDocument());
        fieldPanel.add(priceModifierField, 1);

        priceUpdateButton = new JButton("Update price modifier");
        fieldPanel.add(priceUpdateButton, 2);

        this.add(fieldPanel);

        modifiedPriceData = new JEditorPane("text/html", "<p></p>");
        modifiedPriceData.setEditable(false);
        this.add(modifiedPriceData);
    }

    public void setCalendarText(int date, String text) {
        calendarComponent.setCalendarText(date, text);
    }

    /**
     * "Date X: basePrice * priceModifier = newPrice"
     * 
     * @param text
     */
    public void setModifiedPriceText(String text) {
        this.modifiedPriceData.setText(text);
    }

    public String getPriceModifierField() {
        return this.priceModifierField.getText();
    }

    public void setPriceModifierField(String text) {
        this.priceModifierField.setText(text);
    }

    public void selectCalendarDate(int date) {
        this.calendarComponent.selectDate(date);
    }

    public boolean getIsCalendarFocused() {
        return this.calendarComponent.isFocusOwner();
    }

    public void resetCalendarSelection() {
        this.calendarComponent.resetSelection();
    }

    public boolean getIsUpdatePriceModifierFieldFocused() {
        return this.priceModifierField.isFocusOwner();
    }

    public int getCalendarRowAtPoint(Point point) {
        return this.calendarComponent.rowAtPoint(point);
    }

    public int getCalendarColAtPoint(Point point) {
        return this.calendarComponent.columnAtPoint(point);
    }

    public void setListener(ManagePricesListener listener) {
        this.calendarComponent.setListener(listener);
        this.priceModifierField.addKeyListener(listener);
        this.priceUpdateButton.addActionListener(listener);
        this.priceUpdateButton.addKeyListener(listener);
    }


}

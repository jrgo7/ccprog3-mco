package src.view.gui.subpanel;

import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.BoxLayout;
import src.view.gui.component.StyledHTMLPane;
import src.view.gui.component.StyledLabel;
import src.view.gui.component.StyledPanel;
import javax.swing.JTextField;

import src.controller.gui.ManagePricesListener;
import src.view.gui.component.Calendar;
import src.view.gui.component.DecimalDocument;
import src.view.gui.component.FontCollection;
import src.view.gui.component.StyledButton;

public class ManagePricesPanel extends StyledPanel {
    private Calendar calendarComponent;
    private JTextField priceModifierField;
    private StyledHTMLPane modifiedPriceData;
    private StyledButton priceUpdateButton;

    public ManagePricesPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        calendarComponent = new Calendar();
        this.add(calendarComponent);

        StyledPanel fieldPanel = new StyledPanel();
        fieldPanel.setLayout(new GridLayout(1, 3));

        StyledLabel priceModifierLabel = new StyledLabel("Update price modifier:");
        fieldPanel.add(priceModifierLabel, 0);

        priceModifierField = new JTextField();
        priceModifierField.setDocument(new DecimalDocument());
        fieldPanel.add(priceModifierField, 1);

        priceUpdateButton = new StyledButton("Update price modifier");
        fieldPanel.add(priceUpdateButton, 2);

        this.add(fieldPanel);

        modifiedPriceData = new StyledHTMLPane();
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

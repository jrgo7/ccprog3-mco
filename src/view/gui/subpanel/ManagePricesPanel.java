package src.view.gui.subpanel;

import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.JTextField;

import src.controller.gui.ManagePricesListener;
import src.view.gui.component.Calendar;
import src.view.gui.component.DecimalDocument;
import src.view.gui.component.GUIFactory;
import src.view.gui.component.StyledButton;
import src.view.gui.component.StyledHTMLPane;
import src.view.gui.component.StyledLabel;
import src.view.gui.component.StyledPanel;
import src.view.gui.panel.ManageHotelPanel;

/** Represents the Manage Prices subpanel under a {@link ManageHotelPanel} */
public class ManagePricesPanel extends StyledPanel {
    /** The calendar for date selection. */
    private Calendar calendarComponent;

    /** The price modifier input field. */
    private JTextField priceModifierField;

    /** A panel for displaying the modified price data. */
    private StyledHTMLPane modifiedPriceData;

    /** A button for confirming the price modifications. */
    private StyledButton priceUpdateButton;

    /** Initializes the subpanel */
    public ManagePricesPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        calendarComponent = new Calendar();
        this.add(calendarComponent);

        StyledPanel fieldPanel = new StyledPanel();
        fieldPanel.setLayout(new GridLayout(1, 3));

        StyledLabel priceModifierLabel = new StyledLabel(
                "Update price modifier:");
        fieldPanel.add(priceModifierLabel);

        priceModifierField = new JTextField();
        priceModifierField.setDocument(new DecimalDocument());
        fieldPanel.add(priceModifierField);

        priceUpdateButton = GUIFactory
                .createButton("Update price modifier");
        fieldPanel.add(priceUpdateButton);

        this.add(fieldPanel);

        modifiedPriceData = new StyledHTMLPane();
        this.add(modifiedPriceData);
    }

    /**
     * Sets the text on a specific date in a calendar.
     * 
     * @param date the date to set
     * @param text the text to set
     */
    public void setCalendarText(int date, String text) {
        calendarComponent.setCalendarText(date, text);
    }

    /**
     * "Date X: basePrice * priceModifier = newPrice"
     * 
     * @param text the text to display
     */
    public void setModifiedPriceText(String text) {
        this.modifiedPriceData.setText(text);
    }

    /** {@return the text in the price modifier input field} */
    public String getPriceModifierFieldText() {
        return this.priceModifierField.getText();
    }

    /**
     * Sets the text of the price modifier input field.
     * 
     * @param text the text to set
     */
    public void setPriceModifierFieldText(String text) {
        this.priceModifierField.setText(text);
    }

    /**
     * Sets the calendar selection.
     * 
     * @param date the date to select
     */
    public void setCalendarDate(int date) {
        this.calendarComponent.selectDate(date);
    }

    /**
     * Checks if the calendar is under mouse focus.
     *
     * @return {@code true} if the calendar is under mouse focus, {@code false}
     *         otherwise
     */
    public boolean getIsCalendarFocused() {
        return this.calendarComponent.isFocusOwner();
    }

    /** Clears the calendar selection */
    public void clearCalendarSelection() {
        this.calendarComponent.resetSelection();
    }

    /**
     * Checks if the price modifier field is under mouse focus.
     *
     * @return {@code true} if the price modifier field is under mouse focus,
     *         {@code false} otherwise
     */
    public boolean getIsUpdatePriceModifierFieldFocused() {
        return this.priceModifierField.isFocusOwner();
    }

    /**
     * {@return the row index within the calendar given a point position}
     *
     * @param point the point to check, usually representing the mouse location
     */
    public int getCalendarRowAtPoint(Point point) {
        return this.calendarComponent.rowAtPoint(point);
    }

    /**
     * {@return the column index within the calendar given a point position}
     *
     * @param point the point to check, usually representing the mouse location
     */
    public int getCalendarColAtPoint(Point point) {
        return this.calendarComponent.columnAtPoint(point);
    }

    /**
     * Sets the {@link ManagePricesListener} for each component.
     * 
     * @param listener the listener to set
     */
    public void setListener(ManagePricesListener listener) {
        this.calendarComponent.setListener(listener);
        this.priceModifierField.addKeyListener(listener);
        this.priceUpdateButton.addActionListener(listener);
        this.priceUpdateButton.addKeyListener(listener);
    }
}

package src.controller.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import src.model.Hotel;
import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.component.Calendar;
import src.view.gui.delegate.ManageHotelDelegate;

/**
 * An extended {@link CalendarListener} meant to respond to events on the Manage
 * Prices Panel.
 */
public class ManagePricesListener extends CalendarListener
        implements ActionListener {
    /**
     * Initialize this listener.
     * 
     * @param reservationSystem the {@link ReservationSystem} to bind to this
     *                          listener
     * @param view              the {@link TopView} to bind to this listener
     */
    public ManagePricesListener(ReservationSystem reservationSystem,
            TopView view) {
        super(reservationSystem, view);
    }

    /**
     * Sets the content of the price modifier field, then updates the data
     * panel.
     * 
     * @see ManageHotelDelegate#setPriceModifierFieldText(String)
     * @see #setModifiedPriceText()
     */
    private void setPriceModifierFieldText() {
        int date = Calendar.toDate(this.getRow(), this.getCol());
        if (date < 1 || date > 31) {
            return;
        }
        view.getManageHotelDelegate().setPriceModifierFieldText(
                String.valueOf(
                        reservationSystem.getPriceModifier(
                                view.getSelectedIndex(), date)));
        setModifiedPriceText();
    }

    /**
     * Sets the content of the price modifier panel.
     * 
     * @see ManageHotelDelegate#setModifiedPriceText(String)
     */
    private void setModifiedPriceText() {
        int date = this.getDate();
        if (date < 1 || date > 31) {
            return;
        }

        int index = view.getSelectedIndex();
        double basePrice = reservationSystem.getBasePrice(index);
        double priceModifier = reservationSystem.getPriceModifier(index, date);

        view.getManageHotelDelegate().setModifiedPriceText(String.format("""
                <h2>Day %d</h2>
                %.2f * %.2f = %.2f""",
                this.getDate(),
                basePrice,
                priceModifier,
                basePrice * priceModifier));
    }

    /** {@inheritDoc} Sets the price modifier field text. */
    @Override
    protected void handleSelected(int row, int col) {
        setPriceModifierFieldText();
    }

    /** Accepts the enter key as a confirmation input. */
    private void handlePressEnterKey() {
        /* The price modifer was changed */
        if (view.getManageHotelDelegate()
                .getIsUpdatePriceModifierFieldFocused()) {
            updatePriceModifier();
        }
        /* The base price was changed */
        else if (view.getManageHotelDelegate()
                .getIsUpdateBasePriceFieldFocused()) {
            updateBasePrice();
        }
    }

    /** {@inheritDoc} Treated as if the selection was updated. */
    @Override
    protected void handleClicked(int row, int col) {
        setPriceModifierFieldText();
    }

    /** {@inheritDoc} Treated as if the selection was updated. */
    @Override
    protected void handleDragged(int row, int col) {
        setPriceModifierFieldText();
    }

    /** No behavior is defined for this implementation. {@inheritDoc} */
    @Override
    protected void handleMoved(int row, int col) {
        /* Implementation left blank */
    }

    /** No behavior is defined for this implementation. {@inheritDoc} */
    @Override
    protected void handleReleased(int row, int col) {
        /* Implementation left blank */
    }

    /** {@inheritDoc} Clears the calendar selection. */
    @Override
    protected void handleReleasedOutsideComponent() {
        view.getManageHotelDelegate().clearManagePricesCalendarSelection();
    }

    /** {@inheritDoc} Accept an enter key input. */
    @Override
    public void keyPressed(KeyEvent e) {
        if (view.getManageHotelDelegate().getIsManagePricesCalendarFocused()) {
            super.keyPressed(e);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            handlePressEnterKey();
        }
    }

    /**
     * {@inheritDoc} Handles receiving a click on the update base price or
     * update price modifier buttons.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Update base price":
                updateBasePrice();
                break;
            case "Update price modifier":
                updatePriceModifier();
                break;
        }
    }

    /**
     * Updates the base price for a night.
     * 
     * @see ReservationSystem#setBasePrice(int, int)
     */
    private void updateBasePrice() {
        int index = view.getSelectedIndex();
        double newBasePrice = Double.parseDouble(
                view.getManageHotelDelegate().getUpdateBasePriceFieldText());
        if (!view.confirmAction(
                "update the base price to " + newBasePrice + "?", null)) {
            return;
        }
        int result = reservationSystem.setBasePrice(index, newBasePrice);

        /* Handle result */
        switch (result) {
            case Hotel.SET_BASE_PRICE_SUCCESS:
                setModifiedPriceText();
                break;
            case Hotel.SET_BASE_PRICE_ERROR_LESS_THAN_MIN:
                view.basePriceUpdateLessThanMinimumError();
                break;
            case Hotel.SET_BASE_PRICE_ERROR_RESERVATIONS_EXIST:
                view.basePriceUpdateReservationsExistError();
                break;
        }
    }

    /**
     * Updates the price modifier for a night.
     * 
     * @see ReservationSystem#setPriceModifier(int, int, double)
     */
    private void updatePriceModifier() {
        int date = this.getDate();
        int index = view.getSelectedIndex();
        double newModifier = Double.parseDouble(
                view.getManageHotelDelegate().getPriceModifierFieldText());
        if (ReservationSystem.validateDate(date) &&
                !view.confirmAction(
                        "update the price modifier for day  " + date + " to "
                                + newModifier + "?",
                        "Update price modifier - " + date)) {
            return;
        }

        int status = reservationSystem.setPriceModifier(index, date,
                newModifier);

        switch (status) {
            case Hotel.SET_PRICE_MODIFIER_SUCCESS:
                view.getManageHotelDelegate().setManagePricesCalendarText(
                        date, String.format("%d: %.2f", date, newModifier));
                break;
            case Hotel.SET_PRICE_MODIFIER_RESERVATIONS_EXIST:
                view.showPriceModifierReservationsExistError();
                break;
            case Hotel.SET_PRICE_MODIFIER_OUT_OF_BOUNDS:
                view.showPriceModifierOutOfBoundsError();
                break;
        }

        this.setPriceModifierFieldText();
        view.getManageHotelDelegate().setPriceModifierCalendarDate(date);
    }

    /** {@inheritDoc} Sets the calendar selection. */
    @Override
    protected void setRowAndCol(MouseEvent e) {
        setRow(view.getManageHotelDelegate()
                .getPriceModifierCalendarRowFromMouse(e.getPoint()));
        setCol(view.getManageHotelDelegate()
                .getPriceModifierCalendarColFromMouse(e.getPoint()));
    }
}

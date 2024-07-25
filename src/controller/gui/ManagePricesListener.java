package src.controller.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import src.model.Hotel;
import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.component.Calendar;

/**
 * An extended {@link CalendarListener} meant to respond to events on the Manage
 * Prices Panel.
 */
public class ManagePricesListener extends CalendarListener
        implements ActionListener {

    public ManagePricesListener(ReservationSystem reservationSystem,
            TopView view) {
        super(reservationSystem, view);
    }

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

    @Override
    protected void handleSelected(int row, int col) {
        setPriceModifierFieldText();
    }

    private void handlePressEnterKey(int row, int col) {
        if (view.getManageHotelDelegate()
                .getIsUpdatePriceModifierFieldFocused()) {
            updatePriceModifier();
        } else if (view.getManageHotelDelegate()
                .getIsUpdateBasePriceFieldFocused()) {
            updateBasePrice();
        }
    }

    @Override
    protected void handleClicked(int row, int col) {
        setPriceModifierFieldText();
    }

    @Override
    protected void handleDragged(int row, int col) {
        setPriceModifierFieldText();
    }

    @Override
    protected void handleMoved(int row, int col) {
        /** No special behavior */
    }

    @Override
    protected void handleReleased(int row, int col) {
        /** No special behavior */
    }

    @Override
    protected void handleReleasedOutsideComponent() {
        view.getManageHotelDelegate().clearManagePricesCalendarSelection();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (view.getManageHotelDelegate().getIsManagePricesCalendarFocused()) {
            super.keyPressed(e);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            handlePressEnterKey(this.getRow(), this.getCol());
        }
    }

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

    public void updateBasePrice() {
        int index = view.getSelectedIndex();
        double newBasePrice = Double.parseDouble(
                view.getManageHotelDelegate().getUpdateBasePriceFieldText());
        int result = reservationSystem.setBasePrice(index, newBasePrice);
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

    public void updatePriceModifier() {
        int date = this.getDate();
        int index = view.getSelectedIndex();
        double newModifier = Double.parseDouble(
                view.getManageHotelDelegate().getPriceModifierFieldText());
        if (reservationSystem.setPriceModifier(index, date, newModifier)) {
            view.getManageHotelDelegate().setManagePricesCalendarText(
                    date, String.format("%d: %.2f", date, newModifier));
        } else {
            view.showPriceModifierError();
        }
        this.setPriceModifierFieldText();
        view.getManageHotelDelegate().setPriceModifierCalendarDate(date);
    }

    @Override
    protected void setRowAndCol(MouseEvent e) {
        setRow(view.getManageHotelDelegate()
                .getPriceModifierCalendarRowFromMouse(e.getPoint()));
        setCol(view.getManageHotelDelegate()
                .getPriceModifierCalendarColFromMouse(e.getPoint()));
    }
}

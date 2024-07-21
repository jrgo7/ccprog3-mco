package src.controller.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.component.Calendar;

public class ManagePricesListener extends CalendarListener
        implements ActionListener {

    public ManagePricesListener(ReservationSystem reservationSystem,
            TopView view) {
        super(reservationSystem, view);
    }

    private void setPriceModifierField() {
        int date = Calendar.toDate(row, col);
        if (date < 1 || date > 31) {
            return;
        }
        view.setPriceModifierField(
                String.valueOf(
                    reservationSystem.getPriceModifier(
                        view.getHotelListSelectedIndex(), date)));
        setModifiedPriceText();
    }

    private void setModifiedPriceText() {
        int date = Calendar.toDate(row, col);
        if (date < 1 || date > 31) {
            return;
        }

        int index = view.getHotelListSelectedIndex();
        double basePrice = reservationSystem.getBasePrice(index);
        double priceModifier = reservationSystem.getPriceModifier(index, date);

        view.setModifiedPriceText(String.format("""
                <div style="font-family: sans-serif">
                <h2>Day %d</h2>
                %.2f * %.2f = %.2f
                </div>""",
                Calendar.toDate(row, col),
                basePrice,
                priceModifier,
                basePrice * priceModifier));
    }

    @Override
    protected void handleSelected(int row, int col) {
        setPriceModifierField();
    }

    @Override
    protected void handlePressEnterKey(int row, int col) {
        if (view.getIsUpdatePriceModifierFieldFocused()) {
            updatePriceModifier();
        } else if (view.getIsUpdateBasePriceFieldFocused()) {
            updateBasePrice();
        }
    }

    @Override
    protected void handleClicked(int row, int col) {
        setPriceModifierField();
    }

    @Override
    protected void handleReleased(int row, int col) {

    }

    @Override
    protected void handleDragged(int row, int col) {
        setPriceModifierField();
    }

    @Override
    protected void handleMoved(int row, int col) {

    }

    @Override
    protected void handleReleasedOutsideComponent() {
        view.resetPriceModifierCalendarSelection();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (view.getIsPriceModifierCalendarFocused()) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER ||
                    e.getKeyCode() == KeyEvent.VK_TAB) {
                e.consume(); // Prevent enter/tab from changing calendar position
            } else {
                super.keyPressed(e);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            handlePressEnterKey(row, col);
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
        int index = view.getHotelListSelectedIndex();
        try {
            double newBasePrice = Double.parseDouble(view.getUpdateBasePriceText());
            if (reservationSystem.setBasePrice(index, newBasePrice)) {
                setModifiedPriceText();
            } else {
                view.invalidBasePriceUpdateError();
            }
        } catch (NumberFormatException e) {
            view.invalidBasePriceUpdateError();
        }
    }

    public void updatePriceModifier() {
        int row = this.getRow();
        int col = this.getCol();
        int date = Calendar.toDate(row, col);
        int index = view.getHotelListSelectedIndex();
        double newModifier = Double.parseDouble(view.getPriceModifierField());
        if (reservationSystem.setPriceModifier(index, date, newModifier)) {
            view.setManagePricesCalendarText(
                    date, String.format("%d: %.2f", date, newModifier));
        } else {
            view.showPriceModifierError();
        }
        this.setPriceModifierField();
        this.setRow(row);
        this.setCol(col);
        view.setPriceModiferCalendarDate(date);
    }
}

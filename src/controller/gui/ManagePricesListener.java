package src.controller.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import src.model.Hotel;
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
        int day = Calendar.toDay(row, col);
        if (day < 1 || day > 31) {
            return;
        }
        Hotel hotel = reservationSystem.getHotel(
                view.getHotelListSelectedIndex());
        view.setPriceModifierField(
                String.valueOf(hotel
                        .getPriceModifierOnNight(Calendar.toDay(row, col))));
        view.setModifiedPriceText(String.format("""
                <div style="font-family: sans-serif">
                <h2>Day %d</h2>
                %.2f * %.2f = %.2f
                </div>""",
                Calendar.toDay(row, col),
                hotel.getBasePrice(),
                hotel.getPriceModifierOnNight(day),
                hotel.getBasePrice() * hotel.getPriceModifierOnNight(day)));
    }

    @Override
    protected void handleSelected(int row, int col) {
        setPriceModifierField();
    }

    @Override
    protected void handlePressEnterKey(int row, int col) {
        if (!view.getIsPriceModifierCalendarFocused()) {
            updatePriceModifier();
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

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (view.getIsPriceModifierCalendarFocused()) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                e.consume(); // Prevent enter from changing calendar position
            }
            super.keyPressed(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updatePriceModifier();
    }

    public void updatePriceModifier() {
        int row = this.getRow();
        int col = this.getCol();
        int day = Calendar.toDay(row, col);
        double newModifier = Double.parseDouble(view.getPriceModifierField());
        Hotel hotel = reservationSystem
                .getHotel(view.getHotelListSelectedIndex());
        if (hotel.setPriceModifier(day, newModifier)) {
            view.setManagePricesCalendarText(
                    day, String.format("%d: %.2f", day, newModifier));
        } else {
            view.showPriceModifierError();
        }
        this.setPriceModifierField();
        this.setRow(row);
        this.setCol(col);
        view.setPriceModiferCalendarDay(day);
    }
}

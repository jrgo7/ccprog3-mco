package src.view.gui;

import src.view.gui.panel.ManageHotelPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ManageHotelDelegate {
    private ManageHotelPanel manageHotelPanel;

    public ManageHotelDelegate(ManageHotelPanel manageHotelPanel) {
        this.manageHotelPanel = manageHotelPanel;
    }

    public void updateManageRoomData(String data, ArrayList<Integer> availableDates) {
        this.manageHotelPanel.updateRoomData(data, availableDates);
    }

    public void updateReservationList(String[] data) {
        ArrayList<String> dataAsList = new ArrayList<>(Arrays.asList(data));
        this.manageHotelPanel.updateReservationList(dataAsList);
    }

    public int getManageReservationSelectedIndex() {
        return this.manageHotelPanel.getManageReservationSelectedIndex();
    }

    public void setManageReservationData(String data) {
        this.manageHotelPanel.updateReservationData(data);
    }

    public void setManageReservationVisible(boolean visible) {
        this.manageHotelPanel.setManageReservationVisible(visible);
    }

    // Manage hotel delegations

    public int getManageRoomSelectedIndex() {
        return this.manageHotelPanel.getManageRoomSelectedIndex();
    }

    public void setManageHotelReservationData(String data) {
        this.manageHotelPanel.updateReservationData(data);
    }

    public String getRenameHotelText() {
        return this.manageHotelPanel.getRenameHotelText();
    }

    public void setRenameHotelText(String name) {
        this.manageHotelPanel.setRenameHotelText(name);
    }

    public String getUpdateBasePriceText() {
        return this.manageHotelPanel.getUpdateBasePriceText();
    }

    public void setUpdateBasePriceText(String basePrice) {
        this.manageHotelPanel.setUpdateBasePriceText(basePrice);
    }

    public void setManagePricesCalendarText(int date, String text) {
        this.manageHotelPanel.setManagePricesCalendarText(date, text);
    }

    public String getPriceModifierField() {
        return this.manageHotelPanel.getPriceModifierField();
    }

    public void setPriceModifierField(String text) {
        this.manageHotelPanel.setPriceModifierField(text);
    }

    public void setModifiedPriceText(String text) {
        this.manageHotelPanel.setModifiedPriceText(text);
    }

    public void setPriceModifierCalendarDate(int date) {
        this.manageHotelPanel.setPriceModiferCalendarDate(date);
    }

    public int getPriceModifierCalendarRowFromMouse(Point point) {
        return this.manageHotelPanel.getCalendarRowAtPoint(point);
    }

    public int getPriceModifierCalendarColFromMouse(Point point) {
        return this.manageHotelPanel.getCalendarColAtPoint(point);
    }

    public boolean getIsPriceModifierCalendarFocused() {
        return this.manageHotelPanel.getIsPriceModifierCalendarFocused();
    }

    public void resetPriceModifierCalendarSelection() {
        this.manageHotelPanel.resetPriceModifierCalendarSelection();
    }

    public boolean getIsUpdatePriceModifierFieldFocused() {
        return this.manageHotelPanel.getIsUpdatePriceModifierFieldFocused();
    }

    public boolean getIsUpdateBasePriceFieldFocused() {
        return this.manageHotelPanel.getIsUpdateBasePriceFieldFocused();
    }

    public void setManageRoomVisible(boolean visible) {
        this.manageHotelPanel.setManageRoomPanelVisible(visible);
    }

    public void clearSelectedIndex() {
        this.manageHotelPanel.clearRoomListSelection();
    }
}

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
        this.manageHotelPanel.setRoomData(data, availableDates);
    }

    public void setReservationList(String[] data) {
        ArrayList<String> dataAsList = new ArrayList<>(Arrays.asList(data));
        this.manageHotelPanel.setReservationList(dataAsList);
    }

    public int getSelectedReservationIndex() {
        return this.manageHotelPanel.getSelectedReservationIndex();
    }

    public void setManageReservationData(String data) {
        this.manageHotelPanel.setReservationData(data);
    }

    public void setReservationDataVisible(boolean visible) {
        this.manageHotelPanel.setReservationDataVisible(visible);
    }

    // Manage hotel delegations

    public int getSelectedRoomIndex() {
        return this.manageHotelPanel.getSelectedRoomIndex();
    }

    public void setManageHotelReservationData(String data) {
        this.manageHotelPanel.setReservationData(data);
    }

    public String getRenameHotelFieldText() {
        return this.manageHotelPanel.getRenameHotelFieldText();
    }

    public void setRenameHotelFieldText(String name) {
        this.manageHotelPanel.setRenameHotelFieldText(name);
    }

    public String getUpdateBasePriceFieldText() {
        return this.manageHotelPanel.getUpdateBasePriceFieldText();
    }

    public void setUpdateBasePriceFieldText(String basePrice) {
        this.manageHotelPanel.setUpdateBasePriceFieldText(basePrice);
    }

    public void setManagePricesCalendarText(int date, String text) {
        this.manageHotelPanel.setManagePricesCalendarText(date, text);
    }

    public String getPriceModifierFieldText() {
        return this.manageHotelPanel.getPriceModifierFieldText();
    }

    public void setPriceModifierFieldText(String text) {
        this.manageHotelPanel.setPriceModifierFieldText(text);
    }

    public void setModifiedPriceText(String text) {
        this.manageHotelPanel.setModifiedPriceText(text);
    }

    public void setPriceModifierCalendarDate(int date) {
        this.manageHotelPanel.setManagePricesCalendarSelection(date);
    }

    public int getPriceModifierCalendarRowFromMouse(Point point) {
        return this.manageHotelPanel.getCalendarRowAtPoint(point);
    }

    public int getPriceModifierCalendarColFromMouse(Point point) {
        return this.manageHotelPanel.getCalendarColAtPoint(point);
    }

    public boolean getIsManagePricesCalendarFocused() {
        return this.manageHotelPanel.getIsManagePricesCalendarFocused();
    }

    public void clearManagePricesCalendarSelection() {
        this.manageHotelPanel.clearManagePricesCalendarSelection();
    }

    public boolean getIsUpdatePriceModifierFieldFocused() {
        return this.manageHotelPanel.getIsUpdatePriceModifierFieldFocused();
    }

    public boolean getIsUpdateBasePriceFieldFocused() {
        return this.manageHotelPanel.getIsUpdateBasePriceFieldFocused();
    }

    public void setManageRoomVisible(boolean visible) {
        this.manageHotelPanel.setRoomDataVisible(visible);
    }

    public void clearSelectedIndex() {
        this.manageHotelPanel.clearRoomListSelection();
    }
}

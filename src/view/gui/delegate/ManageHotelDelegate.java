package src.view.gui.delegate;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import src.model.ReservationSystem;
import src.model.Room;
import src.view.gui.TopView;
import src.view.gui.panel.ManageHotelPanel;

/**
 * Represents a delegate class that assists {@link TopView} in passing data to
 * its composite {@link ManageHotelPanel}.
 */
public class ManageHotelDelegate {
    /** The panel that this delegate communicates with. */
    private ManageHotelPanel manageHotelPanel;

    /** Initialies the panel. */
    public ManageHotelDelegate(ManageHotelPanel manageHotelPanel) {
        this.manageHotelPanel = manageHotelPanel;
    }

    /* Edit hotel data panel */

    /**
     * {@return the text in the rename hotel field}
     *
     * @see ManageHotelPanel#getRenameHotelFieldText
     */
    public String getRenameHotelFieldText() {
        return this.manageHotelPanel.getRenameHotelFieldText();
    }

    /**
     * Sets the text for the rename hotel field.
     *
     * @param name the text to set
     * @see ManageHotelPanel#setRenameHotelFieldText
     */
    public void setRenameHotelFieldText(String name) {
        this.manageHotelPanel.setRenameHotelFieldText(name);
    }

    /**
     * {@return the text in the update base price field}
     *
     * @see ManageHotelPanel#getUpdateBasePriceFieldText
     */
    public String getUpdateBasePriceFieldText() {
        return this.manageHotelPanel.getUpdateBasePriceFieldText();
    }

    /**
     * Sets the text for the update base price field.
     *
     * @param basePrice the text to set
     * @see ManageHotelPanel#setUpdateBasePriceFieldText
     */
    public void setUpdateBasePriceFieldText(String basePrice) {
        this.manageHotelPanel.setUpdateBasePriceFieldText(basePrice);
    }

    /* Manage rooms panel */

    /**
     * Sets the room data with the given data and available dates.
     *
     * @param data           the room data string to set
     * @param availableDates an {@link ArrayList} of available dates. Usually
     *                       obtained with {@link Room#getAvailableDates()}
     * @see ManageHotelPanel#setRoomData
     */
    public void setRoomData(String data, ArrayList<Integer> availableDates) {
        this.manageHotelPanel.setRoomData(data, availableDates);
    }

    /**
     * {@return the index of the selected room}
     *
     * @see ManageHotelPanel#getSelectedRoomIndex
     */
    public int getSelectedRoomIndex() {
        return this.manageHotelPanel.getSelectedRoomIndex();
    }

    /**
     * Sets the visibility of the room data panel found in the Manage Rooms
     * panel.
     *
     * @param visible {@code true} to display the panel, {@code false} otherwise
     * @see ManageHotelPanel#setRoomDataVisible
     */
    public void setManageRoomVisible(boolean visible) {
        this.manageHotelPanel.setRoomDataVisible(visible);
    }

    /**
     * Clears the room list selection.
     *
     * @see ManageHotelPanel#clearRoomListSelection
     */
    public void clearSelectedRoomIndex() {
        this.manageHotelPanel.clearRoomListSelection();
    }

    /* Manage reservations subpanel */

    /**
     * Sets the reservation list with the given data.
     *
     * @param data the array containing string representations of active
     *             reservations. Usually obtained through
     *             {@link ReservationSystem#getReservationNames(int)}
     * @see ManageHotelPanel#setReservationList
     */
    public void setReservationList(String[] data) {
        ArrayList<String> dataAsList = new ArrayList<>(Arrays.asList(data));
        this.manageHotelPanel.setReservationList(dataAsList);
    }

    /**
     * {@return the index of the selected reservation}
     *
     * @see ManageHotelPanel#getSelectedReservationIndex
     */
    public int getSelectedReservationIndex() {
        return this.manageHotelPanel.getSelectedReservationIndex();
    }

    /**
     * Sets the text in the reservation data panel found in the Manage
     * Reservations panel.
     *
     * @param data the reservation data to set.
     * @see ManageHotelPanel#setReservationData
     */
    public void setManageReservationData(String data) {
        this.manageHotelPanel.setReservationData(data);
    }

    /**
     * Sets the visibility of the reservation data panel found in the Manage
     * Reservations panel.
     *
     * @param visible {@code true} to display the panel, {@code false} otherwise
     * @see ManageHotelPanel#setReservationDataVisible
     */
    public void setReservationDataVisible(boolean visible) {
        this.manageHotelPanel.setReservationDataVisible(visible);
    }

    /* Manage prices subpanel */

    /**
     * Sets the text for a cell corresponding to a givven date in the Manage
     * Prices calendar.
     *
     * @param date the date to set
     * @param text the text to set
     * @see ManageHotelPanel#setManagePricesCalendarText
     */
    public void setManagePricesCalendarText(int date, String text) {
        this.manageHotelPanel.setManagePricesCalendarText(date, text);
    }

    /**
     * {@return the text in the price modifier field}
     *
     * @see ManageHotelPanel#getPriceModifierFieldText
     */
    public String getPriceModifierFieldText() {
        return this.manageHotelPanel.getPriceModifierFieldText();
    }

    /**
     * Sets the text for the price modifier field.
     *
     * @param text the text to set
     * @see ManageHotelPanel#setPriceModifierFieldText
     */
    public void setPriceModifierFieldText(String text) {
        this.manageHotelPanel.setPriceModifierFieldText(text);
    }

    /**
     * Sets the text for the modified price field.
     *
     * @param text the text to set
     * @see ManageHotelPanel#setModifiedPriceText
     */
    public void setModifiedPriceText(String text) {
        this.manageHotelPanel.setModifiedPriceText(text);
    }

    /**
     * Sets the Manage Prices calendar selection to a given date.
     *
     * @param date the date to set
     * @see ManageHotelPanel#setManagePricesCalendarSelection
     */
    public void setPriceModifierCalendarDate(int date) {
        this.manageHotelPanel.setManagePricesCalendarSelection(date);
    }

    /**
     * {@return the row index within the Manage Prices calendar given a point
     * position}
     *
     * @param point the point to check, usually representing the mouse location
     * @see ManageHotelPanel#getCalendarRowAtPoint
     */
    public int getPriceModifierCalendarRowFromMouse(Point point) {
        return this.manageHotelPanel.getCalendarRowAtPoint(point);
    }

    /**
     * {@return the column index within the Manage Prices calendar given a point
     * position}
     *
     * @param point the point to check, usually representing the mouse location
     * @see ManageHotelPanel#getCalendarColAtPoint
     */
    public int getPriceModifierCalendarColFromMouse(Point point) {
        return this.manageHotelPanel.getCalendarColAtPoint(point);
    }

    /**
     * Checks if the Managae Prices calendar is under mouse focus.
     *
     * @return {@code true} if the calendar is under mouse focus, {@code false}
     *         otherwise
     * @see ManageHotelPanel#getIsManagePricesCalendarFocused
     */
    public boolean getIsManagePricesCalendarFocused() {
        return this.manageHotelPanel.getIsManagePricesCalendarFocused();
    }

    /**
     * Clears the Managae Prices calendar selection.
     *
     * @see ManageHotelPanel#clearManagePricesCalendarSelection
     */
    public void clearManagePricesCalendarSelection() {
        this.manageHotelPanel.clearManagePricesCalendarSelection();
    }

    /**
     * Checks if the update price modifier field is under mouse focus.
     *
     * @return {@code true} if the update price modifier field is under mouse
     *         focus, {@code false} otherwise
     * @see ManageHotelPanel#getIsUpdatePriceModifierFieldFocused
     */
    public boolean getIsUpdatePriceModifierFieldFocused() {
        return this.manageHotelPanel.getIsUpdatePriceModifierFieldFocused();
    }

    /**
     * Checks if the update base price field is under mouse focus.
     *
     * @return {@code true} if the update base price field is under mouse focus,
     *         {@code false} otherwise
     * @see ManageHotelPanel#getIsUpdateBasePriceFieldFocused
     */
    public boolean getIsUpdateBasePriceFieldFocused() {
        return this.manageHotelPanel.getIsUpdateBasePriceFieldFocused();
    }

}

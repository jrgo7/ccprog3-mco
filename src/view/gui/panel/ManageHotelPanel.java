package src.view.gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import src.view.gui.component.StyledTextField;

import src.controller.gui.HotelListListener;
import src.controller.gui.ManagePricesListener;
import src.controller.gui.ManageReservationListener;
import src.controller.gui.ManageRoomListener;
import src.controller.gui.RenameHotelListener;
import src.model.ReservationSystem;
import src.model.Room;
import src.view.gui.TopView;
import src.view.gui.component.DecimalDocument;
import src.view.gui.component.StyledButton;
import src.view.gui.component.GUIFactory;
import src.view.gui.component.StyledLabel;
import src.view.gui.component.StyledPanel;
import src.view.gui.component.StyledTabbedPane;
import src.view.gui.subpanel.ManagePricesPanel;
import src.view.gui.subpanel.ManageReservationsPanel;
import src.view.gui.subpanel.ManageRoomsPanel;

/** Represents the Manage Hotel panel. */
public class ManageHotelPanel extends StyledPanel {
    /** A {@link StyledTabbedPane} containing subpanels. */
    private StyledTabbedPane subpanels;

    /** Represents the Manage Rooms subpanel. */
    private ManageRoomsPanel manageRoomsSubpanel;

    /** Represents the Manage Reservations subpanel. */
    private ManageReservationsPanel manageReservationsSubpanel;

    /** Represents the Manage Prices subpanel. */
    private ManagePricesPanel managePricesSubpanel;

    /** Text field for renaming the hotel. */
    private StyledTextField renameHotelField;

    /** Text field for updating the base price. */
    private StyledTextField basePriceField;

    /** Button for renaming the hotel. */
    private StyledButton renameHotelButton;

    /** Button for updating the base price. */
    private StyledButton updateBasePriceButton;

    /** Button for removing the hotel. */
    private StyledButton removeHotelButton;

    /**
     * Initializes the hotel info subpanel, the subpanel tabs, and the remove
     * hotel button.
     */
    public ManageHotelPanel() {
        this.setLayout(new BorderLayout());

        this.initializeEditInfoPanel();
        this.initializeSubpanels();

        StyledPanel removeHotelPanel = new StyledPanel();

        removeHotelPanel.setLayout(new BorderLayout());
        removeHotelButton = GUIFactory.createDestructiveButton(
                "Remove hotel");
        removeHotelPanel.add(removeHotelButton, BorderLayout.WEST);

        this.add(removeHotelPanel, BorderLayout.SOUTH);
    }

    /** Initializes the subpanels. */
    public void initializeSubpanels() {
        subpanels = new StyledTabbedPane();

        this.manageRoomsSubpanel = new ManageRoomsPanel();
        subpanels.addTab("Manage rooms", this.manageRoomsSubpanel);

        this.manageReservationsSubpanel = new ManageReservationsPanel();
        subpanels.addTab("Manage reservations",
                this.manageReservationsSubpanel);

        this.managePricesSubpanel = new ManagePricesPanel();
        subpanels.addTab("Manage prices", this.managePricesSubpanel);

        this.add(subpanels, BorderLayout.CENTER);
    }

    /** Initializes the panel that contains options for editing hotel data. */
    public void initializeEditInfoPanel() {
        StyledPanel editInfoPanel = new StyledPanel();
        editInfoPanel.setLayout(new GridLayout(2, 3));

        StyledLabel renameLabel = new StyledLabel("Rename hotel:");
        editInfoPanel.add(renameLabel);

        this.renameHotelField = new StyledTextField();
        editInfoPanel.add(this.renameHotelField);

        this.renameHotelButton = GUIFactory
                .createButton("Rename hotel");
        editInfoPanel.add(this.renameHotelButton);

        StyledLabel basePriceLabel = new StyledLabel("Update base price:");
        editInfoPanel.add(basePriceLabel);

        /* TODO: Maybe abandon DecimalDocument for JSpinner */
        this.basePriceField = new StyledTextField();
        this.basePriceField.setDocument(new DecimalDocument());

        editInfoPanel.add(this.basePriceField);

        this.updateBasePriceButton = GUIFactory
                .createButton("Update base price");
        editInfoPanel.add(this.updateBasePriceButton);

        this.add(editInfoPanel, BorderLayout.NORTH);
    }

    /**
     * Sets a listener for renaming the hotel.
     * 
     * @param listener the listener to set
     */
    public void setRenameHotelListener(RenameHotelListener listener) {
        this.renameHotelField.addKeyListener(listener);
        this.renameHotelButton.addActionListener(listener);
        this.renameHotelButton.addKeyListener(listener);
    }

    /**
     * Sets a listener for managing hotel prices.
     * 
     * @param listener the listener to set
     */
    public void setManagePricesListener(ManagePricesListener listener) {
        this.basePriceField.addKeyListener(listener);
        this.updateBasePriceButton.addActionListener(listener);
        this.updateBasePriceButton.addKeyListener(listener);
        this.managePricesSubpanel.setListener(listener);
    }

    /**
     * Sets a listener for managing rooms.
     * 
     * @param listener the listener to set
     */
    public void setManageRoomsListener(ManageRoomListener listener) {
        this.manageRoomsSubpanel.setRoomListListener(listener);
        this.manageRoomsSubpanel.setRemoveButtonListener(listener);
    }

    /**
     * Sets a listener for managing reservations.
     * 
     * @param listener the listener to set
     */
    public void setManageReservationsListener(
            ManageReservationListener listener) {
        this.manageReservationsSubpanel.setListener(listener);
        this.manageReservationsSubpanel
                .setRemoveButtonListener((ActionListener) listener);
    }

    /**
     * Sets a listener for the remove hotel button. This usually identical to
     * the listener for the hotel list.
     * 
     * @param listener the listener to set
     * @see TopView#setTopViewHotelListListener(HotelListListener)
     */
    public void setRemoveHotelButtonListener(HotelListListener listener) {
        this.removeHotelButton.addActionListener(listener);
    }

    /* Edit hotel info panel */

    /**
     * {@return the text in the rename hotel field}
     */
    public String getRenameHotelFieldText() {
        return this.renameHotelField.getText();
    }

    /**
     * Sets the text in the rename hotel field.
     *
     * @param text the text to set
     */
    public void setRenameHotelFieldText(String text) {
        this.renameHotelField.setText(text);
    }

    /**
     * {@return the text from the update base price field}
     */
    public String getUpdateBasePriceFieldText() {
        return this.basePriceField.getText();
    }

    /**
     * Sets the text in the update base price field.
     *
     * @param text the text to set
     */
    public void setUpdateBasePriceFieldText(String text) {
        this.basePriceField.setText(text);
    }

    /* Manage rooms subpanel */

    /**
     * Sets the room list with the given data.
     *
     * @param data an {@link ArrayList} of room names to set
     * @see ManageRoomsPanel#setRoomList
     */
    public void setRoomList(ArrayList<String> data) {
        this.manageRoomsSubpanel.setRoomList(data);
    }

    /**
     * Sets the room data panel with the given data and available dates.
     *
     * @param data           the room data string to set
     * @param availableDates an {@link ArrayList} of available dates. Usually
     *                       obtained with {@link Room#getAvailableDates()}
     * @see ManageRoomsPanel#setRoomData
     */
    public void setRoomData(String data, ArrayList<Integer> availableDates) {
        this.manageRoomsSubpanel.setRoomData(data, availableDates);
    }

    /**
     * Clears the room list selection.
     *
     * @see ManageRoomsPanel#clearRoomListSelection
     */
    public void clearRoomListSelection() {
        this.manageRoomsSubpanel.clearRoomListSelection();
    }

    /**
     * {@return the index of the selected room}
     * 
     * @see ManageRoomsPanel#getSelectedRoomIndex
     */
    public int getSelectedRoomIndex() {
        return this.manageRoomsSubpanel.getSelectedRoomIndex();
    }

    /**
     * Sets the visibility of the room data panel.
     *
     * @param visible {@code true} to display the panel, {@code false} otherwise
     * @see ManageRoomsPanel#setWrapperVisible
     */
    public void setRoomDataVisible(boolean visible) {
        this.manageRoomsSubpanel.setWrapperVisible(visible);
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
        this.managePricesSubpanel.setCalendarText(date, text);
    }

    /**
     * Sets the Manage Prices calendar selection to a given date.
     *
     * @param date the date to set
     * @see ManagePricesPanel#setCalendarDate
     */
    public void setManagePricesCalendarSelection(int date) {
        this.managePricesSubpanel.setCalendarDate(date);
    }

    /**
     * {@return the text in the price modifier field}
     * 
     * @see ManagePricesPanel#getPriceModifierFieldText
     */
    public String getPriceModifierFieldText() {
        return this.managePricesSubpanel.getPriceModifierFieldText();
    }

    /**
     * Sets the text in the price modifier field.
     *
     * @param text the text to set
     * @see ManagePricesPanel#setPriceModifierFieldText
     */
    public void setPriceModifierFieldText(String text) {
        this.managePricesSubpanel.setPriceModifierFieldText(text);
    }

    /**
     * Sets the modified price label text. Follows the following format:
     * {@code Date X: basePrice * priceModifier = newPrice}
     *
     * @param text the text to set
     * @see ManagePricesPanel#setModifiedPriceText
     */
    public void setModifiedPriceText(String text) {
        this.managePricesSubpanel.setModifiedPriceText(text);
    }

    /**
     * Checks if the Manage Prices calendar is under mouse focus.
     *
     * @return {@code true} if the calendar is under mouse focus, {@code false}
     *         otherwise
     * @see ManagePricesPanel#getIsCalendarFocused
     */
    public boolean getIsManagePricesCalendarFocused() {
        return this.managePricesSubpanel.getIsCalendarFocused();
    }

    /**
     * Clears the Manage Prices calendar selection.
     *
     * @see ManagePricesPanel#clearCalendarSelection
     */
    public void clearManagePricesCalendarSelection() {
        this.managePricesSubpanel.clearCalendarSelection();
    }

    /**
     * Checks if the update price modifier field is under mouse focus.
     *
     * @return {@code true} if the update price modifier field is under mouse
     *         focus, {@code false} otherwise
     * @see ManagePricesPanel#getIsUpdatePriceModifierFieldFocused
     */
    public boolean getIsUpdatePriceModifierFieldFocused() {
        return this.managePricesSubpanel.getIsUpdatePriceModifierFieldFocused();
    }

    /**
     * Checks if the update base price field is under mouse focus.
     *
     * @return {@code true} if the update base price field is under mouse focus,
     *         {@code false} otherwise
     */
    public boolean getIsUpdateBasePriceFieldFocused() {
        return this.basePriceField.isFocusOwner();
    }

    /**
     * {@return the row index within the calendar given a point position}
     *
     * @param point the point to check, usually representing the mouse location
     * @see ManagePricesPanel#getCalendarRowAtPoint
     */
    public int getCalendarRowAtPoint(Point point) {
        return this.managePricesSubpanel.getCalendarRowAtPoint(point);
    }

    /**
     * {@return the column index within the calendar given a point position}
     *
     * @param point the point to check, usually representing the mouse location
     * @see ManagePricesPanel#getCalendarColAtPoint
     */
    public int getCalendarColAtPoint(Point point) {
        return this.managePricesSubpanel.getCalendarColAtPoint(point);
    }

    /* Manage reservations subpanel */

    /**
     * Sets the reservation list with the given data.
     *
     * @param list the array containing string representations of active
     *             reservations. Usually obtained through
     *             {@link ReservationSystem#getReservationNames(int)}
     * @see ManageReservationsPanel#setReservationList
     */
    public void setReservationList(ArrayList<String> list) {
        this.manageReservationsSubpanel.setReservationList(list);
    }

    /**
     * Sets the text in the reservation data panel.
     *
     * @param data the text to set
     * @see ManageReservationsPanel#setReservationData
     */
    public void setReservationData(String data) {
        this.manageReservationsSubpanel.setReservationData(data);
    }

    /**
     * {@return the index of the selected reservation}
     * 
     * @see ManageReservationsPanel#getSelectedIndex
     */
    public int getSelectedReservationIndex() {
        return this.manageReservationsSubpanel.getSelectedIndex();
    }

    /**
     * Sets the visibility of the reservation data panel.
     *
     * @param visible {@code true} to display the panel, {@code false} otherwise
     * @see ManageReservationsPanel#setWrapperVisible
     */
    public void setReservationDataVisible(boolean visible) {
        this.manageReservationsSubpanel.setWrapperVisible(visible);
    }

    /**
     * Clears the reservation list selection.
     * 
     * @see ManageReservationsPanel#clearSelectedIndex
     */
    public void clearReservationListSelection() {
        this.manageReservationsSubpanel.clearSelectedIndex();
    }
}

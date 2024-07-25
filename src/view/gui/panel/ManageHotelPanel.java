package src.view.gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;

import src.controller.gui.ManagePricesListener;
import src.controller.gui.ManageReservationListener;
import src.controller.gui.ManageRoomListener;
import src.controller.gui.RenameHotelListener;
import src.view.gui.component.DecimalDocument;
import src.view.gui.component.StyledButton;
import src.view.gui.component.StyledButtonFactory;
import src.view.gui.component.StyledLabel;
import src.view.gui.component.StyledPanel;
import src.view.gui.component.StyledTabbedPane;
import src.view.gui.subpanel.ManagePricesPanel;
import src.view.gui.subpanel.ManageReservationsPanel;
import src.view.gui.subpanel.ManageRoomsPanel;

public class ManageHotelPanel extends StyledPanel {
    private StyledTabbedPane subpanels;

    private ManageRoomsPanel manageRoomsSubpanel;
    private ManageReservationsPanel manageReservationsSubpanel;
    private ManagePricesPanel managePricesSubpanel;

    private JTextField renameHotelField;
    private JTextField basePriceField;

    private StyledButton renameHotelButton;
    private StyledButton updateBasePriceButton;
    private StyledButton removeHotelButton;

    public ManageHotelPanel() {
        this.setLayout(new BorderLayout());

        this.initializeEditInfoPanel();
        this.initializeSubpanels();

        StyledPanel removeHotelPanel = new StyledPanel();

        removeHotelPanel.setLayout(new BorderLayout());
        removeHotelButton = StyledButtonFactory
                .createDestructiveButton("Remove hotel");
        removeHotelPanel.add(removeHotelButton, BorderLayout.WEST);

        this.add(removeHotelPanel, BorderLayout.SOUTH);
    }

    /** Initializes the subpanels. */
    public void initializeSubpanels() {
        subpanels = new StyledTabbedPane();

        this.manageRoomsSubpanel = new ManageRoomsPanel();
        subpanels.add("Manage rooms", this.manageRoomsSubpanel);

        this.manageReservationsSubpanel = new ManageReservationsPanel();
        subpanels.add("Manage reservations", this.manageReservationsSubpanel);

        this.managePricesSubpanel = new ManagePricesPanel();
        subpanels.add("Manage prices", this.managePricesSubpanel);

        this.add(subpanels, BorderLayout.CENTER);
    }

    /** Initializes the panel that contains options for editing hotel data. */
    public void initializeEditInfoPanel() {
        StyledPanel editInfoPanel = new StyledPanel();
        editInfoPanel.setLayout(new GridLayout(2, 3));

        StyledLabel renameLabel = new StyledLabel("Rename hotel:");
        editInfoPanel.add(renameLabel);

        this.renameHotelField = new JTextField();
        editInfoPanel.add(this.renameHotelField);

        this.renameHotelButton = StyledButtonFactory
                .createButton("Rename hotel");
        editInfoPanel.add(this.renameHotelButton);

        StyledLabel basePriceLabel = new StyledLabel("Update base price:");
        editInfoPanel.add(basePriceLabel);

        /* TODO: Maybe abandon DecimalDocument for JSpinner */
        this.basePriceField = new JTextField();
        this.basePriceField.setDocument(new DecimalDocument());

        editInfoPanel.add(this.basePriceField);

        this.updateBasePriceButton = StyledButtonFactory
                .createButton("Update base price");
        editInfoPanel.add(this.updateBasePriceButton);

        this.add(editInfoPanel, BorderLayout.NORTH);
    }

    /**
     * Sets a listener for renaming the hotel.
     * 
     * @param listener
     */
    public void setRenameHotelListener(RenameHotelListener listener) {
        this.renameHotelField.addKeyListener(listener);
        this.renameHotelButton.addActionListener(listener);
        this.renameHotelButton.addKeyListener(listener);
    }

    /**
     * Sets a listener for managing hotel prices.
     * 
     * @param listener
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
     * @param listener
     */
    public void setManageRoomsListener(ManageRoomListener listener) {
        this.manageRoomsSubpanel.setRoomListListener(listener);
        this.manageRoomsSubpanel.setRemoveButtonListener(listener);
    }

    /**
     * Sets a listener for managing reservations.
     * 
     * @param listener
     */
    public void setManageReservationsListener(
            ManageReservationListener listener) {
        this.manageReservationsSubpanel.setListener(listener);
        this.manageReservationsSubpanel
                .setRemoveButtonListener((ActionListener) listener);
    }

    /* Edit hotel info panel */

    public String getRenameHotelFieldText() {
        return this.renameHotelField.getText();
    }

    public void setRenameHotelFieldText(String text) {
        this.renameHotelField.setText(text);
    }

    public String getUpdateBasePriceFieldText() {
        return this.basePriceField.getText();
    }

    public void setUpdateBasePriceFieldText(String text) {
        this.basePriceField.setText(text);
    }

    /* Manage rooms subpanel */

    public void setRoomList(ArrayList<String> data) {
        this.manageRoomsSubpanel.setRoomList(data);
    }

    public void setRoomData(String data, ArrayList<Integer> availableDates) {
        this.manageRoomsSubpanel.setRoomData(data, availableDates);
    }

    public void clearRoomListSelection() {
        this.manageRoomsSubpanel.clearRoomListSelection();
    }

    public int getSelectedRoomIndex() {
        return this.manageRoomsSubpanel.getSelectedRoomIndex();
    }

    public void setRoomDataVisible(boolean visible) {
        this.manageRoomsSubpanel.setWrapperVisible(visible);
    }

    /* Manage prices subpanel */

    public void setManagePricesCalendarText(int date, String text) {
        this.managePricesSubpanel.setCalendarText(date, text);
    }

    public void setManagePricesCalendarSelection(int date) {
        this.managePricesSubpanel.setCalendarDate(date);
    }

    public String getPriceModifierFieldText() {
        return this.managePricesSubpanel.getPriceModifierFieldText();
    }

    public void setPriceModifierFieldText(String text) {
        this.managePricesSubpanel.setPriceModifierFieldText(text);
    }

    public void setModifiedPriceText(String text) {
        this.managePricesSubpanel.setModifiedPriceText(text);
    }

    public boolean getIsManagePricesCalendarFocused() {
        return this.managePricesSubpanel.getIsCalendarFocused();
    }

    public void clearManagePricesCalendarSelection() {
        this.managePricesSubpanel.clearCalendarSelection();
    }

    public boolean getIsUpdatePriceModifierFieldFocused() {
        return this.managePricesSubpanel.getIsUpdatePriceModifierFieldFocused();
    }

    public boolean getIsUpdateBasePriceFieldFocused() {
        return this.basePriceField.isFocusOwner();
    }

    public int getCalendarRowAtPoint(Point point) {
        return this.managePricesSubpanel.getCalendarRowAtPoint(point);
    }

    public int getCalendarColAtPoint(Point point) {
        return this.managePricesSubpanel.getCalendarColAtPoint(point);
    }

    /* Manage reservations subpanel */

    public void setReservationList(ArrayList<String> list) {
        this.manageReservationsSubpanel.setReservationList(list);
    }

    public void setReservationData(String data) {
        this.manageReservationsSubpanel.setReservationData(data);
    }

    public int getSelectedReservationIndex() {
        return this.manageReservationsSubpanel.getSelectedIndex();
    }

    public void setReservationDataVisible(boolean visible) {
        this.manageReservationsSubpanel.setWrapperVisible(visible);
    }
}

package src.view.gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import src.view.gui.component.StyledButton;
import src.view.gui.component.StyledLabel;
import src.view.gui.component.StyledPanel;
import src.view.gui.component.StyledTabbedPane;
import javax.swing.JTextField;

import src.controller.gui.ManagePricesListener;
import src.controller.gui.ManageReservationListener;
import src.controller.gui.ManageRoomListener;
import src.controller.gui.RenameHotelListener;
import src.view.gui.component.DecimalDocument;
import src.view.gui.component.FontCollection;
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

    public ManageHotelPanel() {
        this.setLayout(new BorderLayout());

        StyledPanel infoEditPanel = new StyledPanel();
        infoEditPanel.setLayout(new GridLayout(2, 3));

        StyledLabel renameLabel = new StyledLabel("Rename hotel:");
        infoEditPanel.add(renameLabel);

        renameHotelField = new JTextField();
        infoEditPanel.add(renameHotelField);

        renameHotelButton = new StyledButton("Rename hotel");
        infoEditPanel.add(renameHotelButton);

        StyledLabel basePriceLabel = new StyledLabel("Update base price:");
        infoEditPanel.add(basePriceLabel);

        basePriceField = new JTextField();
        basePriceField.setDocument(new DecimalDocument()); // TODO maybe a spinner; abandon DecimalDocument
        infoEditPanel.add(basePriceField);

        updateBasePriceButton = new StyledButton("Update base price");
        infoEditPanel.add(updateBasePriceButton);

        this.add(infoEditPanel, BorderLayout.NORTH);

        subpanels = new StyledTabbedPane();

        manageRoomsSubpanel = new ManageRoomsPanel();
        subpanels.add("Manage rooms", manageRoomsSubpanel);

        manageReservationsSubpanel = new ManageReservationsPanel();
        subpanels.add("Manage reservations", manageReservationsSubpanel);

        managePricesSubpanel = new ManagePricesPanel();
        subpanels.add("Manage prices", managePricesSubpanel);

        this.add(subpanels, BorderLayout.CENTER);
    }

    // Info edit panel

    public String getRenameHotelText() {
        return this.renameHotelField.getText();
    }

    public void setRenameHotelText(String text) {
        this.renameHotelField.setText(text);
    }

    public String getUpdateBasePriceText() {
        return this.basePriceField.getText();
    }

    public void setUpdateBasePriceText(String text) {
        this.basePriceField.setText(text);
    }

    public void setRenameHotelListener(RenameHotelListener listener) {
        this.renameHotelField.addKeyListener(listener);
        this.renameHotelButton.addActionListener(listener);
        this.renameHotelButton.addKeyListener(listener);
    }

    public void setUpdateBasePriceListener(ManagePricesListener listener) {
        this.basePriceField.addKeyListener(listener);
        this.updateBasePriceButton.addActionListener(listener);
        this.updateBasePriceButton.addKeyListener(listener);
    }

    // Manage rooms subpanel

    public void updateRoomList(ArrayList<String> data) {
        this.manageRoomsSubpanel.updateRoomList(data);
    }

    public void setManageRoomListener(ManageRoomListener manageRoomListener) {
        this.manageRoomsSubpanel.setRoomListListener(manageRoomListener);
    }

    public void updateRoomData(String data, ArrayList<Integer> availableDates) {
        this.manageRoomsSubpanel.updateRoomData(data, availableDates);
    }

    // Manage reservations subpanel

    // Manage prices subpanel

    public void setManagePricesCalendarText(int date, String text) {
        this.managePricesSubpanel.setCalendarText(date, text);
    }

    public String getPriceModifierField() {
        return this.managePricesSubpanel.getPriceModifierField();
    }

    public void setPriceModifierField(String text) {
        this.managePricesSubpanel.setPriceModifierField(text);
    }

    public void setModifiedPriceText(String text) {
        this.managePricesSubpanel.setModifiedPriceText(text);
    }

    public void setPriceModiferCalendarDate(int date) {
        this.managePricesSubpanel.selectCalendarDate(date);
    }

    public boolean getIsPriceModifierCalendarFocused() {
        return this.managePricesSubpanel.getIsCalendarFocused();
    }

    public void resetPriceModifierCalendarSelection() {
        this.managePricesSubpanel.resetCalendarSelection();
    }

    public boolean getIsUpdatePriceModifierFieldFocused() {
        return this.managePricesSubpanel.getIsUpdatePriceModifierFieldFocused();
    }

    public boolean getIsUpdateBasePriceFieldFocused() {
        return this.basePriceField.isFocusOwner();
    }

    public void setManagePricesListener(ManagePricesListener listener) {
        this.managePricesSubpanel.setListener(listener);
    }

    public int getCalendarRowAtPoint(Point point) {
        return this.managePricesSubpanel.getCalendarRowAtPoint(point);
    }

    public int getCalendarColAtPoint(Point point) {
        return this.managePricesSubpanel.getCalendarColAtPoint(point);
    }

    public void setManageReservationsListener(ManageReservationListener listener) {
        this.manageReservationsSubpanel.setListener(listener);
        this.manageReservationsSubpanel.setRemoveButtonListener((ActionListener) listener);
    }

    public void updateReservationList(ArrayList<String> list) {
        this.manageReservationsSubpanel.updateReservationList(list);
    }

    public void updateReservationData(String data) {
        this.manageReservationsSubpanel.updateReservationData(data);
    }

    public int getManageReservationSelectedIndex() {
        return this.manageReservationsSubpanel.getSelectedIndex();
    }

    public void setManageReservationVisible(boolean visible){
        this.manageReservationsSubpanel.setWrapperVisible(visible);
    }
}

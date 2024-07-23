package src.view.gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import src.controller.gui.ManagePricesListener;
import src.controller.gui.ManageRoomListener;
import src.controller.gui.RenameHotelListener;
import src.view.gui.TopView;
import src.view.gui.component.DecimalDocument;
import src.view.gui.subpanel.ManagePricesPanel;
import src.view.gui.subpanel.ManageReservationsPanel;
import src.view.gui.subpanel.ManageRoomsPanel;

public class ManageHotelPanel extends JPanel {
    private JTabbedPane subpanels;
    private ManageRoomsPanel manageRoomsSubpanel;
    private ManageReservationsPanel manageReservationsSubpanel;
    private ManagePricesPanel managePricesSubpanel;
    private JTextField renameHotelField;
    private JTextField basePriceField;
    private JButton renameHotelButton;
    private JButton updateBasePriceButton;

    public ManageHotelPanel() {
        this.setLayout(new BorderLayout());

        JPanel infoEditPanel = new JPanel();
        infoEditPanel.setLayout(new GridLayout(2, 3));

        JLabel renameLabel = new JLabel("Rename hotel:");
        renameLabel.setFont(TopView.SEGOE_UI_FONT);
        infoEditPanel.add(renameLabel, 0);
        renameHotelField = new JTextField();
        infoEditPanel.add(renameHotelField, 1);
        renameHotelButton = new JButton("Rename hotel");
        renameHotelButton.setFont(TopView.SEGOE_UI_FONT);
        infoEditPanel.add(renameHotelButton, 2);

        JLabel basePriceLabel = new JLabel("Update base price:");
        basePriceLabel.setFont(TopView.SEGOE_UI_FONT);
        infoEditPanel.add(basePriceLabel, 3);

        basePriceField = new JTextField();
        basePriceField.setDocument(new DecimalDocument());
        infoEditPanel.add(basePriceField, 4);
        updateBasePriceButton = new JButton("Update base price");
        updateBasePriceButton.setFont(TopView.SEGOE_UI_FONT);
        infoEditPanel.add(updateBasePriceButton, 5);

        this.add(infoEditPanel, BorderLayout.NORTH);

        subpanels = new JTabbedPane();

        manageRoomsSubpanel = new ManageRoomsPanel();
        subpanels.add("Rooms", manageRoomsSubpanel);

        manageReservationsSubpanel = new ManageReservationsPanel();
        subpanels.add("Reservations", manageReservationsSubpanel);

        managePricesSubpanel = new ManagePricesPanel();
        subpanels.add("Prices", managePricesSubpanel);

        subpanels.setFont(TopView.SEGOE_UI_FONT);
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
        this.renameHotelButton.addKeyListener(listener); // * enables "tabbing"
                                                         // to this button and
                                                         // pressing enter
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
        this.manageRoomsSubpanel.setListener(manageRoomListener);
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

}

package src.view.gui.subpanel;

import java.awt.BorderLayout;
import java.util.ArrayList;

import src.view.gui.component.StyledHTMLPane;

import src.view.gui.component.StyledPanel;
import javax.swing.event.ListSelectionListener;

import src.controller.gui.RoomListListener;
import src.view.gui.component.RoomAvailabilityCalendar;
import src.view.gui.component.RoomListPanel;

public class ViewRoomPanel extends StyledPanel {
    private RoomListPanel roomListPanel;
    private StyledHTMLPane roomDataComponent;
    private RoomAvailabilityCalendar roomAvailabilityCalendar;
    private StyledPanel roomDataPanel;

    public ViewRoomPanel() {
        this.setLayout(new BorderLayout());

        this.roomListPanel = new RoomListPanel(200, false);

        this.add(roomListPanel, BorderLayout.WEST);

        roomDataPanel = new StyledPanel();
        roomDataPanel.setLayout(new BorderLayout());
        
        this.roomDataComponent = new StyledHTMLPane();
        roomDataPanel.add(roomDataComponent, BorderLayout.NORTH);

        this.roomAvailabilityCalendar = new RoomAvailabilityCalendar();
        roomDataPanel.add(roomAvailabilityCalendar, BorderLayout.CENTER);
        this.roomDataPanel.setVisible(false);

        this.add(roomDataPanel, BorderLayout.CENTER);
    }

    public void updateRoomData(String data, ArrayList<Integer> availableDates) {
        this.roomDataComponent.setText(data);
        this.roomAvailabilityCalendar.setAvailability(availableDates);
        this.roomDataPanel.setVisible(true);
    }

    public void updateRoomList(ArrayList<String> data) {
        this.roomListPanel.setList(data);
    }

    public void setRoomListListener(RoomListListener roomListListener) {
        this.roomListPanel.setListener((ListSelectionListener) roomListListener);
    }

    public int getViewRoomSelectedIndex() {
        return this.roomListPanel.getSelectedIndex();
    }

    public void resetRoomListSelection() {
        this.roomListPanel.clearSelection();
        this.roomDataPanel.setVisible(false);
    }
}

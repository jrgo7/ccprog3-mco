package src.view.gui.subpanel;

import java.awt.BorderLayout;
import java.util.ArrayList;

import src.view.gui.component.StyledPanel;

import src.controller.gui.ManageRoomListener;
import src.view.gui.component.RoomListPanel;

public class ManageRoomsPanel extends StyledPanel {
    private RoomListPanel roomListPanel;

    public ManageRoomsPanel() {
        this.setLayout(new BorderLayout());

        this.roomListPanel = new RoomListPanel(200, true);

        this.add(roomListPanel, BorderLayout.WEST);
    }

    public void updateRoomList(ArrayList<String> data) {
        this.roomListPanel.setList(data);
    }

    public void setListener(ManageRoomListener manageRoomListener) {
        this.roomListPanel.setListener(manageRoomListener);
    }
}

package src.view.gui.subpanel;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import src.view.gui.component.RoomListPanel;

public class ManageRoomsPanel extends JPanel {
    private RoomListPanel roomListPanel;

    public ManageRoomsPanel() {
        this.setLayout(new BorderLayout());

        this.roomListPanel = new RoomListPanel(-1);

        this.add(roomListPanel, BorderLayout.WEST);
    }

    public void updateRoomList(ArrayList<String> data) {
        this.roomListPanel.setList(data);
    }
}

package src.view.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ViewRoomPanel extends JPanel {
    private RoomListPanel roomListPanel;

    public ViewRoomPanel () {
        this.setLayout(new BorderLayout());

        this.roomListPanel = new RoomListPanel(-1);

        this.add(roomListPanel, BorderLayout.WEST);
    }

    public void updateRoomList(ArrayList<String> data) {
        this.roomListPanel.updateList(data);
    }
}

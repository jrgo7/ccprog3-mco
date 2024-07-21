package src.view.gui.subpanel;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;

import src.controller.gui.RoomListListener;
import src.view.gui.component.RoomListPanel;

public class ViewRoomPanel extends JPanel {
    private RoomListPanel roomListPanel;

    private JEditorPane roomDataComponent;

    public ViewRoomPanel() {
        this.setLayout(new BorderLayout());

        this.roomListPanel = new RoomListPanel(200);

        this.add(roomListPanel, BorderLayout.WEST);

        this.roomDataComponent = new JEditorPane();
        this.roomDataComponent.setEditable(false);

        this.add(roomDataComponent, BorderLayout.CENTER);
    }

    public void updateRoomData(String data) {
        this.roomDataComponent.setText(data);
    }

    public void updateRoomList(ArrayList<String> data) {
        this.roomListPanel.setList(data);
    }

    public void setRoomListListener(RoomListListener roomListListener) {
        this.roomListPanel.setListener((ListSelectionListener) roomListListener);
    }
}

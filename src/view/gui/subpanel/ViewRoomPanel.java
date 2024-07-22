package src.view.gui.subpanel;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;

import src.controller.gui.RoomListListener;
import src.view.gui.component.Calendar;
import src.view.gui.component.RoomListPanel;

public class ViewRoomPanel extends JPanel {
    private RoomListPanel roomListPanel;
    private JEditorPane roomDataComponent;
    private Calendar roomAvailabilityCalendar;
    private JPanel roomDataPanel;

    public ViewRoomPanel() {
        this.setLayout(new BorderLayout());

        this.roomListPanel = new RoomListPanel(200, false);

        this.add(roomListPanel, BorderLayout.WEST);

        roomDataPanel = new JPanel();
        roomDataPanel.setLayout(new BorderLayout());
        
        this.roomDataComponent = new JEditorPane("text/html", "<p></p>");
        this.roomDataComponent.setEditable(false);
        roomDataPanel.add(roomDataComponent, BorderLayout.NORTH);

        this.roomAvailabilityCalendar = new Calendar();
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
}

package src.view.gui.subpanel;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.event.ListSelectionListener;
import src.controller.gui.RoomListListener;
import src.view.gui.component.RoomAvailabilityCalendar;
import src.view.gui.component.RoomListPanel;
import src.view.gui.component.StyledHTMLPane;
import src.view.gui.component.StyledPanel;

public class ViewRoomPanel extends StyledPanel {
    protected RoomListPanel roomListPanel;
    private StyledHTMLPane roomDataComponent;
    private RoomAvailabilityCalendar roomAvailabilityCalendar;
    private StyledPanel roomDataPanel;
    protected StyledPanel outer;

    public ViewRoomPanel(boolean addable) {
        this.setLayout(new BorderLayout());

        this.roomListPanel = new RoomListPanel(200, addable);

        this.add(roomListPanel, BorderLayout.WEST);

        this.outer = new StyledPanel();
        this.outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));

        roomDataPanel = new StyledPanel();
        roomDataPanel.setLayout(new BorderLayout());
        
        this.roomDataComponent = new StyledHTMLPane();
        roomDataPanel.add(roomDataComponent, BorderLayout.NORTH);

        this.roomAvailabilityCalendar = new RoomAvailabilityCalendar();
        roomDataPanel.add(roomAvailabilityCalendar, BorderLayout.CENTER);

        this.outer.add(roomDataPanel);
        this.outer.setVisible(false);

        this.add(outer, BorderLayout.CENTER);
    }

    public ViewRoomPanel() {
        this(false);
    }

    public void updateRoomData(String data, ArrayList<Integer> availableDates) {
        this.roomDataComponent.setText(data);
        this.roomAvailabilityCalendar.setAvailability(availableDates);
        this.outer.setVisible(true);
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
        this.outer.setVisible(false);
    }

    public void setWrapperVisible(boolean visible) {
        this.outer.setVisible(visible);
    }

    public void setSelectedIndex(int index) {
        this.roomListPanel.setSelectedIndex(index);
    }
}

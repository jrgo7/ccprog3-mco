package src.view.gui.subpanel;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.event.ListSelectionListener;
import src.controller.gui.RoomListListener;
import src.model.Room;
import src.view.gui.component.RoomAvailabilityCalendar;
import src.view.gui.component.RoomListPanel;
import src.view.gui.component.StyledHTMLPane;
import src.view.gui.component.StyledPanel;
import src.view.gui.panel.ViewHotelPanel;

/** Represents the View Rooms subpanel under a {@link ViewHotelPanel} */
public class ViewRoomPanel extends StyledPanel {
    /** Panel containing the room list. */
    protected RoomListPanel roomListPanel;

    /** Panel containing the room data. */
    private StyledHTMLPane roomDataComponent;

    /** Calendar displaying room availability information. */
    private RoomAvailabilityCalendar roomAvailabilityCalendar;

    /** Panel that wraps around the room data panel. */
    private StyledPanel roomDataPanel;

    /**
     * The panel wrapping around all other components. Hidden when no selection
     * is made.
     */
    protected StyledPanel outer;

    /**
     * Initializes the panel.
     * 
     * @param addable {@code true} if items can be added to the list.
     *                {@code false} otherwise.
     */
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

    /** Initializes the panel to be not addable by default. */
    public ViewRoomPanel() {
        this(false);
    }

    /**
     * Sets the text in the room data panel.
     * 
     * @param data           the text to set
     * @param availableDates an {@link ArrayList} of available dates. Usually
     *                       obtained with {@link Room#getAvailableDates()}
     */
    public void setRoomData(String data, ArrayList<Integer> availableDates) {
        this.roomDataComponent.setText(data);
        this.roomAvailabilityCalendar.setAvailabilityData(availableDates);
        this.outer.setVisible(true);
    }

    /**
     * Sets the room list with the given data.
     * 
     * @param data the list rooms to set
     */
    public void setRoomList(ArrayList<String> data) {
        this.roomListPanel.setList(data);
    }

    /**
     * Sets the listener for room list.
     * 
     * @param roomListListener the listener to set
     */
    public void setRoomListListener(RoomListListener roomListListener) {
        this.roomListPanel
                .setListener((ListSelectionListener) roomListListener);
    }

    /** {@return the index of the room list selection} */
    public int getSelectedRoomIndex() {
        return this.roomListPanel.getSelectedIndex();
    }

    /** Clears the room list selection. */
    public void clearRoomListSelection() {
        this.roomListPanel.clearSelection();
        this.outer.setVisible(false);
    }

    /**
     * Sets the visibility of the right-hand panel.
     * 
     * @param visible {@code true} to display the panel, {@code false} otherwise
     */
    public void setWrapperVisible(boolean visible) {
        this.outer.setVisible(visible);
    }

    /**
     * Sets the room list selection
     * 
     * @param index the index to set
     */
    public void setSelectedIndex(int index) {
        this.roomListPanel.setSelectedIndex(index);
    }
}

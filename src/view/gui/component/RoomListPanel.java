package src.view.gui.component;

import java.util.ArrayList;

/** Represents the {@link ListPanel} containing the list of rooms in a hotel. */
public class RoomListPanel extends ListPanel {
    private boolean addable;

    /**
     * Initializes the panel to a given width.
     * 
     * @param width The width of the panel
     */
    public RoomListPanel(int width, boolean addable) {
        super("Rooms", width, FontCollection.SEGOE_UI_BODY_LARGE);
        this.addable = addable;
    }

    /**
     * {@inheritDoc} The last element of the list will be a string containing
     * {@code Add room...} if rooms may be added through this panel.
     */
    @Override
    public void setList(ArrayList<String> data) {
        if (this.addable) {
            ArrayList<String> dataAppended = new ArrayList<>();
            dataAppended.addAll(data);
            dataAppended.add("Add room...");
            super.setList(dataAppended);
        } else {
            super.setList(data);
        }
    }
}

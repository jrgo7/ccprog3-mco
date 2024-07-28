package src.view.gui.component;

import java.util.ArrayList;

/** Represents the {@link ListPanel} containing the list of rooms in a hotel. */
public class RoomListPanel extends ListPanel {
    /**
     * {@code true} if items can be added to the panel, {@code false} otherwise.
     */
    private boolean addable;

    /**
     * Initializes the panel to a given width.
     * 
     * @param width   The width of the panel
     * @param addable {@code true} if items can be added to the panel,
     *                {@code false} otherwise
     */
    public RoomListPanel(int width, boolean addable) {
        super("Rooms", width, FontCollection.SEGOE_UI_SUBTITLE);
        this.addable = addable;
    }

    /**
     * Initializes the panel to a given width. Set to not be addable by default.
     * 
     * @param width The width of the panel
     */
    public RoomListPanel(int width) {
        this(width, false);
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
        } else
            super.setList(data);
    }
}

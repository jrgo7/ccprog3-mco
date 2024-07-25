package src.view.gui.component;

import java.util.ArrayList;

/**
 * Represents the {@link ListPanel} containing the list of hotels in the
 * application.
 */
public class HotelListPanel extends ListPanel {
    /**
     * Initializes the panel to a given width.
     * 
     * @param width The width of the panel
     */
    public HotelListPanel(int width) {
        super("Hotels",
                width,
                FontCollection.SEGOE_UI_TITLE,
                ColorCollection.BACKGROUND_COMPLEMENT);
    }

    /**
     * {@inheritDoc} The last element of the list will be a string containing
     * {@code Add hotel...}.
     */
    @Override
    public void setList(ArrayList<String> data) {
        data.add("Add hotel...");
        super.setList(data);
    }
}

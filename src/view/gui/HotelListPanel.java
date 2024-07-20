package src.view.gui;

import java.util.ArrayList;

public class HotelListPanel extends ListPanel {
    public HotelListPanel(int width) {
        super("Hotels", width);
    }

    /*
     * TODO: Why isnt `{@inheritDoc}` working is it because its inherited from an
     * abstract method
     */
    /**
     * {@inheritDoc} The last element of the list will be a string containing
     * {@code Add hotel...}.
     * 
     * @param data The updated list of hotel names
     */
    @Override
    public void updateList(ArrayList<String> data) {
        data.add("Add hotel...");
        super.updateList(data);
    }
}

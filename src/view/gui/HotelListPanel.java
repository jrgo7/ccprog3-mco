package src.view.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import src.model.Hotel;

public class HotelListPanel extends JPanel {
    /**
     * The {@link JList} component representing a browsable list of {@link Hotel}s
     * in the application.
     */
    private JList<String> hotelListComponent;

    /**
     * When cancelling adding a hotel to the list, the list selection will revert to
     * this index.
     */
    private int fallbackIndex;

    /** Initializes the panel containing the list of hotels */
    public HotelListPanel(int width) {
        this.setLayout(new BorderLayout());
        this.fallbackIndex = 0;

        JEditorPane hotelListPanelLabel = new JEditorPane("text/html", "<h1 style=\"font-family: sans-serif\">Hotels</h1>");
        hotelListPanelLabel.setEditable(false);

        this.add(hotelListPanelLabel, BorderLayout.NORTH);

        this.hotelListComponent = new JList<String>();
        this.hotelListComponent.setFixedCellWidth(width);
        this.hotelListComponent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.hotelListComponent.setFont(TopView.ARIAL_PLAIN_FONT);

        this.add(hotelListComponent);
    }

    /**
     * Sets a {@link ListSelectionListener} to the {@link JList} of hotel names tied
     * to this panel.
     * 
     * @param listener The listener to assign to the list
     */
    public void setListener(ListSelectionListener listener) {
        this.hotelListComponent.getSelectionModel().addListSelectionListener(listener);
    }

    /**
     * Updates the {@link JList} of hotel names tied to this panel. The last element
     * in the list will always be the string {@code Add hotel...}.
     * 
     * @param data The updated list of hotel names
     */
    public void updateHotelList(ArrayList<String> data) {
        data.add("Add hotel...");
        String[] temp = new String[data.size()];
        hotelListComponent.setListData(data.toArray(temp));
    }

    /**
     * {@return the selected index in the list}
     * 
     * @see JList#getSelectedIndex()
     */
    public int getSelectedIndex() {
        return this.hotelListComponent.getSelectedIndex();
    }

    /** {@return the current fallback index of the list} */
    public int getFallbackIndex() {
        return this.fallbackIndex;
    }

    /**
     * Sets the list selection to a given index.
     * 
     * @param index The index to set
     * @see JList#setSelectionInterval(int, int)
     */
    public void setSelectedIndex(int index) {
        this.hotelListComponent.setSelectionInterval(index, index);
    }

    /**
     * Sets the fallback index for the list.
     * 
     * @param index The index to set
     */
    public void setFallbackIndex(int index) {
        this.fallbackIndex = index;
    }

    /**
     * Clears the list selection.
     * 
     * @see JList#removeSelectionInterval(int, int)
     */
    public void clearSelection() {
        this.hotelListComponent.removeSelectionInterval(0, this.hotelListComponent.getComponentCount());
    }
}

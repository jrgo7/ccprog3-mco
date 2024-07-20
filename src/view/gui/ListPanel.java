package src.view.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import src.model.Hotel;

public abstract class ListPanel extends JPanel {
    /**
     * The {@link JList} component representing a browsable list of {@link Hotel}s
     * in the application.
     */
    protected JList<String> listComponent;

    /**
     * When cancelling adding a hotel to the list, the list selection will revert to
     * this index.
     */
    private int fallbackIndex;

    /** Initializes the panel containing the list of hotels */
    public ListPanel(String name, int width) {
        this.setLayout(new BorderLayout());
        this.fallbackIndex = 0;

        JLabel hotelListPanelLabel = new JLabel(name);
        this.setFont(new Font("Arial", 0, 32));

        this.add(hotelListPanelLabel, BorderLayout.NORTH);

        this.listComponent = new JList<String>();
        this.listComponent.setFixedCellWidth(width);
        this.listComponent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(listComponent);
    }

    /**
     * Updates the {@link JList} of hotel names tied to this panel.
     * 
     * @param data The updated list of hotel names
     */
    public void updateList(ArrayList<String> data) {
        String[] temp = new String[data.size()];
        this.listComponent.setListData(data.toArray(temp));
    }

    /**
     * Sets a {@link ListSelectionListener} to the {@link JList} of hotel names tied
     * to this panel.
     * 
     * @param listener The listener to assign to the list
     */
    public void setListener(ListSelectionListener listener) {
        this.listComponent.getSelectionModel().addListSelectionListener(listener);
    }

    /**
     * {@return the selected index in the list}
     * 
     * @see JList#getSelectedIndex()
     */
    public int getSelectedIndex() {
        return this.listComponent.getSelectedIndex();
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
        this.listComponent.setSelectionInterval(index, index);
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
        this.listComponent.removeSelectionInterval(0, this.listComponent.getComponentCount());
    }
}

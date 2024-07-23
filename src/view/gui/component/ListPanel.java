package src.view.gui.component;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;


/**
 * Represents an abstract {@link JPanel} that wraps around a single
 * {@link JList} component.
 */
public abstract class ListPanel extends JPanel {
    /** The {@link JList} contained in the panel. */
    private JList<String> listComponent;

    /**
     * When cancelling certain actions that interact with the list, the list
     * selection will revert to this index.
     */
    private int fallbackIndex;

    /**
     * Initializes the panel to a given width and with a header.
     * 
     * @param name  The string to put in the header
     * @param width The width of the panel
     */
    public ListPanel(String name, int width) {
        this.setLayout(new BorderLayout());
        this.fallbackIndex = 0;

        JLabel header = new JLabel(name);
        header.setFont(FontCollection.SEGOE_UI_BODY_LARGE);

        this.add(header, BorderLayout.NORTH);

        this.listComponent = new JList<String>();
        this.listComponent.setFixedCellWidth(width);
        this.listComponent.setFixedCellHeight(42);
        this.listComponent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.listComponent.setFont(FontCollection.SEGOE_UI_BODY);
        
        this.add(new JScrollPane(listComponent));
    }

    /**
     * Sets the list content.
     * 
     * @param data The new list of string items
     */
    public void setList(ArrayList<String> data) {
        String[] temp = new String[data.size()];
        this.listComponent.setListData(data.toArray(temp));
    }

    /**
     * Sets a {@link ListSelectionListener} to the {@link JList} tied to this
     * panel.
     * 
     * @param listener The listener to assign to the list
     */
    public void setListener(ListSelectionListener listener) {
        this.listComponent.getSelectionModel()
                .addListSelectionListener(listener);
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
        this.listComponent.removeSelectionInterval(0,
                this.listComponent.getComponentCount());
    }
}

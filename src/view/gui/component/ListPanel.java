package src.view.gui.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

/**
 * Represents an abstract {@link StyledPanel} that wraps around a single
 * {@link JList} component.
 */
public abstract class ListPanel extends StyledPanel {
    /** The {@link JList} contained in the panel. */
    private JList<String> listComponent;

    /**
     * When cancelling certain actions that interact with the list, the list
     * selection will revert to this index.
     */
    private int fallbackIndex;

    /**
     * This header is shown at the top of the list.
     */
    private StyledLabel header;

    /**
     * Initializes the panel to a given header name and width.
     * 
     * @param name  The string to put in the header
     * @param width The width of the panel
     */
    public ListPanel(String name, int width) {
        this.setLayout(new BorderLayout());
        this.fallbackIndex = 0;

        this.header = new StyledLabel(name, FontCollection.SEGOE_UI_TITLE);

        this.add(header, BorderLayout.NORTH);

        this.listComponent = new JList<String>();
        this.listComponent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.listComponent.setFixedCellWidth(width);
        this.listComponent.setFixedCellHeight(42);
        this.listComponent.setFont(FontCollection.SEGOE_UI_BODY);

        this.add(new StyledScrollPane(listComponent));
    }

    /**
     * Initializes the panel with a given header name, width, and font.
     * 
     * @param name
     * @param width
     * @param font
     */
    public ListPanel(String name, int width, Font headerFont) {
        this(name, width);
        this.header.setFont(headerFont);
    }

    /**
     * Initializes the panel with a given header name, width, font, and
     * background color.
     * 
     * @param name
     * @param width
     * @param font
     * @param background
     */
    public ListPanel(String name, int width, Font headerFont, Color background) {
        this(name, width, headerFont);
        this.setBackground(background);
        this.listComponent.setBackground(background);
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
        this.listComponent.removeSelectionInterval(
            this.listComponent.getSelectedIndex(),
            this.listComponent.getSelectionModel().getMaxSelectionIndex());
    }
}

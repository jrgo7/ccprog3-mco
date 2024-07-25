package src.controller.gui;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.component.ListPanel;

/** Represents an abstract listener for {@link ListPanel} components. */
public abstract class ListAddListener implements ListSelectionListener {
    /** The {@link ReservationSystem} tied to the listener. */
    protected ReservationSystem reservationSystem;

    /** The {@link TopView} used to communicate with the GUI. */
    protected TopView view;

    public ListAddListener(ReservationSystem reservationSystem,
            TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
        this.updateList();
    }

    /** Refreshes the list. */
    public abstract void updateList();

    /** {@return the list length} */
    protected abstract int getListLength();

    /**
     * Called when handling a change in the list selection.
     * 
     * @param selectedIndex The index of the new selection. Obtained through
     *                      {@link ListSelectionModel#getMinSelectionIndex()}.
     * @see #value
     */
    protected abstract void handleValueChanged(int selectedIndex);

    /**
     * Called when viewing an item in the list to update an information panel.
     * 
     * @param selectedIndex The index of the selection (at this point will
     *                      usually NOT be equal to {@link #getListLength()})
     */
    protected abstract void updateDataPanel(int selectedIndex);

    /**
     * Called when adding an item to the list.
     * 
     * @param selectedIndex The index of the selection (at this point will
     *                      usually be equal to {@link #getListLength()})
     */
    protected abstract void addToList(int selectedIndex);

    /**
     * {@inheritDoc} Calls {@link #addToList(int)} when selecting the last item
     * in the list (designated as the "add" option), or
     * {@link #updateDataPanel(int)} otherwise.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
            return;

        /*
         * TODO: Unsure if ListSelectionModel does funny stuff with the
         * selection index. Might be better to fetch it from the view
         */
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        int selectedIndex = lsm.getMinSelectionIndex();

        this.handleValueChanged(selectedIndex);
    }
}

package src.controller.gui;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import src.model.ReservationSystem;
import src.view.gui.TopView;

public abstract class ListAddListener implements ListSelectionListener {
    protected ReservationSystem reservationSystem;
    protected TopView view;

    /**
     * Refreshes the list.
     */
    public abstract void updateList();

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

    /** {@return the list length} */
    protected abstract int getListLength();

    public ListAddListener(ReservationSystem reservationSystem,
            TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
        //this.updateList();
    }

    /**
     * {@inheritDoc} Calls {@link #addToList(int)} when selecting the last item
     * in the list (designated as the "add" option), or
     * {@link #updateDataPanel(int)} otherwise.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
            return;

        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        int selectedIndex = lsm.getMinSelectionIndex();

        if (selectedIndex == this.getListLength())
            this.addToList(selectedIndex);
        else if (selectedIndex >= 0)
            this.updateDataPanel(selectedIndex);
    }
}

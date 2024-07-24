package src.controller.gui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ListSelectionEvent;

import src.model.ReservationSystem;
import src.view.gui.TopView;

public class ManageReservationListener extends ReservationListListener implements ActionListener {
    public ManageReservationListener(ReservationSystem reservationSystem, TopView view) {
        super(reservationSystem, view);
    }

    /** {@inheritDoc} */
    @Override
    protected void handleValueChanged(int selectedIndex) {
        this.updateDataPanel(selectedIndex);
    }
    
   /** {@inheritDoc} */
   @Override
   protected void updateDataPanel(int selectedIndex) {
       /* Exit if selected index is invalid */
       if (selectedIndex < 0) {
           this.view.setManageReservationVisible(false);
           return;
       }
           
       this.view.setManageReservationVisible(true);
       int hotelIndex = view.getHotelListSelectedIndex();

       if (hotelIndex < 0)
           hotelIndex = 0;
       this.view.setManageReservationData(
               reservationSystem.getReservationString(hotelIndex,
                       selectedIndex));
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        int reservationIndex = view.getManageReservationSelectedIndex();
        int hotelIndex = view.getHotelListSelectedIndex();

        if (reservationIndex < 0 || hotelIndex < 0)
            return;

        this.reservationSystem.removeReservation(hotelIndex, reservationIndex);
        this.updateList();
        this.updateDataPanel(reservationIndex);
        
        this.updateDataPanel(view.getManageReservationSelectedIndex());
    }
}

package src.controller.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
           this.view.getManageHotelDelegate().setManageReservationVisible(false);
           return;
       }
           
       this.view.getManageHotelDelegate().setManageReservationVisible(true);
       int hotelIndex = view.getSelectedIndex();

       if (hotelIndex < 0)
           hotelIndex = 0;
       this.view.getManageHotelDelegate().setManageReservationData(
               reservationSystem.getReservationString(hotelIndex,
                       selectedIndex));
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        int reservationIndex = view.getManageHotelDelegate().getManageReservationSelectedIndex();
        int hotelIndex = view.getSelectedIndex();

        if (reservationIndex < 0 || hotelIndex < 0)
            return;

        this.reservationSystem.removeReservation(hotelIndex, reservationIndex);
        this.updateList();
        this.updateDataPanel(-1);
    }
}

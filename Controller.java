import java.awt.event.*;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class Controller implements ActionListener, ListSelectionListener {
    ReservationSystem reservationSystem;
    TopView view;

    public Controller(ReservationSystem reservationSystem, TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
        this.view.setListeners(this);
        this.update();
    }

    public void update() {
        view.setHotelListData(reservationSystem.getHotelNamesAsList());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            int selectedIndex = lsm.getMinSelectionIndex();
            int hotelCount = reservationSystem.getHotelCount();
            System.out.println("Selected " + selectedIndex);
            if (selectedIndex == hotelCount) {
                // Selected "Add hotel" button
                if (reservationSystem.addHotel(view.promptAddHotel())) {
                    view.setTabIndex(TopView.VIEW_HOTEL_SCREEN);
                    this.update();
                    view.setHotelListSelectedIndex(selectedIndex); // redundant but gives a nice blue selection
                                                                   // highlight
                } else if (reservationSystem.getHotelCount() > 0) {
                    view.setHotelListSelectedIndex(
                            view.getHotelListPrevSelectedIndex());
                } else {
                    view.removeHotelListSelection();
                }
            }
            if (selectedIndex >= 0 && selectedIndex < hotelCount) {
                view.setHotelNameLabelText(
                        reservationSystem.getHotelNames()[selectedIndex]);
                view.setHotelDataText(
                        reservationSystem.getHotel(selectedIndex).toString());
                view.setHotelListPrevSelectedIndex(selectedIndex);
            }
        }
    }
}

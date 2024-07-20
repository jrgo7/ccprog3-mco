package src.controller.gui;

import src.model.ReservationSystem;
import src.view.gui.TopView;

public class Controller {
    ReservationSystem reservationSystem;
    TopView view;
    AvailabilityCalendarListener availabilityCalendarListener;
    HotelListListener hotelListListener;
    RenameHotelListener renameHotelListener;
    UpdateBasePriceListener updateBasePriceListener;

    public Controller(ReservationSystem reservationSystem, TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
        availabilityCalendarListener = new AvailabilityCalendarListener(reservationSystem, view);
        hotelListListener = new HotelListListener(reservationSystem, view);
        renameHotelListener = new RenameHotelListener(reservationSystem, view);
        updateBasePriceListener = new UpdateBasePriceListener(reservationSystem, view);

        this.view.setListeners(
            hotelListListener,
            availabilityCalendarListener,
            renameHotelListener,
            updateBasePriceListener);
    }
}

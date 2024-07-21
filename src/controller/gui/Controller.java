package src.controller.gui;

import src.model.ReservationSystem;
import src.view.gui.TopView;

public class Controller {
    ReservationSystem reservationSystem;
    TopView view;
    AvailabilityCalendarListener availabilityCalendarListener;
    HotelListListener hotelListListener;
    RenameHotelListener renameHotelListener;
    ManagePricesListener managePricesListener;
    RoomListListener viewRoomListListener;
    SimulateBookingRoomListListener simulateBookingRoomListListener;
    ManageRoomListener manageRoomListListener;

    public Controller(ReservationSystem reservationSystem, TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
        availabilityCalendarListener = new AvailabilityCalendarListener(
                reservationSystem, view);
        hotelListListener = new HotelListListener(reservationSystem, view);
        renameHotelListener = new RenameHotelListener(reservationSystem, view);
        managePricesListener = new ManagePricesListener(reservationSystem, view);
        viewRoomListListener = new RoomListListener(reservationSystem, view);
        simulateBookingRoomListListener = new SimulateBookingRoomListListener(reservationSystem, view);
        manageRoomListListener = new ManageRoomListener(reservationSystem, view);
        this.view.setTopViewHotelListListener(hotelListListener);
        this.view.setViewHotelListeners(
                availabilityCalendarListener,
                viewRoomListListener);
        this.view.setManageHotelListeners(
                renameHotelListener,
                managePricesListener,
                manageRoomListListener);
        this.view.setSimulateBookingListeners(
                simulateBookingRoomListListener
        );
    }
}

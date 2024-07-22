package src.controller.gui;

import src.model.ReservationSystem;
import src.view.gui.TopView;

public class Controller {
    ReservationSystem reservationSystem;
    TopView view;

    HotelListListener hotelListListener;

    AvailabilityCalendarListener availabilityCalendarListener;
    RoomListListener viewRoomListListener;

    RenameHotelListener renameHotelListener;
    ManagePricesListener managePricesListener;
    ManageRoomListener manageRoomListListener;

    SimulateBookingRoomListListener simulateBookingRoomListListener;
    BookingCalendarListener bookingCalendarListener;

    public Controller(ReservationSystem reservationSystem, TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;

        hotelListListener = new HotelListListener(reservationSystem, view);

        availabilityCalendarListener = new AvailabilityCalendarListener(
                reservationSystem, view);
        viewRoomListListener = new RoomListListener(reservationSystem, view);
        
        renameHotelListener = new RenameHotelListener(
                reservationSystem, view);
        managePricesListener = new ManagePricesListener(
                reservationSystem, view);
        manageRoomListListener = new ManageRoomListener(
                reservationSystem, view);
        
        simulateBookingRoomListListener = new SimulateBookingRoomListListener(
                reservationSystem, view);
        bookingCalendarListener = new BookingCalendarListener(
                reservationSystem, view);

        this.view.setTopViewHotelListListener(hotelListListener);
        
        this.view.setViewHotelListeners(
                availabilityCalendarListener, viewRoomListListener);

        this.view.setManageHotelListeners(
                renameHotelListener, managePricesListener,
                manageRoomListListener);

        this.view.setSimulateBookingListeners(
                simulateBookingRoomListListener, bookingCalendarListener);
    }
}

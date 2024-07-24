package src.controller.gui;

import src.model.ReservationSystem;
import src.view.gui.TopView;

public class Controller {
    ReservationSystem reservationSystem;
    TopView view;

    TopMenuPaneListener topMenuPaneListener;
    HotelListListener hotelListListener;

    AvailabilityCalendarListener availabilityCalendarListener;
    RoomListListener viewRoomListListener;

    RenameHotelListener renameHotelListener;
    ManagePricesListener managePricesListener;
    ManageRoomListener manageRoomListListener;
    ManageReservationListener manageReservationListener;

    SimulateBookingRoomListListener simulateBookingRoomListListener;
    BookingCalendarListener bookingCalendarListener;

    ReservationListListener viewReservationListListener;

    public Controller(ReservationSystem reservationSystem, TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;

        topMenuPaneListener = new TopMenuPaneListener(reservationSystem, view);
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

                manageReservationListener = new ManageReservationListener(reservationSystem, view);

        viewReservationListListener = new ReservationListListener(reservationSystem, view);

        this.view.setTopMenuPaneListener(topMenuPaneListener);
        this.view.setTopViewHotelListListener(hotelListListener);
        
        this.view.setViewHotelListeners(
                availabilityCalendarListener, viewRoomListListener, viewReservationListListener);

        this.view.setManageHotelListeners(
                renameHotelListener, managePricesListener,
                manageRoomListListener, manageReservationListener);

        this.view.setSimulateBookingListeners(
                simulateBookingRoomListListener, bookingCalendarListener);
    }
}

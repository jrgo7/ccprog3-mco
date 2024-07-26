package src.controller.gui;

import src.model.ReservationSystem;
import src.view.gui.TopView;

/**
 * Represents a controller that facilitates communication between the model and
 * the view.
 */
public class Controller {
    private TopMenuPaneListener topMenuPaneListener;
    private HotelListListener hotelListListener;

    private HotelAvailabilityCalendarListener availabilityCalendarListener;
    private RoomListListener viewRoomListListener;
    private ReservationListListener viewReservationListListener;

    private RenameHotelListener renameHotelListener;
    private ManagePricesListener managePricesListener;
    private ManageRoomListener manageRoomListListener;
    private ManageReservationListener manageReservationListener;

    private SimulateBookingRoomListListener simulateBookingRoomListListener;
    private SimulateBookingCalendarListener bookingCalendarListener;

    /** Initializes the listeners in the view. */
    public Controller(ReservationSystem reservationSystem, TopView view) {
        this.topMenuPaneListener = new TopMenuPaneListener(view);
        this.hotelListListener = new HotelListListener(reservationSystem, view);

        this.availabilityCalendarListener = new HotelAvailabilityCalendarListener(
                reservationSystem, view);
        this.viewRoomListListener = new RoomListListener(
                reservationSystem, view);
        this.viewReservationListListener = new ReservationListListener(
                reservationSystem, view);

        this.renameHotelListener = new RenameHotelListener(
                reservationSystem, view);
        this.managePricesListener = new ManagePricesListener(
                reservationSystem, view);
        this.manageRoomListListener = new ManageRoomListener(
                reservationSystem, view);
        this.manageReservationListener = new ManageReservationListener(
                reservationSystem, view);

        this.simulateBookingRoomListListener = new SimulateBookingRoomListListener(
                reservationSystem, view);
        this.bookingCalendarListener = new SimulateBookingCalendarListener(
                reservationSystem, view);

        view.setTopMenuPaneListener(topMenuPaneListener);
        view.setTopViewHotelListListener(hotelListListener);

        view.setViewHotelListeners(
                availabilityCalendarListener, viewRoomListListener,
                viewReservationListListener);

        view.setManageHotelListeners(
                renameHotelListener, managePricesListener,
                manageRoomListListener, manageReservationListener);

        view.setSimulateBookingListeners(
                simulateBookingRoomListListener, bookingCalendarListener);
    }
}

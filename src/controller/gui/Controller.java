package src.controller.gui;

import src.model.ReservationSystem;
import src.view.gui.TopView;

/**
 * Represents a controller that facilitates communication between the model and
 * the view.
 */
public class Controller {
    /** The listener for the top menu tabs. */
    private TopMenuPaneListener topMenuPaneListener;

    /** The listener for the hotel list. */
    private HotelListListener hotelListListener;

    /** The listener for the hotel availability calendar. */
    private HotelAvailabilityCalendarListener availabilityCalendarListener;

    /** The listener for the room list in the View Rooms tab. */
    private RoomListListener viewRoomListListener;

    /** The listener for the reservation list in the View Reservations tab. */
    private ReservationListListener viewReservationListListener;

    /** The listener for the hotel renaming feature. */
    private RenameHotelListener renameHotelListener;

    /** The listener for the Manage Prices tab. */
    private ManagePricesListener managePricesListener;

    /** The listener for the room list in the Manage Rooms tab. */
    private ManageRoomListener manageRoomListListener;

    /** The listener for the reservation list in the Manage Reservations tab. */
    private ManageReservationListener manageReservationListener;

    /** The listener for the Simulate Booking tab. */
    private SimulateBookingRoomListListener simulateBookingRoomListListener;

    /** The listener for the booking calendar in the Simulate Booking tab. */
    private SimulateBookingCalendarListener bookingCalendarListener;

    /**
     * Initializes the listeners in the view.
     * 
     * @param reservationSystem the {@link ReservationSystem} tied to the
     *                          controller
     * @param view              the {@link TopView} tied to the controller
     */
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

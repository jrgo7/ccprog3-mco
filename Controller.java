public class Controller {
    ReservationSystem reservationSystem;
    TopView view;
    AvailabilityCalendarListener availabilityCalendarListener;
    HotelListListener hotelListListener;

    public Controller(ReservationSystem reservationSystem, TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
        availabilityCalendarListener = new AvailabilityCalendarListener(reservationSystem, view);
        hotelListListener = new HotelListListener(reservationSystem, view);
        this.view.setListeners(hotelListListener, availabilityCalendarListener);
    }
}

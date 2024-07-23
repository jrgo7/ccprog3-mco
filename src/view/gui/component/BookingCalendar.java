package src.view.gui.component;

/**
 * This class extends {@link RoomAvailabilityCalendar} by adding functionality to
 * set its internal {@link BookingCalendarRenderer} with a specific check-in
 * and check-out date, used in order to provide a highlighted selection of the
 * range encompassed therein.
 */
public class BookingCalendar extends RoomAvailabilityCalendar {
    public BookingCalendar() {
        super();
        this.renderer = new BookingCalendarRenderer();
        this.setDefaultRenderer(Object.class, renderer);
    }

    /**
     * Set values for the internal renderer to render checkIn date
     * @param checkIn
     */
    public void setCalendarCheckIn(int checkIn) {
        ((BookingCalendarRenderer) renderer).setCheckIn(checkIn);
    }

    /**
     * Set values for the internal renderer to render checkOut date
     * @param checkOut
     */
    public void setCalendarCheckOut(int checkOut) {
        ((BookingCalendarRenderer) renderer).setCheckOut(checkOut);
    }

}
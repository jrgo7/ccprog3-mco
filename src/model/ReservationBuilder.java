package src.model;

/**
 * Represents a builder class for generation instances of {@link Reservation}.
 */
public class ReservationBuilder {
    /** The index of the {@link Hotel} set. */
    private int hotelIndex;

    /** The name of the guest making the reservation. */
    private String guestName;

    /** The check-in date. */
    private int checkIn;

    /** The check-out date. */
    private int checkOut;

    /** The index of the {@link Room} set */
    private int roomIndex;

    /**
     * The discount code string used.
     * 
     * @see Reservation#setDiscountCode(String)
     */
    private String discountCode;

    /** Initializes the builder. */
    public ReservationBuilder() {
        this.reset();
    }

    /**
     * Reset the values of this {@link ReservationBuilder} to their defaults,
     * except for the hotel index and the room index, which should be modified
     * only when the user selects a new {@link Hotel} or {@link Room}.
     */
    public void reset() {
        this.checkIn = 1;
        this.checkOut = 31;
        this.guestName = "";
        this.discountCode = "";
    }

    /**
     * Sets the hotel index.
     * 
     * @param hotelIndex The index to set
     */
    public void setHotelIndex(int hotelIndex) {
        this.hotelIndex = hotelIndex;
    }

    /**
     * Sets the guest name.
     * 
     * @param guestName The name to set
     */
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    /**
     * Validate a pair of check-in and check-out dates; this is called when the
     * builder wants to change either of the two.
     * 
     * All of the conditions must be satisfied in order to return true: * Either
     * the new checkIn or checkOut is different from the old one. * The checkIn
     * date is within day 1-30. * The checkOut date is within 2-31.
     * 
     * @param checkIn  The check-in date to set
     * @param checkOut The check-out date to set
     * @return {@code true} if the pair is valid, {@code false} otherwise
     */
    public boolean validateDates(int checkIn, int checkOut) {
        return (this.checkIn != checkIn || this.checkOut != checkOut) &&
                (checkIn >= 1 && checkIn < 31) &&
                (checkOut > 1 && checkOut <= 31) && (checkIn < checkOut);
    }

    /**
     * Sets the check-in date.
     * 
     * @param checkIn The check-in date to set
     * @return {@code true} if the date changed as a result of the call,
     *         {@code false} otherwise or if the date inputted was out of
     *         bounds.
     */
    public boolean setCheckIn(int checkIn) {
        boolean changed = validateDates(checkIn, this.checkOut);
        if (changed) {
            this.checkIn = checkIn;
        }
        return changed;
    }

    /**
     * Sets the check-out date.
     * 
     * @param checkOut The check-out date to set
     * @return {@code true} if the date changed as a result of the call,
     *         {@code false} otherwise or if the date inputted was out of
     *         bounds.
     */
    public boolean setCheckOut(int checkOut) {
        boolean changed = validateDates(this.checkIn, checkOut);
        if (changed) {
            this.checkOut = checkOut;
        }
        return changed;
    }

    /**
     * Sets the room index.
     * 
     * @param roomIndex The index to set
     */
    public void setRoomIndex(int roomIndex) {
        this.roomIndex = roomIndex;
    }

    /**
     * Sets the discount code used.
     * 
     * @param discountCode The discount code to set
     */
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    /** {@return the hotel index set} */
    public int getHotelIndex() {
        return this.hotelIndex;
    }

    /** {@return the name of the guest} */
    public String getGuestName() {
        return this.guestName;
    }

    /** {@return the check-in date} */
    public int getCheckIn() {
        return this.checkIn;
    }

    /** {@return the check-out date} */
    public int getCheckOut() {
        return this.checkOut;
    }

    /** {@return the index of the room} */
    public int getRoomIndex() {
        return this.roomIndex;
    }

    /** {@return the discount code used} */
    public String getDiscountCode() {
        return this.discountCode;
    }

}

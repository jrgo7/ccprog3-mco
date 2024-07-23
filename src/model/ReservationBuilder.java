package src.model;

public class ReservationBuilder {
    private int hotelIndex;
    private String guestName;
    private int checkIn;
    private int checkOut;
    private int roomIndex;
    private String discountCode;

    public ReservationBuilder() {
        this.checkIn = 1;
        this.checkOut = 31;
    }

    public void setHotelIndex(int hotelIndex) {
        this.hotelIndex = hotelIndex;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    /**
     * Validate a pair of check-in and check-out dates; this is called when the
     * builder wants to change either of the two.
     * 
     * All of the conditions must be satisfied in order to return true:
     * * Either the new checkIn or checkOut is different from the old one.
     * ? This prevents redundant updating in the model and critically the GUI
     * * The checkIn date is within day 1-30.
     * * The checkOut date is within 2-31.
     * 
     * @param checkIn
     * @param checkOut
     * @return
     */
    public boolean validateDates(int checkIn, int checkOut) {
        return (this.checkIn != checkIn || this.checkOut != checkOut) &&
                (checkIn >= 1 && checkIn < 31) &&
                (checkOut > 1 && checkOut <= 31) && (checkIn < checkOut);
    }

    public boolean setCheckIn(int checkIn) {
        boolean changed = validateDates(checkIn, this.checkOut);
        if (changed) {
            this.checkIn = checkIn;
        }
        return changed;
    }

    public boolean setCheckOut(int checkOut) {
        boolean changed = validateDates(this.checkIn, checkOut);
        if (changed) {
            this.checkOut = checkOut;
        }
        return changed;
    }

    public void setRoomIndex(int roomIndex) {
        this.roomIndex = roomIndex;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public int getHotelIndex() {
        return this.hotelIndex;
    }

    public String getGuestName() {
        return this.guestName;
    }

    public int getCheckIn() {
        return this.checkIn;
    }

    public int getCheckOut() {
        return this.checkOut;
    }

    public int getRoomIndex() {
        return this.roomIndex;
    }

    public String getDiscountCode() {
        return this.discountCode;
    }
}

package src.model;

/** Represents a reservation tied to a {@link Hotel} and a {@link Room}. */
public class Reservation {
    /*
     * TODO: Think of a way to access date price modifiers without needing a
     * reference to Hotel
     */
    /** The {@link Hotel} tied to the reservation */
    private Hotel hotel;

    /** The name of the guest tied to the reservation. */
    private String guestName;

    /** The check-in date of the reservation. */
    private int checkIn;

    /** The check-out date of the reservation. */
    private int checkOut;

    /** The {@link Room} tied to the reservation. */
    private Room room;

    /**
     * The discount code used for this reservation. Left {@code NULL} if no code
     * is used.
     */
    private String discountCode;

    /**
     * Initializes a new reservation instance given booking information.
     * 
     * @param guestName The name of the guest
     * @param checkIn   The check-in date
     * @param checkOut  The check-out date
     * @param room      The room instance to book a reservation for
     * @see Hotel#addReservation(String, int, int, int)
     */
    public Reservation(Hotel hotel, String guestName, int checkIn, int checkOut,
            Room room) {
        this.hotel = hotel;
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.room = room;
        this.discountCode = "";
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    /** {@return the guest name tied to the reservation} */
    public String getGuestName() {
        return this.guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    /** {@return the check-in date of the reservation} */
    public int getCheckIn() {
        return this.checkIn;
    }

    public void setCheckIn(int checkIn) {
        this.checkIn = checkIn;
    }

    /** {@return the check-out date of the reservation} */
    public int getCheckOut() {
        return this.checkOut;
    }

    public void setCheckOut(int checkOut) {
        this.checkOut = checkOut;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * {@return the number of nights the reservation is good for} Calculated as
     * {@code check-out date - check-in date}
     */
    public int getNightCount() {
        return this.checkOut - this.checkIn;
    }

    /**
     * {@return the discount code modifier for a given night}
     * 
     * @param night The night to apply the discount to
     * @see #setDiscountCode(String)
     */
    private double getDiscountCodeModifier(int night) {
        if (!discountCode.equals(""))
            switch (discountCode) {
                case "I_WORK_HERE":
                    /* Applies a 10% discount */
                    return 0.9;
                case "STAY4_GET1":
                    /* This code only gives the check-in date for free */
                    if (night == this.checkIn)
                        return 0.0;
                    break;
                case "PAYDAY":
                    /* Applies a 7% discount */
                    return 0.93;
            }

        return 1.0;
    }

    /**
     * {@return the price for a given night} Calculated as
     * {@code Room base price * Hotel night price modifier * Discount code modifier}
     * 
     * @param night The night to calculate the price for
     * @see Room#getBasePrice()
     * @see Hotel#getPriceModifier(int)
     * @see #getDiscountCodeModifier(int)
     */
    private double getPriceForNight(int night) {
        return this.room.getBasePrice()
                * this.hotel.getPriceModifier(night)
                * getDiscountCodeModifier(night);
    }

    /**
     * {@return the total price for the reservation} Calculated as the sum of
     * each night's individual prices.
     * 
     * @see #getPriceForNight(int)
     */
    public double getTotalPrice() {
        double totalPrice = 0;

        for (int i = this.checkIn; i < this.checkOut; i++)
            totalPrice += getPriceForNight(i);

        return totalPrice;
    }

    /**
     * Sets the discount code used for the reservation. The following are valid
     * discount codes given their conditions are fulfilled
     * <ul>
     * <li>{@code I_WORK_HERE} always succeeds with a 10% discount to the final
     * price
     * <li>{@code STAY4_GET1} gives the first day for free if the reservation is
     * for at least 5 days
     * <li>{@code PAYDAY} gives a 7% discount to the final price if the
     * reservation covers day 15 or day 30 (but not as checkout)
     * </ul>
     * <p>
     * Note that a reservation that does not use a reservation code will have it
     * set to {@code null} by default.
     * 
     * @param discountCode The discount code string to use
     * @return {@code true} if the discount code was set successfully,
     *         {@code false} otherwise
     */
    public boolean setDiscountCode(String discountCode) {
        if (!discountCode.equals("")) {
            switch (discountCode) {
                case "I_WORK_HERE":
                    /* Code always succeeds */
                    break;
                case "STAY4_GET1":
                    /* Code fails if staying for less than 5 nights */
                    if (getNightCount() < 5)
                        return false;
                    break;
                case "PAYDAY":
                    /* Code fails if stay does not include 15 or 30 */
                    if (!(this.checkIn <= 15 && this.checkOut > 15)
                            && !(this.checkIn <= 30 && this.checkOut > 30))
                        return false;
                    break;
                default:
                    return false;
            }
        }

        /* Update discount code */
        this.discountCode = discountCode;
        return true;
    }

    /**
     * {@return a string listing the price breakdown of the reservation} This
     * includes the number of nights, the base price, and the total price of the
     * reservation.
     * 
     * @see #getNightCount()
     * @see #getTotalPrice()
     */
    public String getPriceBreakdown() {
        /* TODO: Rework this method */
        String breakdown = String.format(
                "<h2>Price breakdown</h2>",
                this.checkIn,
                this.checkOut);
        breakdown += """
                <table>
                <tr>
                <th>Date</th>
                <th>Base price</th>
                <th>Price modifier</th>
                <th>Discount code modifier</th>
                <th>Computed price for the night</th>
                </tr>""";
        for (int date = this.checkIn; date < this.checkOut; date++)
            breakdown += String.format(
                    """
                            <tr>
                            <td>%d-%d</td>
                            <td>%.2f</td>
                            <td>%.2f</td>
                            <td>%.2f</td>
                            <td>%.2f</td>
                            </tr>""",
                    date,
                    date + 1,
                    this.room.getBasePrice(),
                    this.hotel.getPriceModifier(date),
                    this.getDiscountCodeModifier(date),
                    this.getPriceForNight(date));

        return breakdown;
    }

    /**
     * Returns the name of the {@link Room} tied to this reservation.
     * 
     * @return the name of the room tied to this reservation
     */
    public String getRoomName() {
        return room.getName();
    }

    /**
     * Removes this reservation from the list of reservations in its associated
     * {@link Room}.
     * 
     * @return {@code true} if the reservation was successfully removed,
     *         {@code false} otherwise
     * @see Room#removeReservation(Reservation)
     */
    public boolean removeFromRoom() {
        return room.removeReservation(this);
    }

    /**
     * {@inheritDoc} Includes the guest name, the room, check-in and check-out
     * days, total price, and a price breakdown.
     * 
     * @see Room#toString()
     * @see #getPriceBreakdown()
     */
    @Override
    public String toString() {
        return String.format("""
                <h2>Reservation by %s</h2>
                <ul>
                <li>Room: %s</li>
                <li>Duration: from %d to %d</li>
                <li>Total price: %.2f</li>
                <li>Discount code: %s</li>
                </ul>
                %s""",
                this.getGuestName(),
                this.room.toStringMinimal(),
                this.getCheckIn(),
                this.getCheckOut(),
                this.getTotalPrice(),
                this.discountCode,
                this.getPriceBreakdown());
    }
}
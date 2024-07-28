package src.model;

import java.util.ArrayList;
import java.util.Arrays;

/** Represents a hotel that may be added to a {@link ReservationSystem}. */
public class Hotel {
    /** The name of the hotel. */
    private String name;

    /** The list of {@link Room}s tied to the hotel. */
    private ArrayList<Room> rooms;

    /** The list of {@link Reservation}s tied to the hotel. */
    private ArrayList<Reservation> reservations;

    /**
     * The base price for the hotel. New {@link Room}s created within the hotel
     * will have this set as their base price.
     */
    private double basePrice;

    /** The "highest room number" currently in the hotel. */
    private int lastRoomNumber;

    /** The price modifiers for each night. */
    private double[] priceModifiers;

    // Status codes

    public static final int RESERVATION_SUCCESS = 0;
    public static final int RESERVATION_ERROR_INVALID_TIME = 1;
    public static final int RESERVATION_ERROR_INVALID_ROOM = 2;
    public static final int RESERVATION_ERROR_UNAVAILABLE_ROOM = 3;
    public static final int RESERVATION_ERROR_INVALID_DISCOUNT_CODE = 4;

    public static final int SET_BASE_PRICE_SUCCESS = 0;
    public static final int SET_BASE_PRICE_ERROR_LESS_THAN_MIN = 1;
    public static final int SET_BASE_PRICE_ERROR_RESERVATIONS_EXIST = 2;

    /**
     * Initializes a new hotel instance given a name. The created hotel begins
     * with zero reservations, a base price of 1299.00, and one room.
     * 
     * @param name The name of the hotel
     */
    public Hotel(String name) {
        this.name = name;

        /* Create empty lists for rooms and reservations */
        this.rooms = new ArrayList<Room>();
        this.reservations = new ArrayList<Reservation>();

        this.basePrice = 1299;
        this.lastRoomNumber = 0;

        /* Add a single room */
        this.addRooms(1, 1);

        priceModifiers = new double[31];
        Arrays.fill(priceModifiers, 1.0);
    }

    /** {@return the name of the hotel} */
    public String getName() {
        return this.name;
    }

    /** {@return the base price of the hotel} */
    public double getBasePrice() {
        return this.basePrice;
    }

    /** {@return the price modifier for a given night} */
    public double getPriceModifier(int night) {
        return this.priceModifiers[night - 1];
    }

    /**
     * Returns the number of {@link Room}s tied to the hotel.
     * 
     * @return the number of rooms tied to the hotel.
     */
    public int getRoomCount() {
        return this.rooms.size();
    }

    /**
     * Returns the number of {@link DeluxeRoom}s tied to the hotel.
     * 
     * @return the number of deluxe rooms tied to the hotel.
     */
    public int getDeluxeRoomCount() {
        int ctr = 0;
        for (Room r : rooms) {
            ctr += (r instanceof DeluxeRoom) ? 1 : 0;
        }
        return ctr;
    }

    /**
     * Returns the number of {@link ExecutiveRoom}s tied to the hotel.
     * 
     * @return the number of executive rooms tied to the hotel.
     */
    public int getExecutiveRoomCount() {
        int ctr = 0;
        for (Room r : rooms) {
            ctr += (r instanceof ExecutiveRoom) ? 1 : 0;
        }
        return ctr;
    }

    /**
     * Returns the hotel's estimated earnings, calculated as the sum of the
     * total prices of each reservation.
     * 
     * @return the calculated estimated earnings
     * @see Reservation#getTotalPrice()
     */
    public double getEarnings() {
        double earnings = 0.0;
        for (Reservation r : this.reservations)
            earnings += r.getTotalPrice();

        return earnings;
    }

    /**
     * Returns the number of {@link Reservation}s tied to the hotel.
     * 
     * @return the number of reservations tied to the hotel.
     */
    public int getReservationCount() {
        return this.reservations.size();
    }

    /**
     * Returns the number of {@link Reservation}s tied to the hotel for a given
     * date.
     * 
     * @param date            The date to check for reservations
     * @param excludeCheckOut Flag to exclude reservations that check out on the
     *                        date provided. If {@code true}, then a reservation
     *                        whose check-out date is {@code date} will be
     *                        counted.
     * @return the number of reservations tied to the hotel for a given date
     * @see #getAvailableRoomCount(int)
     */
    public int getReservationCountOnDate(int date, boolean excludeCheckOut) {
        int retval = 0;

        for (Reservation i : this.reservations)
            if (date >= i.getCheckIn() &&
                    (excludeCheckOut
                            /*
                             * Use a different condition when including
                             * reservations that check out on the given date
                             */
                            ? date < i.getCheckOut()
                            : date <= i.getCheckOut()))
                retval++;

        return retval;
    }

    /**
     * Returns a list of dates during which a {@link Room} at a given index is
     * available.
     * 
     * @param roomIndex The index of the room to inspect
     * @return a list of dates on which a room is available
     * @see Room#getAvailableDates()
     */
    public ArrayList<Integer> getAvailableDatesForRoom(int roomIndex) {
        return this.rooms.get(roomIndex).getAvailableDates();
    }

    /**
     * Returns a calendar string indicating the dates during which a
     * {@link Room} instance at a given index is available.
     * 
     * @param roomIndex The index of the room to inspect
     * @return a formatted calendar string showing the dates on which a room is
     *         available
     */
    public String getCalendarStringForRoom(int roomIndex) {
        return this.rooms.get(roomIndex).getAvailableDatesAsCalendarString();
    }

    /**
     * Returns a string representation of a {@link Room} at a given index.
     * 
     * @param roomIndex The index of the room to inspect
     * @return a string representation of the data stored in a room
     * @see Room#toString()
     */
    public String getRoomString(int roomIndex) {
        return this.rooms.get(roomIndex).toString();
    }

    /**
     * Returns a string representation of a {@link Reservation} at a given
     * index.
     * 
     * @param reservationIndex The index of a reservation to inspect
     * @return a string representation of the data stored in a reservation
     * @see Reservation#toString()
     */
    public String getReservationString(int reservationIndex) {
        return this.reservations.get(reservationIndex).toString();
    }

    /**
     * Sets the hotel name. Accessible from {@link ReservationSystem}.
     * 
     * @param name The name of the hotel
     * @see ReservationSystem#renameHotel(int, String)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the base price for the hotel and all of its rooms. Fails if
     * attempting to set a base price of lower than 100.00 or if any
     * {@link Reservation} instances are tied to the hotel.
     * 
     * @param basePrice The new base price to set
     * @return {@code true} if the base price was set successfully,
     *         {@code false} otherwise
     * @see Room#setBasePrice(double)
     */
    public int setBasePrice(double basePrice) {
        if (basePrice < 100)
            return SET_BASE_PRICE_ERROR_LESS_THAN_MIN;

        if (!this.reservations.isEmpty())
            return SET_BASE_PRICE_ERROR_RESERVATIONS_EXIST;

        this.basePrice = basePrice;

        for (Room room : this.rooms)
            room.setBasePrice(basePrice);

        return SET_BASE_PRICE_SUCCESS;
    }

    /**
     * Sets the price modifier for a given date. Fails if attempting to set a
     * value greater than {@code 1.50} or lower than {@code 0.50}.
     * 
     * @param date     The date to set a price modifier for
     * @param modifier The new price modifier to set
     * @return {@code true} if the price modifier for the date was set
     *         successfully, {@code false} otherwise.
     */
    public boolean setPriceModifier(int date, double modifier) {
        if (modifier < 0.5 || modifier > 1.5)
            return false;
        else if (date < 1 || date > 31)
            return false;
        
        for (Room i : rooms)
            if (!i.isAvailableOn(date))
                return false;

        this.priceModifiers[date - 1] = modifier;
        return true;
    }

    /**
     * Adds a given number of {@link Room}s to the hotel. Room names follow a
     * format starting with {@code RM001} for the first room. New rooms will
     * always increment the number by 1, so the next room would be
     * {@code RM002}. This occurs even if the room had previously been removed
     * (i.e., even if {@code RM002} were to be removed, the next room would
     * still be {@code RM003}).
     * 
     * @param count The number of rooms to add
     * @param type  The type of the room ({@code 1} for normal, {@code 2} for
     *              deluxe, {@code 3} for executive).
     */
    public void addRooms(int count, int type) {
        String formatString = "RM%03d";

        /*
         * TODO: We can maybe do something with generic types and a factory
         * design pattern because this looks pretty bad
         */
        switch (type) {
            default:
            case 1:
                for (int i = 0; i < count && this.rooms.size() < 50; i++)
                    this.rooms.add(
                            new Room(
                                    String.format(formatString,
                                            1 + this.lastRoomNumber++),
                                    this.basePrice));
                break;
            case 2:
                formatString += "-DX";
                for (int i = 0; i < count && this.rooms.size() < 50; i++)
                    this.rooms.add(
                            new DeluxeRoom(
                                    String.format(formatString,
                                            1 + this.lastRoomNumber++),
                                    this.basePrice));
                break;
            case 3:
                formatString += "-EX";
                for (int i = 0; i < count && this.rooms.size() < 50; i++)
                    this.rooms.add(
                            new ExecutiveRoom(
                                    String.format(formatString,
                                            1 + this.lastRoomNumber++),
                                    this.basePrice));
                break;
        }
    }

    /**
     * Removes a {@link Room} at a given index from the hotel. Fails if the room
     * has any reservations tied to it or if the index is out of range.
     * 
     * @param roomIndex The index of the room to remove
     * @return {@code true} if the room was removed successfully, {@code false}
     *         otherwise
     */
    public boolean removeRoom(int roomIndex) {
        if (this.rooms.get(roomIndex).getReservationCount() > 0
                || roomIndex < 0
                || roomIndex >= this.rooms.size()
                || this.rooms.size() <= 1)
            return false;

        this.rooms.remove(roomIndex);
        return true;
    }

    /**
     * Creates a new {@link Reservation} given booking information and the index
     * of the {@link Room} for which a reservation will be made. The reservation
     * will then be tied to both the hotel and the selected room. Fails if the
     * check-in date is outside the valid range ({@code 1} to {@code 30}), the
     * check-out date is before the check-in date, if there are availability
     * conflicts with the room, if the room index is out of range, or if the
     * discount code used is invalid for the reservation.
     * <p>
     * Note that a reservation can be made with a check-out date that overlaps
     * with the check-in date of another reservation. This is because a room is
     * marked as available on a date even if there exist reservations that check
     * out on that date.
     * 
     * @param guestName The name of the guest
     * @param checkIn   The check-in date
     * @param checkOut  The check-out date
     * @param roomIndex The index of the room to book a reservation for
     * @return {@code true} if a reservation was made successfully,
     *         {@code false} otherwise
     * @see Room#isAvailableOn(int)
     */
    public int addReservation(String guestName, int checkIn, int checkOut,
            int roomIndex, String discountCode) {
        if (checkIn > 30 || checkIn < 1 || checkOut <= checkIn) {
            return RESERVATION_ERROR_INVALID_TIME;
        }

        if (roomIndex >= this.rooms.size() || roomIndex < 0) {
            return RESERVATION_ERROR_INVALID_ROOM;
        }

        Room room = this.rooms.get(roomIndex);
        /* No need to check validity on check-out date */
        for (int date = checkIn; date < checkOut; date++) {
            if (!room.isAvailableOn(date)) {
                return RESERVATION_ERROR_UNAVAILABLE_ROOM;
            }
        }

        Reservation reservation = new Reservation(
                this, guestName, checkIn, checkOut, room);

        if (discountCode != null && !reservation.setDiscountCode(discountCode))
            return RESERVATION_ERROR_INVALID_DISCOUNT_CODE;

        this.reservations.add(reservation);

        /* Also add the reservation to the room */
        room.addReservation(reservation);
        return RESERVATION_SUCCESS;
    }

    /**
     * Removes a {@link Reservation} at a given index.
     * 
     * @param index The index of the reservation to remove
     * @return {@code true} if a reservation was successfully removed,
     *         {@code false} if the index given is out of range
     */
    public boolean removeReservation(int index) {
        if (index < 0 || index >= this.reservations.size())
            return false;

        Reservation reservation = this.reservations.get(index);
        /* Also remove the reservation from the room it is tied to */
        reservation.removeFromRoom();
        this.reservations.remove(index);

        return true;
    }

    /**
     * Returns a primitive string array containing the names of all {@link Room}
     * instances in the hotel in the same order they appear in the hotel's list.
     * 
     * @return An array containing the names of all rooms in the hotel
     */
    public String[] getRoomNames() {
        int i, count = this.rooms.size();
        String[] retval = new String[count];

        for (i = 0; i < count; i++)
            retval[i] = this.rooms.get(i).getName();

        return retval;
    }

    /**
     * Returns a primitive string array containing the names of all
     * {@link Reservation}s in the hotel in the same order they appear in the
     * hotel's list. This name is formatted as {@code RMXXX: Guest}.
     * 
     * @return An array containing the names of all reservations in the hotel
     */
    public String[] getReservationNames() {
        int i, count = this.reservations.size();
        String[] retval = new String[count];
        Reservation reservation;

        for (i = 0; i < count; i++) {
            reservation = this.reservations.get(i);
            retval[i] = reservation.getRoomName() + ": "
                    + reservation.getGuestName();
        }

        return retval;
    }

    /**
     * Returns the number of available rooms available on a given date.
     * Calculated by subtracting the number of reservations on a given date
     * (excluding those that check out on that date) from the total number of
     * rooms.
     * 
     * @param date The date to inspect
     * @return the number of available rooms on the given date
     */
    public int getAvailableRoomCount(int date) {
        return this.getRoomCount()
                /* Exclude reservations that check out on the date */
                - this.getReservationCountOnDate(date, true);
    }

    /**
     * {@inheritDoc} Includes its name, room count, and estimated earnings.
     * 
     * @see #getEarnings()
     */
    @Override
    public String toString() {
        return String.format("""
                <h2>%s</h2>
                <ul>
                  <li>Rooms: %d<br></li>
                  <ul>
                  <li>of which Normal Rooms: %d</li>
                  <li>of which Deluxe Rooms: %d</li>
                  <li>of which Executive Rooms: %d</li>
                  </ul>
                  <li>Estimated earnings: %.2f</li>
                </ul>""",
                this.getName(),
                this.getRoomCount(),
                this.getRoomCount() - this.getDeluxeRoomCount()
                        - this.getExecutiveRoomCount(),
                this.getDeluxeRoomCount(),
                this.getExecutiveRoomCount(),
                this.getEarnings());
    }

    // ?
    public Room getRoom(int roomIndex) {
        return this.rooms.get(roomIndex);
    }
}
package src.model;

import java.util.ArrayList;

/** Represents the reservation system managing a list of hotels. */
public class ReservationSystem {
    /** The list of all hotels in the system. */
    private ArrayList<Hotel> hotels;
    private ReservationBuilder reservationBuilder;

    /**
     * Initializes a reservation system containing zero instances of
     * {@link Hotel}. The reservation system starts with no hotels and may have
     * zero or multiple at any given time.
     */
    public ReservationSystem() {
        this.hotels = new ArrayList<Hotel>();
        this.reservationBuilder = new ReservationBuilder();
    }

    /**
     * Returns the {@link Hotel} at a given index.
     * 
     * @param index The index of the hotel to be retrieved from the system
     * @return the hotel instance at the given index, or {@code null} if the
     *         index is out of range
     */
    private Hotel getHotel(int index) {
        if (index < 0 || index >= this.hotels.size())
            return null;
        return this.hotels.get(index);
    }

    /**
     * Removes a {@link Hotel} at a given index from the list of hotels in the
     * system.
     * 
     * @param index The index of the hotel to be removed from the system
     * @return {@code true} if a hotel was removed successfully, {@code false}
     *         if the index given is out of range
     */
    public boolean removeHotel(int index) {
        if (index < 0 || index >= this.hotels.size()) {
            return false;
        }
        this.hotels.remove(index);
        return true;
    }

    /**
     * Removes a reservation from a given hotel.
     * 
     * @param hotelIndex       The index of the hotel to remove a reservation
     *                         from
     * @param reservationIndex The index of the reservation to remove
     * @return {@code true} if the reservation was removed successfully,
     *         {@code false} otherwise
     */
    public boolean removeReservation(int hotelIndex, int reservationIndex) {
        return this.getHotel(hotelIndex).removeReservation(reservationIndex);
    }

    /**
     * Checks if a given name is already assigned to a {@link Hotel} in the
     * system. Used when creating a new hotel with {@link #addHotel(Hotel)} or
     * when renaming an existing hotel with {@link #renameHotel(int, String)}
     * 
     * @param name The name to validate
     * @return {@code true} if a hotel with the given name exists in the system,
     *         {@code false} otherwise
     */
    private boolean hotelNameExists(String name) {
        for (Hotel i : this.hotels)
            if (i.getName().equals(name))
                return true;

        return false;
    }

    /**
     * Adds a given {@link Hotel} to the list of hotels in the system. Fails if
     * a hotel with the same name already exists in the system.
     * 
     * @param name The name of the new hotel instance to add to the system
     * @return {@code true} if the hotel was created successfully, {@code false}
     *         otherwise
     */
    public boolean addHotel(String name) {
        if (hotelNameExists(name) || name == null)
            return false;

        return this.hotels.add(new Hotel(name));
    }

    /**
     * Renames a given {@link Hotel} at a given index within the list of hotels
     * in the system. Fails if a hotel with the same name as the inputted name
     * already exists in the system.
     * 
     * @param index The index of the hotel to be renamed
     * @param name  The new name of the hotel
     * @return {@code true} if the hotel was renamed successfully, {@code false}
     *         otherwise
     */
    public boolean renameHotel(int index, String name) {
        if (hotelNameExists(name))
            return false;

        getHotel(index).setName(name);
        return true;
    }

    /**
     * {@return the list of hotel names in the system} Name strings are stored
     * in a new {@link ArrayList}.
     */
    public ArrayList<String> getHotelNamesAsList() {
        ArrayList<String> hotelNames = new ArrayList<String>();
        for (Hotel h : hotels) {
            hotelNames.add(h.getName());
        }
        return hotelNames;
    }

    /**
     * Returns the number of {@link Hotel} instances currently stored.
     * 
     * @return An integer representing the number of hotels in the system.
     */
    public int getHotelCount() {
        return this.hotels.size();
    }

    /**
     * {@return the reservation count on a specified date for the given hotel}
     *
     * @param hotelIndex      the index of the hotel
     * @param date            the date to check
     * @param excludeCheckOut {@code true} to exclude reservations checking out
     *                        on the date, {@code false} otherwise
     * @see Hotel#getReservationCountOnDate
     */
    public int getReservationCountOnDate(int hotelIndex, int date,
            boolean excludeCheckOut) {
        return this.getHotel(hotelIndex).getReservationCountOnDate(date,
                excludeCheckOut);
    }

    /**
     * {@return the available room count on a specified date for the given
     * hotel}
     *
     * @param hotelIndex the index of the hotel
     * @param date       the date to check
     * @see Hotel#getAvailableRoomCount
     */
    public int getAvailableRoomCount(int hotelIndex, int date) {
        return this.getHotel(hotelIndex).getAvailableRoomCount(date);
    }

    /**
     * {@return an array of containing the room names for the given hotel}
     *
     * @param hotelIndex the index of the hotel
     * @see Hotel#getRoomNames
     */
    public String[] getRoomNames(int hotelIndex) {
        return this.getHotel(hotelIndex).getRoomNames();
    }

    /**
     * {@return the string representation of the given hotel}
     *
     * @param hotelIndex the index of the hotel
     * @see Hotel#toString
     */
    public String getHotelString(int hotelIndex) {
        return this.getHotel(hotelIndex).toString();
    }

    /**
     * {@return the name of the given hotel}
     *
     * @param hotelIndex the index of the hotel
     * @see Hotel#getName
     */
    public String getHotelName(int hotelIndex) {
        return this.getHotel(hotelIndex).getName();
    }

    /**
     * {@return the base price of the given hotel}
     *
     * @param hotelIndex the index of the hotel
     * @see Hotel#getBasePrice
     */
    public double getBasePrice(int hotelIndex) {
        return this.getHotel(hotelIndex).getBasePrice();
    }

    /**
     * Sets the base price of the given hotel.
     *
     * @param hotelIndex the index of the hotel
     * @param basePrice  the base price to set
     * @return an int indicating the result of the operation
     * @see Hotel#setBasePrice
     */
    public int setBasePrice(int hotelIndex, double basePrice) {
        return this.getHotel(hotelIndex).setBasePrice(basePrice);
    }

    /**
     * {@return the price modifier for the given hotel on the specified date}
     *
     * @param hotelIndex the index of the hotel
     * @param date       the date to check
     * @see Hotel#getPriceModifier
     */
    public double getPriceModifier(int hotelIndex, int date) {
        return this.getHotel(hotelIndex).getPriceModifier(date);
    }

    /**
     * Sets the price modifier for the given hotel on the specified date.
     *
     * @param hotelIndex    the index of the hotel
     * @param date          the date to set the price modifier for
     * @param priceModifier the price modifier to set
     * @return an integer value indicating the result of the operation
     * @see Hotel#setPriceModifier
     */
    public int setPriceModifier(int hotelIndex, int date,
            double priceModifier) {
        return this.getHotel(hotelIndex).setPriceModifier(date, priceModifier);
    }

    /**
     * {@return the room count for the given hotel}
     *
     * @param hotelIndex the index of the hotel
     * @see Hotel#getRoomCount
     */
    public int getRoomCount(int hotelIndex) {
        return this.getHotel(hotelIndex).getRoomCount();
    }

    /**
     * {@return the string representation of the specified room in the given
     * hotel}
     *
     * @param hotelIndex the index of the hotel
     * @param roomIndex  the index of the room
     * @see Hotel#getRoomString
     */
    public String getRoomString(int hotelIndex, int roomIndex) {
        return this.getHotel(hotelIndex).getRoomString(roomIndex);
    }

    /**
     * Removes the specified room from the given hotel.
     *
     * @param hotelIndex the index of the hotel
     * @param roomIndex  the index of the room to remove
     * @return an integer value indicating the result of the operation
     * @see Hotel#removeRoom
     */
    public int removeRoom(int hotelIndex, int roomIndex) {
        return this.getHotel(hotelIndex).removeRoom(roomIndex);
    }

    /**
     * Adds a reservation to the given hotel.
     *
     * @param hotelIndex   the index of the hotel
     * @param guestName    the name of the guest
     * @param checkIn      the check-in date
     * @param checkOut     the check-out date
     * @param roomIndex    the index of the room
     * @param discountCode the discount code, if any
     * @return an integer value indicating the result of the operation
     * @see Hotel#addReservation
     */
    public int addReservation(int hotelIndex, String guestName, int checkIn,
            int checkOut, int roomIndex, String discountCode) {
        return this.getHotel(hotelIndex).addReservation(guestName, checkIn,
                checkOut, roomIndex, discountCode);
    }

    /**
     * Adds a reservation using a {@link ReservationBuilder}.
     *
     * @param builder the {@link ReservationBuilder} containing reservation
     *                details
     * @return an int indicating the result of the operation
     * @see #addReservation(int, String, int, int, int, String)
     */
    public int addReservation(ReservationBuilder builder) {
        return addReservation(builder.getHotelIndex(), builder.getGuestName(),
                builder.getCheckIn(), builder.getCheckOut(),
                builder.getRoomIndex(), builder.getDiscountCode());
    }

    /**
     * {@return a list of available dates for the specified room in the given
     * hotel}
     *
     * @param hotelIndex the index of the hotel
     * @param roomIndex  the index of the room
     * @see Hotel#getAvailableDatesForRoom
     */
    public ArrayList<Integer> getAvailableDatesForRoom(int hotelIndex,
            int roomIndex) {
        return this.getHotel(hotelIndex).getAvailableDatesForRoom(roomIndex);
    }

    /**
     * Adds rooms to the given hotel.
     *
     * @param hotelIndex the index of the hotel
     * @param amount     the number of rooms to add
     * @param roomType   the type of the rooms to add ({@code 1} for normal,
     *                   {@code 2} for deluxe, {@code 3} for executive).
     * @see Hotel#addRooms
     */
    public void addRooms(int hotelIndex, int amount, int roomType) {
        this.getHotel(hotelIndex).addRooms(amount, roomType);
    }

    /* Reservation builder methods */

    /** {@return the current active reservation builder} */
    public ReservationBuilder getReservationBuilder() {
        return reservationBuilder;
    }

    /**
     * {@return a string representation of the reservation builder}
     * 
     * @see ReservationBuilder#toString()
     */
    public String getReservationBuilderString() {
        Hotel hotel = this.getHotel(reservationBuilder.getHotelIndex());
        Reservation reservation = new Reservation(
                hotel,
                reservationBuilder.getGuestName(),
                reservationBuilder.getCheckIn(),
                reservationBuilder.getCheckOut(),
                hotel.getRoomName(reservationBuilder.getRoomIndex()));
        reservation.setDiscountCode(reservationBuilder.getDiscountCode());
        return reservation.toString();
    }

    /**
     * Reset the {@link ReservationBuilder}'s attributes, except for the
     * currently-selected hotel and room (those get changed when the user)
     * selects a new {@link Hotel} or a new {@link Room} from the corresponding
     * lists.
     */
    public void resetReservationBuilder() {
        reservationBuilder.reset();
    }

    /**
     * {@return an array of reservation names for the given hotel}
     *
     * @param hotelIndex the index of the hotel
     * @see Hotel#getReservationNames
     */
    public String[] getReservationNames(int hotelIndex) {
        return this.getHotel(hotelIndex).getReservationNames();
    }

    /**
     * {@return the string representation of the specified reservation in the
     * given hotel}
     *
     * @param hotelIndex       the index of the hotel
     * @param reservationIndex the index of the reservation
     * @see Hotel#getReservationString
     */
    public String getReservationString(int hotelIndex, int reservationIndex) {
        return this.getHotel(hotelIndex).getReservationString(reservationIndex);
    }

    /**
     * {@return the reservation count for the given hotel}
     *
     * @param hotelIndex the index of the hotel
     * @see Hotel#getReservationCount
     */
    public int getReservationCount(int hotelIndex) {
        return this.getHotel(hotelIndex).getReservationCount();
    }

    /**
     * Validate a date, i.e., check if a date is within 1-31.
     * 
     * @param date the date to validate
     * @return {@code true} if the date is within 1-31, else {@code false}
     */
    public static boolean validateDate(int date) {
        return (date >= 1 && date <= 31);
    }
}
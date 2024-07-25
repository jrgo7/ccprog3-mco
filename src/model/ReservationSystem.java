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
     * Checks if the system contains at least one hotel.
     * 
     * @return {@code true} if at least one hotel exists in the system,
     *         {@code false} otherwise
     */
    public boolean hasHotels() {
        return this.hotels.size() > 0;
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
     * Returns a primitive string array containing the names of all
     * {@link Hotel} instances in the system in the same order they appear in
     * the system's list.
     * 
     * @return An array containing the names of all hotels in the system
     * @see Hotel#getName()
     */
    public String[] getHotelNames() {
        int count = this.hotels.size();
        /* Returned array must be of the same length as the list of hotels */
        String[] names = new String[count];

        for (int i = 0; i < count; i++)
            names[i] = this.hotels.get(i).getName();

        return names;
    }

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

    public int getReservationCountOnDate(
            int hotelIndex, int date, boolean excludeCheckOut) {
        return this.getHotel(hotelIndex).getReservationCountOnDate(date, excludeCheckOut);
    }

    public int getAvailableRoomCount(int hotelIndex, int date) {
        return this.getHotel(hotelIndex).getAvailableRoomCount(date);
    }

    public String[] getRoomNames(int hotelIndex) {
        return this.getHotel(hotelIndex).getRoomNames();
    }

    public String getHotelString(int hotelIndex) {
        return this.getHotel(hotelIndex).toString();
    }

    public String getHotelName(int hotelIndex) {
        return this.getHotel(hotelIndex).getName();
    }

    public double getBasePrice(int hotelIndex) {
        return this.getHotel(hotelIndex).getBasePrice();
    }

    public int setBasePrice(int hotelIndex, double basePrice) {
        return this.getHotel(hotelIndex).setBasePrice(basePrice);
    }

    public double getPriceModifier(int hotelIndex, int date) {
        return this.getHotel(hotelIndex).getPriceModifier(date);
    }

    public boolean setPriceModifier(int hotelIndex, int date, double priceModifier) {
        return this.getHotel(hotelIndex).setPriceModifier(date, priceModifier);
    }

    public int getRoomCount(int hotelIndex) {
        return this.getHotel(hotelIndex).getRoomCount();
    }

    public String getRoomString(int hotelIndex, int roomIndex) {
        return this.getHotel(hotelIndex).getRoomString(roomIndex);
    }

    public boolean removeRoom(int hotelIndex, int roomIndex) {
        return this.getHotel(hotelIndex).removeRoom(roomIndex);
    }

    public int addReservation(
            int hotelIndex, String guestName, int checkIn, int checkOut,
            int roomIndex, String discountCode) {
        return this.getHotel(hotelIndex).addReservation(
                guestName, checkIn, checkOut, roomIndex, discountCode);
    }

    public int addReservation(ReservationBuilder builder) {
        return addReservation(
                builder.getHotelIndex(), builder.getGuestName(),
                builder.getCheckIn(), builder.getCheckOut(),
                builder.getRoomIndex(), builder.getDiscountCode());
    }

    public ArrayList<Integer> getAvailableDatesForRoom(
            int hotelIndex, int roomIndex) {
        return this.getHotel(hotelIndex).getAvailableDatesForRoom(roomIndex);
    }

    public void addRooms(int hotelIndex, int amount, int roomType) {
        this.getHotel(hotelIndex).addRooms(amount, roomType);
    }

    // reservation builder

    public ReservationBuilder getReservationBuilder() {
        return reservationBuilder;
    }

    public String getReservationBuilderString() {
        Reservation reservation = new Reservation(
                this.getHotel(reservationBuilder.getHotelIndex()),
                reservationBuilder.getGuestName(),
                reservationBuilder.getCheckIn(),
                reservationBuilder.getCheckOut(),
                this.getHotel(
                        reservationBuilder.getHotelIndex())
                        .getRoom(reservationBuilder.getRoomIndex()));
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

    public String[] getReservationNames(int hotelIndex) {
        return this.getHotel(hotelIndex).getReservationNames();
    }

    public String getReservationString(int hotelIndex, int reservationIndex) {
        return this.getHotel(hotelIndex).getReservationString(reservationIndex);
    }

    public int getReservationCount(int hotelIndex) {
        return  this.getHotel(hotelIndex).getReservationCount();
    }
}
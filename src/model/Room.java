package src.model;

import java.util.ArrayList;

/** Represents a room within a {@link Hotel}. */
public class Room {
    /** The name of the room. */
    private String name;

    /** The base price of the room. Matches that of its {@link Hotel}. */
    private double basePrice;

    /** The list of {@link Reservation}s tied to the room. */
    private ArrayList<Reservation> reservations;

    /**
     * Initializes a new Room instance given a name and a base price.
     * 
     * @param name      The name of the room
     * @param basePrice The base price of the room
     */
    public Room(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
        this.reservations = new ArrayList<>();
    }

    /** {@return the name of the room} */
    public String getName() {
        return this.name;
    }

    /** {@return the base price of the room} */
    public double getBasePrice() {
        return this.basePrice;
    }

    /**
     * Sets the base price for the room. Fails if the new base price is less
     * than 100.00.
     * 
     * @param basePrice The new base price of the room
     * @return {@code true} if the base price was updated successfully,
     *         {@code false} otherwise
     */
    public boolean setBasePrice(double basePrice) {
        if (basePrice < 100)
            return false;

        this.basePrice = basePrice;
        return true;
    }

    /**
     * Adds a {@link Reservation} to the room.
     * 
     * @param reservation the reservation instance to add
     * @see Hotel#addReservation
     */
    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    /** {@return the number of reservations tied to the room} */
    public int getReservationCount() {
        return this.reservations.size();
    }

    /**
     * Removes a {@link Reservation} from the room. Fails if the list does not
     * contain the specified reservation.
     * 
     * @param reservation The reservation instance to remove
     * @return {@code true} if a reservation was successfully removed,
     *         {@code false} otherwise
     */
    public boolean removeReservation(Reservation reservation) {
        return this.reservations.remove(reservation);
    }

    /**
     * {@return a list of dates on which the room is available}
     * 
     * @see #isAvailableOn(int)
     */
    public ArrayList<Integer> getAvailableDates() {
        ArrayList<Integer> retval = new ArrayList<>();

        for (int i = 1; i <= 31; i++)
            if (this.isAvailableOn(i))
                retval.add(i);

        return retval;
    }

    /**
     * Checks whether a room is available on a given date, i.e., there are no
     * reservations for the room on a given date (excluding reservations that
     * check out on that date).
     * 
     * @param date The date to inspect
     * @return {@code true} if the room is available on the given date,
     *         {@code false} otherwise
     */
    public boolean isAvailableOn(int date) {
        for (Reservation i : this.reservations)
            /* Exclude reservations that check out on the given date */
            if (i.getCheckIn() <= date && i.getCheckOut() > date)
                return false;
        return true;
    }

    /**
     * {@inheritDoc} Includes its name and base price. A calendar containing the
     * dates on which the room is available is also shown separately in the View
     * rooms screen.
     * 
     * @return the formatted string
     */
    @Override
    public String toString() {
        return String.format("""
                <h3>%s</h3>
                <ul><li>Base price: %.2f per night</li></ul>""",
                this.getName(),
                this.getBasePrice());
    }

    /**
     * Same as toString, but meant for {@link Reservation#toString()} where the
     * room string representation is part of the reservation's.
     * 
     * @return the formatted string
     */
    public String toStringMinimal() {
        return String.format(
                """
                        <li>%s</li>
                        <ul>
                          <li>Base price: %.2f per night</li>
                        </ul>""",
                this.getName(),
                this.getBasePrice());
    }
}
/*
 * "Oops
 * 'assume that we’re working with a single month with 31 days'
 * - specs"
 * - lowy
 * Thats a suspicious part though
 * - wafl
 */

public class Date {
    private int day;

    public Date(int day) {
        this.day = day;
    }

    public int getDay() {
        return this.day;
    }

    public String toString() {
        return String.format("%d", this.getDay());
    }
}

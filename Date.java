/*
 * "Oops
 * 'assume that we’re working with a single month with 31 days'
 * - specs"
 * - lowy
 * Thats a suspicious part though
 * - wafl
 */

public class Date {
    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    int getYear() {
        return this.year;
    }

    int getMonth() {
        return this.month;
    }

    int getDay() {
        return this.day;
    }

    // TODO: Check specs
    String buildString() {
        return String.format("%d-%d-%d", this.getYear(), this.getMonth(), this.getDay());
    }
}

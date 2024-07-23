package src.view.gui.component;

import java.util.ArrayList;

public class AvailabilityCalendar extends Calendar {
    public AvailabilityCalendar() {
        super();
        this.renderer = new AvailabilityCalendarRenderer();
        this.setDefaultRenderer(Object.class, renderer);
    }

    // "White-out" unavailable dates, showing only the available dates
    public void setAvailability(ArrayList<Integer> availableDates) {
        ((AvailabilityCalendarRenderer) renderer).setAvailableDates(availableDates);
        for (int date = 1; date <= 31; date++) {
            this.setCalendarText(
                    date,
                    (availableDates.contains(date)) ? String.valueOf(date) : "X");
        }
    }
}
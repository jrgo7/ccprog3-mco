package src.view.gui.component;

import java.util.ArrayList;

/**
 * This class extends {@link Calendar} by adding functionality to set its
 * internal {@link AvailabilityCalendarRenderer} with a specific list of
 * available dates; dates not in this list will be highlighted red.
 */
public class AvailabilityCalendar extends Calendar {
    /**
     * Initialize this component with the default renderer.
     */
    public AvailabilityCalendar() {
        super();

        this.renderer = new AvailabilityCalendarRenderer();
        this.setDefaultRenderer(Object.class, renderer);
    }

    /**
     * "White-out" unavailable dates, showing only the available dates
     * 
     * @param availableDates
     */
    public void setAvailability(ArrayList<Integer> availableDates) {
        ((AvailabilityCalendarRenderer) renderer).setAvailableDates(availableDates);
        for (int date = 1; date <= 31; date++) {
            this.setCalendarText(
                    date,
                    (availableDates.contains(date)) ? String.valueOf(date) : "X");
        }
    }
}
package src.view.gui.component;

import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

import src.view.gui.TopView;

public class BookingCalendar extends AvailabilityCalendar {
    public BookingCalendar() {
        super();
        this.renderer = new BookingCalendarRenderer();
        this.setDefaultRenderer(Object.class, renderer);
        this.setCellSelectionEnabled(true);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableModel model = this.getModel();
        for (int i = 1; i <= 31; i++) {
            model.setValueAt(i, (i - 1) / MAX_COLS, (i - 1) % MAX_COLS);
        }
        this.setFont(TopView.ARIAL_PLAIN_FONT);
        this.setRowHeight(64);
    }

    // Set values for the internal renderer to render checkIn date
    public void setCalendarCheckIn(int checkIn) {
        ((BookingCalendarRenderer) renderer).setCheckIn(checkIn);
    }

    // Set values for the internal renderer to render checkOut date
    public void setCalendarCheckOut(int checkOut) {
        ((BookingCalendarRenderer) renderer).setCheckOut(checkOut);
    }

}
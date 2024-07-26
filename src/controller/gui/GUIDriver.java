package src.controller.gui;

import src.model.ReservationSystem;
import src.view.gui.TopView;

/** Handles communication between the model, view, and controller. */
public class GUIDriver {
    /** Main method for GUI implementation. */
    public static void main(String[] args) {
        ReservationSystem rs = new ReservationSystem();
        TopView view = new TopView();
        new Controller(rs, view);
    }
}

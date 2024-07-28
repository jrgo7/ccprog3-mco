package src.controller.gui;

import src.model.ReservationSystem;
import src.view.gui.TopView;

/** Handles communication between the model, view, and controller. */
public final class GUIDriver {
    /**
     * This class is not meant to be instantiated, so a private empty
     * constructor is provided.
     */
    private GUIDriver() {
        /* Implementation left blank */
    }

    /**
     * Main method for GUI implementation.
     * 
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        ReservationSystem rs = new ReservationSystem();
        TopView view = new TopView();
        new Controller(rs, view);
    }
}

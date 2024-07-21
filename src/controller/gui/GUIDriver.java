package src.controller.gui;

import src.model.ReservationSystem;
import src.view.gui.TopView;

public class GUIDriver {
    public static void main(String[] args) {
        ReservationSystem rs = new ReservationSystem();
        TopView view = new TopView();
        Controller controller = new Controller(rs, view);
    }
}

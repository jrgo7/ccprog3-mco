package src.view.gui;

import src.model.ReservationSystem;

public class GUIDriver {
    public static void main(String[] args) {
        ReservationSystem rs = new ReservationSystem();
        TopView view = new TopView();
        Controller controller = new Controller(rs, view);
    }
}

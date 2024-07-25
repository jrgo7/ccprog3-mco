package src.view.gui.component;

public class StyledButtonFactory {
    /**
     * Create a default StyledButton.
     * @param text
     * @return
     */
    public static StyledButton createButton(String text) {
        return new StyledButton(text);
    }

    /**
     * Create a StyledButton with red text. This is used for buttons which
     * perform destructive actions like removing a hotel, room, or reservation;
     * and resetting the booking screen.
     * @param text
     * @return
     */
    public static StyledButton createDestructiveButton(String text) {
        return new StyledButton(text, ColorCollection.INVALID);
    }
}

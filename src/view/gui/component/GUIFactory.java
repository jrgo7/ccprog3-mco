package src.view.gui.component;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

public class GUIFactory {
    /**
     * This class is not meant to be instantiated.
     */
    private GUIFactory() {

    }

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

    /**
     * Create a {@link StyledPanel} container for a {@link StyledHTMLPane}.
     * The container provides scrolling and screen width-fitting for the
     * HTML pane.
     * 
     * @param htmlPane
     * @return
     */
    public static StyledPanel createStyledHTMLPaneContainer(
            StyledHTMLPane htmlPane) {
        StyledPanel container = new StyledPanel();
        container.setLayout(new BorderLayout());
        StyledScrollPane scrollPane = new StyledScrollPane(
            htmlPane,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        container.add(scrollPane, BorderLayout.CENTER);
        return container;
    }

    /**
     * Create a {@link StyledPanel} container for a {@StyledPanel} panel meant
     * to act as a tab page. The container appends a title label at the top.
     */
    public static StyledPanel createTabContainer(
            String title, StyledPanel panel) {
        StyledPanel container = new StyledPanel();
        StyledLabel titleLabel = new StyledLabel(
            title, FontCollection.SEGOE_UI_TITLE);
        container.setLayout(new BorderLayout());
        container.add(titleLabel, BorderLayout.NORTH);
        container.add(panel, BorderLayout.CENTER);
        return container;
    }
}

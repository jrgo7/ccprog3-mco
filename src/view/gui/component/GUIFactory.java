package src.view.gui.component;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

/** Represents a factory for instantiating GUI elements. */
public final class GUIFactory {
    /**
     * This class is not meant to be instantiated, so a private empty
     * constructor is provided.
     */
    private GUIFactory() {
        /* Implementation left blank */
    }

    /**
     * Create a default {@link StyledButton}.
     * 
     * @param text the text in the button
     * @return the created StyledButton instance
     */
    public static StyledButton createButton(String text) {
        return new StyledButton(text);
    }

    /**
     * Create a StyledButton with red text. This is used for buttons which
     * perform destructive actions like removing a hotel, room, or reservation;
     * and resetting the booking screen.
     * 
     * @param text the text in the button
     * @return the created StyledButton instance
     */
    public static StyledButton createDestructiveButton(String text) {
        return new StyledButton(text, ColorCollection.INVALID);
    }

    /**
     * Create a {@link StyledPanel} container for a {@link StyledHTMLPane}. The
     * container provides scrolling and screen width-fitting for the HTML pane.
     * 
     * @param htmlPane the StyledHTMLPane to place in the container
     * @return the created StyledPanel instance
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
     * Create a {@link StyledPanel} container for a {@link StyledPanel} panel
     * meant to act as a tab page. The container appends a title label at the
     * top.
     * 
     * @param title the title of the panel
     * @param panel the panel to wrap in the container
     * @return the created StyledPanel container
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

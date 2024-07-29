package src.view.gui.component;

import java.awt.Font;

/**
 * These {@link Font} objects are based on Microsoft's typography guidelines
 * accessed via the following link:
 * 
 * https://learn.microsoft.com/en-us/windows/apps/design/style/xaml-theme-resources#the-xaml-type-ramp
 */
public final class FontCollection {
    /**
     * This class is not meant to be instantiated, so a private empty
     * constructor is provided.
     */
    private FontCollection() {
        /* Implementation left blank */
    }

    /** Body font */
    public static final Font SEGOE_UI_BODY = new Font("Segoe UI", Font.PLAIN,
            14);

    /** Subtitle font */
    public static final Font SEGOE_UI_SUBTITLE = new Font("Segoe UI",
            Font.PLAIN, 20);

    /** Title font */
    public static final Font SEGOE_UI_TITLE = new Font("Segoe UI", Font.PLAIN,
            28);

}

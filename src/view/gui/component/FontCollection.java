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

    /** Caption font */
    public static final Font SEGOE_UI_CAPTION = new Font("Segoe UI", Font.PLAIN,
            12);

    /** Body font */
    public static final Font SEGOE_UI_BODY = new Font("Segoe UI", Font.PLAIN,
            14);

    /** Strong (bold) body font */
    public static final Font SEGOE_UI_BODY_STRONG = new Font("Segoe UI",
            Font.BOLD, 14);

    /** Large body font */
    public static final Font SEGOE_UI_BODY_LARGE = new Font("Segoe UI",
            Font.PLAIN, 18);

    /** Subtitle font */
    public static final Font SEGOE_UI_SUBTITLE = new Font("Segoe UI",
            Font.PLAIN, 20);

    /** Title font */
    public static final Font SEGOE_UI_TITLE = new Font("Segoe UI", Font.PLAIN,
            28);

    /** Large title font */
    public static final Font SEGOE_UI_TITLE_LARGE = new Font("Segoe UI",
            Font.PLAIN, 40);

    /** Display font */
    public static final Font SEGOE_UI_DISPLAY = new Font("Segoe UI", Font.PLAIN,
            68);
}

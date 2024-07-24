package src.view.gui.component;

import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 * This class extends {@link JEditorPane} by providing it with styled CSS
 * based on Microsoft's typography guidelines accessed via the following link:
 * 
 * https://learn.microsoft.com/en-us/windows/apps/design/style/xaml-theme-resources#the-xaml-type-ramp
 */
public class StyledHTMLPane extends JEditorPane {
    public StyledHTMLPane() {
        super("text/html", "<p></p>");
        StyleSheet sheet = new HTMLEditorKit().getStyleSheet();
        sheet.addRule("""
                body {
                    font-family: 'Segoe UI';
                    font-size: 14;
                }

                h1 {
                    font-size: 28;
                }

                h2 {
                    font-size: 20;
                }

                h3 {
                    font-size: 18;

                h4 {
                    font-size: 14;
                }""");
        this.setEditable(false);
    }

}

package src.view.gui.component;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

public class StyledHTMLPaneContainerFactory {
    /**
     * This class is not meant to be instantiated.
     */
    private StyledHTMLPaneContainerFactory() {

    }

    public static StyledPanel createContainer(StyledHTMLPane htmlPane) {
        StyledPanel container = new StyledPanel();
        container.setLayout(new BorderLayout());
        StyledScrollPane scrollPane = new StyledScrollPane(
            htmlPane,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        container.add(scrollPane);
        return container;
    }
}

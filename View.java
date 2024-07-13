import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JFrame {
    private JList<String> listHotelNames;
    private ArrayList<JButton> btnOptions = new ArrayList<JButton>();

    public View() {
        super("Hotel Reservation System");
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(400, 300));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Unable to configure look and feel.");
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.init();
        this.setVisible(true);
    }

    public void init() {
        this.initHotelList();
        this.initPanelMenu();        
    }

    public void initHotelList() {
        listHotelNames = new JList<String>();
        listHotelNames.setFixedCellWidth(200);
        listHotelNames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listHotelNames.setLayoutOrientation(JList.VERTICAL);
        listHotelNames.setVisibleRowCount(-1); // Show all rows if possible
        JScrollPane scrollPaneListHotelNames = new JScrollPane(
            listHotelNames,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPaneListHotelNames, BorderLayout.CENTER);
    }

    public void initPanelMenu() {
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
        String[] options = {
            "Add hotel", "View hotel", "Manage hotel", "Simulate booking"};
        for (int i = 0; i < options.length; i++) {
            btnOptions.add(new JButton(options[i]));
            btnOptions.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            panelMenu.add(Box.createVerticalGlue()); // Glue pads unoccupied space
            panelMenu.add(btnOptions.get(i));
            panelMenu.add(Box.createVerticalGlue());
        }
        this.add(panelMenu, BorderLayout.EAST);
    }

    public void setActionListener(Controller controller) {
        for (JButton b: btnOptions) {
            b.addActionListener(controller);
        }
    }


    public String promptHotelName() {
        return JOptionPane.showInputDialog(
            "Input a name for the hotel:");
    }

    public void setHotelList(String[] hotelNames) {
        if (hotelNames.length > 0) {
            listHotelNames.setListData(hotelNames);
        } else {
            String[] addHotelText = {
                "There are no hotels in the system.",
                "Select 'Add hotel' to get started!"};
            listHotelNames.setListData(addHotelText);
        }
    }
}
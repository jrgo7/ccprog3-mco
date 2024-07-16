import javax.swing.*;
import java.awt.*;

public class ViewChooseRoom extends JDialog {
    JList<String> listRoom;
    JButton btnSelect;

    public ViewChooseRoom(
            Controller controller,
            JFrame root,
            String title,
            String buttonText,
            Hotel hotel) {
        super(root, title, true);
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(400, 300));
        this.setLocationByPlatform(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Unable to configure look and feel.");
        }
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listRoom = new JList<String>(hotel.getRoomNames());
        listRoom.setSelectedIndex(0);
        JScrollPane scrollPaneRoom = new JScrollPane(
                listRoom,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPaneRoom, BorderLayout.CENTER);
        btnSelect = new JButton(buttonText);
        this.add(btnSelect, BorderLayout.SOUTH);
    }

    public void setActionListener(Controller controller) {
        btnSelect.addActionListener(controller);
    }

    public int getSelectedRoomIndex() {
        int retval = listRoom.getSelectedIndex();
        this.setVisible(false);
        return retval;
    }
}

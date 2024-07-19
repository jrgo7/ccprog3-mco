import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

public class Calendar extends JTable {
    public Calendar() {        
        super(5, 7);
        this.setCellSelectionEnabled(true);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableModel model = getModel();
        for (int i = 1; i <= 31; i++) {
            model.setValueAt(i, (i-1) / 7, (i-1) % 7);
        }
    }

    // Prevents all cells from being edited
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    // Mark the cell corresponding to a certain `day` as unavailable,
    // i.e., set its backgronud color to red
    public void setUnavailable(int day) {

    }

    // Mark the cell corresponding to a certain `day` as available,
    // i.e., set its background color to the default
    public void setAvailable(int day) {

    }

    public void colorByAvailability(int[] availableDates) {
        // Set everything as unavailable then set the `availableDates`
    }
}
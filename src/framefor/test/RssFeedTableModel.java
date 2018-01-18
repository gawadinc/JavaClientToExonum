package framefor.test;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class RssFeedTableModel extends AbstractTableModel {

    private List<FeedMessage> entries = new ArrayList<>();

    public void updateData(List<FeedMessage> entries) {
        this.entries = entries;
        fireTableDataChanged();
    }

    public int getRowCount() {
        return entries.size();
    }

    public int getColumnCount() {
        return 2;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return entries.get(rowIndex).title;
            case 1:
                return entries.get(rowIndex).publicationDate;
        }
        return null;
    }
}

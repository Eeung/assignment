package directory;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class PDirectory extends JTable {
	DefaultTableModel tableModel;

	public PDirectory(String title) {
		Vector<String> v = new Vector<String>();
		v.add(title);
		tableModel = new DefaultTableModel(v, 0);
		setModel(tableModel);
	}

	public void setData(String fileName) {
		SDirectory sDirectory = new SDirectory();
		Vector<VDirectory> vDirectories = sDirectory.getDirectories(fileName);

		for (VDirectory vDirectory : vDirectories) {
			Vector<String> row = new Vector<String>();
			row.add(vDirectory.getName());
			tableModel.addRow(row);
		}

	}

}

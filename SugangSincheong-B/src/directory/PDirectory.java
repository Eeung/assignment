package directory;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class PDirectory extends JTable {
	private DefaultTableModel tableModel;
	private final String blank[] = null;
	private Vector<VDirectory> vDirectories;

	public PDirectory(String title) {
		this();
		String header[] = { title };
		tableModel.setColumnIdentifiers(header);
	}

	public PDirectory(String[] header) {
		this();
		tableModel.setColumnIdentifiers(header);
	}

	public PDirectory() {
		tableModel = new DefaultTableModel(blank, 0) {
			public boolean isCellEditable(int i, int j) {
				return false;
			}
		};
		this.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setModel(tableModel);
	}

	public void setData(String fileName) {
		tableModel.setNumRows(0);
		SDirectory sDirectory = new SDirectory();
		vDirectories = sDirectory.getDirectories(fileName);

		for (VDirectory vDirectory : vDirectories) {
			if (vDirectory.getInformation() == null) {
				Vector<String> row = new Vector<String>();
				row.add(vDirectory.getName());
				tableModel.addRow(row);
			} else
				tableModel.addRow(vDirectory.getInformation());
		}
		setRowSelectionInterval(0, 0);
	}

	public String getData(int index) {
		if (index >= 0)
			return vDirectories.get(index).getFileName();
		return null;
	}
}

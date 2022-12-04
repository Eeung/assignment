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
	private SDirectory sDirectory;

	public PDirectory(String title) {
		this(ListSelectionModel.SINGLE_SELECTION);
		String header[] = { title };
		tableModel.setColumnIdentifiers(header);
	}

	public PDirectory(String[] header, int selectionMode) {
		this(selectionMode);
		tableModel.setColumnIdentifiers(header);
	}

	private PDirectory(int selectionMode) {
		tableModel = new DefaultTableModel(blank, 0) {
			public boolean isCellEditable(int i, int j) {
				return false;
			}
		};
		setModel(tableModel);
		getTableHeader().setReorderingAllowed(false);
		getSelectionModel().setSelectionMode(selectionMode);
	}

	public void setData(String fileName) {
		tableModel.setNumRows(0);
		sDirectory = new SDirectory();
		vDirectories = sDirectory.getDirectories(fileName);

		for (VDirectory vDirectory : vDirectories) {
			if (vDirectory.getInformation() == null) {
				Vector<String> row = new Vector<String>();
				row.add(vDirectory.getName());
				tableModel.addRow(row);
			} else
				tableModel.addRow(vDirectory.getInformation());
		}
		if (tableModel.getRowCount() > 0)
			setRowSelectionInterval(0, 0);
	}

	public String getFile(int index) {
		if (index >= 0)
			return vDirectories.get(index).getFileName();
		return null;
	}

	// Lecture 테이블 전용
	public void saveSelectedLecture(String path) {
		sDirectory = new SDirectory();
		int rows[] = getSelectedRows();
		for (int row : rows) {
			String information = "";
			for (int i = 0; i < getColumnCount(); i++) {
				String Column = getColumnName(i);
				switch (Column) {
				case "과목코드":
				case "수업명":
				case "시간표":
					information += ((String) getValueAt(row, i)) + " ";
				}
			}
			sDirectory.writeLecture(information, path);
		}

		int count = 0;
		for (int row : rows) {
			((DefaultTableModel) getModel()).removeRow(row - count);
			count++;
		}
	}

	// Miridamgi, Sugang 테이블 전용
	public void cancelLecture(String path) {
		int rows[] = getSelectedRows();
		String[] codes = new String[rows.length];
		int i = 0;
		for (int row : rows) {
			codes[i] = (String) getValueAt(row, 0);
			i++;
		}
		setData(path);
		sDirectory = new SDirectory();
		sDirectory.cancelLectures(codes, path);
	}
}

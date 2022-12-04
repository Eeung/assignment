package directory;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class SugangPanel extends JPanel {
	private DirectoryPanel directory;
	private PDirectory miridamgi, sincheong;
	private String header[] = { "과목코드", "수업명", "시간표" };
	private PControlPanel control1, control2;
	private String id;

	public SugangPanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		ActionHandler action = new ActionHandler();

		directory = new DirectoryPanel();
		add(directory);

		control1 = new PControlPanel(action);
		add(control1);

		miridamgi = new PDirectory(header, ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		add(new JScrollPane(miridamgi));

		control2 = new PControlPanel(action);
		add(control2);

		sincheong = new PDirectory(header, ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		add(new JScrollPane(sincheong));
	}

	public void init(String id) {
		this.id = id;

		miridamgi.setData("basket/" + id);
		sincheong.setData("sugang/" + id);
		directory.department.getSelectionModel().removeListSelectionListener(directory);
		directory.department.getSelectionModel().addListSelectionListener(new LectureTableHandler());
		directory.department.getSelectionModel().addListSelectionListener(directory);
		deleteRepetitionLecture(miridamgi, directory.lecture);
		deleteRepetitionLecture(sincheong, miridamgi);
	}

	public class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String actionCommand = ((JButton) e.getSource()).getActionCommand();

			if ((control1.toRight.hashCode() + "").equals(actionCommand)) {
				directory.lecture.saveSelectedLecture("basket/" + id);
				miridamgi.setData("basket/" + id);
				deleteRepetitionLecture(sincheong, miridamgi);
			} else if ((control1.toLeft.hashCode() + "").equals(actionCommand)) {
				miridamgi.cancelLecture("basket/" + id);
				miridamgi.setData("basket/" + id);

				directory.updateData(directory.department, directory.lecture);
				deleteRepetitionLecture(miridamgi, directory.lecture);
				deleteRepetitionLecture(sincheong, miridamgi);
			} else if ((control2.toRight.hashCode() + "").equals(actionCommand)) {
				miridamgi.saveSelectedLecture("sugang/" + id);
				sincheong.setData("sugang/" + id);
			} else if ((control2.toLeft.hashCode() + "").equals(actionCommand)) {
				sincheong.cancelLecture("sugang/" + id);
				sincheong.setData("sugang/" + id);

				miridamgi.setData("basket/" + id);
				deleteRepetitionLecture(sincheong, miridamgi);
			}
		}
	}

	public class LectureTableHandler implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			String source = e.getSource().toString();
			if (!e.getValueIsAdjusting() && source.charAt(source.length() - 2) != '{')
				deleteRepetitionLecture(miridamgi, directory.lecture);
			deleteRepetitionLecture(sincheong, directory.lecture);
		}
	}

	public void deleteRepetitionLecture(PDirectory getter, PDirectory setter) {
		for (int i = 0; i < getter.getRowCount(); i++) {
			String code = (String) getter.getValueAt(i, 0);
			int count = setter.getRowCount();
			for (int j = 0; j < count; j++) {
				if (code.equals((String) setter.getValueAt(j, 0))) {
					((DefaultTableModel) setter.getModel()).removeRow(j);
					count--;
					j--;
				}
			}
		}
	}
	// for (int i = 0; i < miridamgi.getRowCount(); i++) {
	// directory.deleteRepetition((String) miridamgi.getValueAt(i, 0));
	// }
}

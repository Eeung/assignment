package directory;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class DirectoryPanel extends JPanel implements ListSelectionListener {
	private PDirectory campus, college, department, lecture;
	private String header[] = { "과목코드", "수업명", "교수명", "학점", "시간표" };

	public DirectoryPanel() {
		setLayout(new GridLayout(2, 1));

		JPanel ChooseBeforeLecture = new JPanel();
		ChooseBeforeLecture.setLayout(new GridLayout(1, 3));
		add(ChooseBeforeLecture);

		campus = new PDirectory("캠퍼스");
		ChooseBeforeLecture.add(new JScrollPane(campus));

		college = new PDirectory("대학");
		ChooseBeforeLecture.add(new JScrollPane(college));

		department = new PDirectory("학과");
		ChooseBeforeLecture.add(new JScrollPane(department));

		lecture = new PDirectory(header, ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		add(new JScrollPane(lecture));

		campus.getSelectionModel().addListSelectionListener(this);
		college.getSelectionModel().addListSelectionListener(this);
		department.getSelectionModel().addListSelectionListener(this);

		campus.setData("root");
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		String source = e.getSource().toString();
		if (!e.getValueIsAdjusting() && source.charAt(source.length() - 2) != '{') {
			System.out.println(source);
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();

			if (lsm == campus.getSelectionModel())
				updateData(campus, college);
			else if (lsm == college.getSelectionModel())
				updateData(college, department);
			else if (lsm == department.getSelectionModel())
				updateData(department, lecture);

		}
	}

	public void updateData(PDirectory getter, PDirectory target) {
		String fileName = getter.getFile(getter.getSelectedRow());
		if (fileName != null)
			target.setData(fileName);
	}
}

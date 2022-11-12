package directory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class DirectoryPanel extends JPanel implements ListSelectionListener {
	private PDirectory campus, college, department, lecture;
	private String header[] = { "과목코드", "수업명", "교수명", "학점", "시간표" };
	private String miniheader[] = { "수업명", "시간표" };

	public DirectoryPanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new GridLayout(1, 2));
		JPanel directory = new JPanel();
		directory.setLayout(new GridLayout(2, 1));
		add(directory);

		JPanel ChooseBeforeLecture = new JPanel();
		ChooseBeforeLecture.setLayout(new GridLayout(1, 3));
		directory.add(ChooseBeforeLecture);

		campus = new PDirectory("캠퍼스");
		ChooseBeforeLecture.add(new JScrollPane(campus));

		college = new PDirectory("대학");
		ChooseBeforeLecture.add(new JScrollPane(college));

		department = new PDirectory("학과");
		ChooseBeforeLecture.add(new JScrollPane(department));

		lecture = new PDirectory(header);
		directory.add(new JScrollPane(lecture));

		JPanel sinCheong = new JPanel();
		sinCheong.setLayout(new GridLayout(1, 2));
		add(sinCheong);

		JPanel basket = new JPanel();
		basket.setLayout(new BorderLayout());
		sinCheong.add(basket);

		DefaultTableModel basketModel = new DefaultTableModel(miniheader, 0);
		JScrollPane basketScroll = new JScrollPane(new JTable(basketModel));
		basket.add(basketScroll);

		JPanel toFromBasket = new JPanel();
		toFromBasket.setLayout(new GridLayout(2, 1));
		basket.add(toFromBasket, BorderLayout.WEST);

		JButton takeToBasket = new JButton(">>");
		toFromBasket.add(takeToBasket);
		JButton takeFromBasket = new JButton("<<");
		toFromBasket.add(takeFromBasket);

		JPanel succeed = new JPanel();
		succeed.setLayout(new BorderLayout());
		sinCheong.add(succeed);

		DefaultTableModel succeedModel = new DefaultTableModel(miniheader, 0);
		JScrollPane succeedScroll = new JScrollPane(new JTable(succeedModel));
		succeed.add(succeedScroll);

		JPanel toFromSucceed = new JPanel();
		toFromSucceed.setLayout(new GridLayout(2, 1));
		succeed.add(toFromSucceed, BorderLayout.WEST);

		JButton takeToSucceed = new JButton(">>");
		toFromSucceed.add(takeToSucceed);
		JButton takeFromSucceed = new JButton("<<");
		toFromSucceed.add(takeFromSucceed);

		campus.getSelectionModel().addListSelectionListener(this);
		college.getSelectionModel().addListSelectionListener(this);
		department.getSelectionModel().addListSelectionListener(this);

		campus.setData("root");
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		String fileName;

		if (!e.getValueIsAdjusting()) {
			if (lsm == campus.getSelectionModel()) {
				fileName = campus.getData(campus.getSelectedRow());
				if (fileName != null)
					college.setData(fileName);
			} else if (lsm == college.getSelectionModel()) {
				fileName = college.getData(college.getSelectedRow());
				if (fileName != null)
					department.setData(fileName);
			} else if (lsm == department.getSelectionModel()) {
				fileName = department.getData(department.getSelectedRow());
				if (fileName != null)
					lecture.setData(fileName);
			}
		}
	}
}

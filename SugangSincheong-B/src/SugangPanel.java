import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class SugangPanel extends JPanel implements ActionListener {
	JComboBox<String> campus, college, department;
	SugangDirectory SD = new SugangDirectory();
	DefaultTableModel classModel;
	String cam[] = { "용인", "서울" };
	String yong[] = { "공과", "자연과학" };
	String seo[] = { "ict", "경영" };
	String header[] = { "수업명", "교수명", "학점", "시간표" };
	String miniheader[] = { "수업명", "시간표" };
	String blank[][];
	String clas[][] = { { "통합디자인연구", "신완식", "3", "월0900-1150" }, { "디지털디자인", "조태형", "3", "화0900-1150" } };

	public SugangPanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new GridLayout(1, 2));
		JPanel directory = new JPanel();
		directory.setLayout(new BorderLayout());
		add(directory);

		JPanel ChooseBeforeClass = new JPanel();
		directory.add(ChooseBeforeClass, BorderLayout.NORTH);

		campus = new JComboBox<String>();
		campus.setModel(new DefaultComboBoxModel<String>(cam));
		campus.setActionCommand("Which is Campus");
		campus.addActionListener(this);
		ChooseBeforeClass.add(campus);

		college = new JComboBox<String>();
		college.setActionCommand("What is College");
		college.addActionListener(this);
		ChooseBeforeClass.add(college);

		department = new JComboBox<String>();
		department.setActionCommand("What is department");
		department.addActionListener(this);
		ChooseBeforeClass.add(department);

		classModel = new DefaultTableModel(clas, header);
		JTable classTable = new JTable(classModel);
		JScrollPane classScroll = new JScrollPane(classTable);
		directory.add(classScroll);

		JPanel toFromBasket = new JPanel();
		directory.add(toFromBasket, BorderLayout.EAST);
		toFromBasket.setLayout(new GridLayout(2, 1));

		JButton takeToBasket = new JButton(">>");
		toFromBasket.add(takeToBasket);
		JButton takeFromBasket = new JButton("<<");
		toFromBasket.add(takeFromBasket);

		JPanel basket = new JPanel();
		basket.setLayout(new GridLayout(1, 3, 10, 10));
		add(basket);

		DefaultTableModel basketModel = new DefaultTableModel(blank, miniheader);
		JTable basketTable = new JTable(basketModel);
		JScrollPane basketScroll = new JScrollPane(basketTable);
		basket.add(basketScroll);

		JPanel toFromSucceeded = new JPanel();
		toFromSucceeded.setLayout(new GridLayout(2, 1));
		basket.add(toFromSucceeded);

		JButton takeToSucceeded = new JButton(">>");
		toFromSucceeded.add(takeToSucceeded);
		JButton takeFromSucceeded = new JButton("<<");
		toFromSucceeded.add(takeFromSucceeded);

		DefaultTableModel succeededModel = new DefaultTableModel(blank, miniheader);
		JTable succeededTable = new JTable(succeededModel);
		JScrollPane succeededScroll = new JScrollPane(succeededTable);
		basket.add(succeededScroll);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = ((JComboBox) e.getSource()).getActionCommand();
		String selected;

		switch (actionCommand) {
		case "Which is Campus":
			selected = campus.getSelectedItem().toString();
			String[] col = SD.FindCampus(selected);
			college.setModel(new DefaultComboBoxModel<String>(col));
			break;
		case "What is College":
			selected = college.getSelectedItem().toString();
			String[] dep = SD.FindCollege(selected);
			department.setModel(new DefaultComboBoxModel<String>(dep));
			break;
		case "What is department":
			classModel.setNumRows(0);
			selected = department.getSelectedItem().toString();
			String[][] cla = SD.FindDepartment(selected);
			for (int i = 0; i < cla.length; i++) {
				classModel.addRow(cla[i]);
			}
			break;
		}

	}

}

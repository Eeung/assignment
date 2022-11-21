package directory;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class SugangPanel extends JPanel {
	private DirectoryPanel directoryPanel;
	private PDirectory miridamgi, sincheong;
	private String header[] = { "수업명", "시간표" };
	private PControlPanel controlPanel1, controlPanel2;

	public SugangPanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		directoryPanel = new DirectoryPanel();
		add(directoryPanel);

		controlPanel1 = new PControlPanel();
		add(controlPanel1);

		miridamgi = new PDirectory(header, ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		add(new JScrollPane(miridamgi));

		controlPanel2 = new PControlPanel();
		add(controlPanel2);

		sincheong = new PDirectory(header, ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		add(new JScrollPane(sincheong));
	}
}

package directory;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PControlPanel extends JPanel {

	private JButton toRight, toLeft;

	public PControlPanel() {
		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layoutManager);

		toRight = new JButton(">>");
		toLeft = new JButton("<<");

		add(toRight);
		add(toLeft);
	}
}

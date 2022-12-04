package directory;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import directory.SugangPanel.ActionHandler;

@SuppressWarnings("serial")
public class PControlPanel extends JPanel {

	protected JButton toRight, toLeft;

	public PControlPanel(ActionHandler action) {
		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layoutManager);

		toRight = new JButton(">>");
		toRight.setActionCommand(toRight.hashCode() + "");
		toRight.addActionListener(action);

		toLeft = new JButton("<<");
		toLeft.setActionCommand(toLeft.hashCode() + "");
		toLeft.addActionListener(action);

		add(toRight);
		add(toLeft);
	}
}

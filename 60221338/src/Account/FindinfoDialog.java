package Account;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FindinfoDialog extends JDialog implements ActionListener {
	private JTextField NMinput;
	private JLabel foundID;
	private CardLayout card = new CardLayout();
	private JPanel findPg;

	public FindinfoDialog() {
		setModal(true);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screen.width / 4, screen.height / 3);
		setMinimumSize(new Dimension(screen.width / 4, screen.height / 3));

		findPg = new JPanel(card);
		add(findPg);

		JPanel IDfind = new JPanel(new GridLayout(5, 1, 0, 10));
		{
			IDfind.add(new JPanel());

			JPanel NM = new JPanel(new BorderLayout());
			{
				JLabel NMtxt = new JLabel("이름:  ");
				NM.add(NMtxt, BorderLayout.WEST);
				NMinput = new JTextField();
				NMinput.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent ke) {
						JTextField src = (JTextField) ke.getSource();
						if (src.getText().length() >= 15)
							ke.consume();
					}
				});
				NM.add(NMinput);
			}
			IDfind.add(NM);

			foundID = new JLabel("찾은 아이디:");
			IDfind.add(foundID);

			JButton findId = new JButton("아이디 찾기");
			findId.setActionCommand("findId");
			findId.addActionListener(this);
			IDfind.add(findId);

			JPanel toNext = new JPanel();
			JButton next = new JButton("비밀번호 찾기");
			next.setActionCommand("toNext");
			next.addActionListener(this);
			toNext.add(next);
			IDfind.add(toNext);
		}
		findPg.add(IDfind);

		JPanel PWfind = new JPanel(new GridLayout(5, 1, 0, 10));
		{
			PWfind.add(new JPanel());

			JPanel forfindPW = new JPanel(new GridLayout(1, 2, 5, 0));
			{
				JPanel PwId = new JPanel(new BorderLayout());
				JLabel PwIDtxt = new JLabel("아이디: ");
				PwId.add(PwIDtxt, BorderLayout.WEST);
				JTextField PwIDinput = new JTextField();
				PwIDinput.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent ke) {
						JTextField src = (JTextField) ke.getSource();
						if (src.getText().length() >= 15)
							ke.consume();
					}
				});
				PwId.add(PwIDinput);
				forfindPW.add(PwId);

				JPanel PwNm = new JPanel(new BorderLayout());
				JLabel PwNMtxt = new JLabel("이름:  ");
				PwNm.add(PwNMtxt, BorderLayout.WEST);
				JTextField PwNMinput = new JTextField();
				PwNMinput.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent ke) {
						JTextField src = (JTextField) ke.getSource();
						if (src.getText().length() >= 15)
							ke.consume();
					}
				});
				PwNm.add(PwNMinput);
				forfindPW.add(PwNm);
			}
			PWfind.add(forfindPW);

			JPanel changePW = new JPanel(new BorderLayout());
			{
				JLabel PWtxt = new JLabel("변경할 비밀번호:");
				changePW.add(PWtxt, BorderLayout.WEST);
				JTextField PWinput = new JPasswordField();
				PWinput.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent ke) {
						JTextField src = (JTextField) ke.getSource();
						if (src.getText().length() >= 15)
							ke.consume();
					}
				});
				PWinput.setEnabled(false);
				changePW.add(PWinput);
			}
			PWfind.add(changePW);

			JButton PW = new JButton("본인확인");
			PW.setActionCommand("check");
			PW.addActionListener(this);
			PWfind.add(PW);

			JPanel toPrevious = new JPanel();
			JButton previous = new JButton("아이디 찾기");
			previous.setActionCommand("toPrevious");
			previous.addActionListener(this);
			toPrevious.add(previous);
			PWfind.add(toPrevious);
		}
		findPg.add(PWfind);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = ((JButton) e.getSource()).getActionCommand();

		switch (actionCommand) {
		case "findId":
			SAccount sAccount = new SAccount();
			String ids[] = sAccount.FindIds(NMinput.getText());
			if (ids == null) {
				JOptionPane.showMessageDialog(null, "가입되지 않은 정보입니다.");
				break;
			}
			foundID.setText("찾은 " + ids.length + "개의 아이디: " + Arrays.toString(ids));
			break;
		case "check":
		case "changePW":
		case "toNext":
			card.next(findPg);
			break;
		case "toPrevious":
			card.previous(findPg);
			break;
		}

	}
}

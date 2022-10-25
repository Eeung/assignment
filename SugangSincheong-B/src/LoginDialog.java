import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginDialog extends JDialog implements ActionListener {

	private SLogin sLogin;
	private JPanel loginPg;
	private JLabel IDlabel, PWlabel;
	private JTextField IDinput, PWinput;
	private JButton confirm;
	protected String name = null;

	// 로그인 성공 및 실패
	public String login() {
		String id = IDinput.getText();

		String password = PWinput.getText();
		this.sLogin = new SLogin();

		String str = sLogin.login(id, password);

		switch (str) {
		case "non-Pass" -> JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 잘못 입력하셨습니다. 입력하신 내용을 다시 확인해주세요.");
		case "not-exist" -> JOptionPane.showMessageDialog(null, "존재하지 않는 아이디 입니다. 입력하신 내용을 다시 확인해주세요.");
		default -> {
			JOptionPane.showMessageDialog(null, "안녕하십니까, " + str + "님.");
			dispose();
			return str;
		}
		}

		IDinput.setText("");
		PWinput.setText("");
		return null;
	}

	public LoginDialog() {
		setTitle("로그인");
		setModal(true);
		setSize(640, 360);
		setResizable(false);

		// 로그인 페이지 설정
		loginPg = new JPanel();
		add(loginPg);
		loginPg.setLayout(null);
		// loginPg.setBackground(Color.CYAN);

		IDlabel = new JLabel("아이디: ");
		IDlabel.setBounds(140, 80, 100, 30);
		loginPg.add(IDlabel);
		PWlabel = new JLabel("비밀번호: ");
		PWlabel.setBounds(140, 130, 100, 30);
		loginPg.add(PWlabel);

		IDinput = new JTextField();
		IDinput.setBounds(200, 81, 300, 28);
		loginPg.add(IDinput);
		PWinput = new JPasswordField();
		PWinput.setBounds(200, 131, 300, 28);
		loginPg.add(PWinput);

		// 아이디, 비밀번호 입력문자 수 제한
		IDinput.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if (src.getText().length() >= 10)
					ke.consume();
			}
		});
		PWinput.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if (src.getText().length() >= 15)
					ke.consume();
			}
		});

		confirm = new JButton("로그인");
		confirm.setBounds(270, 215, 100, 50);
		loginPg.add(confirm);

		confirm.setActionCommand("tryLogin");
		confirm.addActionListener(this);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = ((JButton) e.getSource()).getActionCommand();

		switch (actionCommand) {
		case "tryLogin":
			String inform = login();
			if (inform != null) {
				name = inform;
			}
			break;
		}
	}
}

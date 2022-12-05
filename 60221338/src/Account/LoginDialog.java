package Account;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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

	private SAccount sAccount;
	private JTextField IDinput, PWinput;
	public String name[] = null;

	// 로그인 성공 및 실패
	public String[] login() {
		String id = IDinput.getText();
		String password = PWinput.getText();
		sAccount = new SAccount();

		String name = sAccount.login(id, password);

		// 로그인 결과
		switch (name) {
		case "non-Pass" -> JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 잘못 입력하셨습니다. 입력하신 내용을 다시 확인해주세요.");
		case "not-exist" -> JOptionPane.showMessageDialog(null, "존재하지 않는 아이디 입니다. 입력하신 내용을 다시 확인해주세요.");
		default -> {
			String inform[] = { name, id };
			JOptionPane.showMessageDialog(null, "안녕하십니까, " + name + "님.");
			dispose();
			return inform;
		}
		}

		// 로그인 실패 시, 초기화
		IDinput.setText("");
		PWinput.setText("");
		return null;
	}

	public LoginDialog() {
		setTitle("로그인");
		setModal(true);
		setSize(640, 400);
		setMinimumSize(new Dimension(640, 400));

		// 로그인 페이지 설정
		JPanel loginPg = new JPanel();
		add(loginPg);
		loginPg.setLayout(new GridLayout(5, 1, 0, 40));
		loginPg.add(new JPanel());

		JPanel ID = new JPanel();
		ID.setLayout(new BorderLayout());
		JLabel IDlabel = new JLabel("           아이디:           ");
		ID.add(IDlabel, BorderLayout.WEST);
		IDinput = new JTextField();
		ID.add(IDinput);
		ID.add(new JLabel("           "), BorderLayout.EAST);
		loginPg.add(ID);

		JPanel PW = new JPanel();
		PW.setLayout(new BorderLayout());
		JLabel PWlabel = new JLabel("           비밀번호:       ");
		PW.add(PWlabel, BorderLayout.WEST);
		PWinput = new JPasswordField();
		PW.add(PWinput);
		PW.add(new JLabel("           "), BorderLayout.EAST);
		loginPg.add(PW);

		// 아이디, 비밀번호 입력문자 수 제한
		IDinput.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if (src.getText().length() >= 15)
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

		JPanel buttons = new JPanel();
		buttons.setLayout(new BorderLayout(5, 0));
		loginPg.add(buttons);

		JButton confirm = new JButton("로그인");
		getRootPane().setDefaultButton(confirm);
		confirm.setActionCommand("tryLogin");
		confirm.addActionListener(this);
		buttons.add(confirm);

		JButton register = new JButton("회원가입");
		register.setActionCommand("signUp");
		register.addActionListener(this);
		buttons.add(register, BorderLayout.EAST);

		loginPg.add(new JPanel());

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = ((JButton) e.getSource()).getActionCommand();

		switch (actionCommand) {
		case "tryLogin":
			name = login();
			break;
		case "signUp":
			new SignupDialog();
			break;
		}
	}
}

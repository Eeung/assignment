package Account;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
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
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screen.width / 5, screen.height / 4 + screen.height / 27);
		setMinimumSize(new Dimension(screen.width / 5, screen.height / 4 + screen.height / 27));

		// 로그인 페이지 설정
		JPanel loginPg = new JPanel();
		add(loginPg);
		loginPg.setLayout(new BorderLayout(0, 20));
		loginPg.add(new JPanel(), BorderLayout.NORTH);

		JPanel inputArea = new JPanel(new BorderLayout(5, 15));

		JPanel label = new JPanel(new GridBagLayout());
		JLabel IDlabel = new JLabel("아이디:");
		addCon(IDlabel, 0, 0, label);
		JLabel PWlabel = new JLabel("비밀번호: ");
		addCon(PWlabel, 0, 1, label);
		inputArea.add(label, BorderLayout.WEST);

		JPanel textField = new JPanel(new GridBagLayout());
		IDinput = new JTextField();
		addCon(IDinput, 0, 0, textField);
		PWinput = new JPasswordField();
		addCon(PWinput, 0, 1, textField);
		inputArea.add(textField);

		loginPg.add(inputArea);

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

		JPanel buttons = new JPanel();
		buttons.setLayout(new BorderLayout());
		inputArea.add(buttons, BorderLayout.SOUTH);

		JButton confirm = new JButton("로그인");
		getRootPane().setDefaultButton(confirm);
		confirm.setActionCommand("tryLogin");
		confirm.addActionListener(this);
		buttons.add(confirm);

		JButton register = new JButton("회원가입");
		register.setActionCommand("signUp");
		register.addActionListener(this);
		buttons.add(register, BorderLayout.EAST);

		JButton findInfo = new JButton("찾기");
		findInfo.setActionCommand("findIt");
		findInfo.addActionListener(this);
		buttons.add(findInfo, BorderLayout.WEST);

		loginPg.add(new JPanel(), BorderLayout.SOUTH);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public void addCon(Component c, int x, int y, JPanel panel) {
		GridBagConstraints gridBagCon = new GridBagConstraints();

		gridBagCon.fill = GridBagConstraints.HORIZONTAL;
		gridBagCon.weightx = 1;
		gridBagCon.weighty = 1;
		gridBagCon.gridx = x;
		gridBagCon.gridy = y;

		panel.add(c, gridBagCon);
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
		case "findIt":
			new FindinfoDialog();
			break;
		}
	}
}

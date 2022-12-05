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

	// �α��� ���� �� ����
	public String[] login() {
		String id = IDinput.getText();
		String password = PWinput.getText();
		sAccount = new SAccount();

		String name = sAccount.login(id, password);

		// �α��� ���
		switch (name) {
		case "non-Pass" -> JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� �߸� �Է��ϼ̽��ϴ�. �Է��Ͻ� ������ �ٽ� Ȯ�����ּ���.");
		case "not-exist" -> JOptionPane.showMessageDialog(null, "�������� �ʴ� ���̵� �Դϴ�. �Է��Ͻ� ������ �ٽ� Ȯ�����ּ���.");
		default -> {
			String inform[] = { name, id };
			JOptionPane.showMessageDialog(null, "�ȳ��Ͻʴϱ�, " + name + "��.");
			dispose();
			return inform;
		}
		}

		// �α��� ���� ��, �ʱ�ȭ
		IDinput.setText("");
		PWinput.setText("");
		return null;
	}

	public LoginDialog() {
		setTitle("�α���");
		setModal(true);
		setSize(640, 400);
		setMinimumSize(new Dimension(640, 400));

		// �α��� ������ ����
		JPanel loginPg = new JPanel();
		add(loginPg);
		loginPg.setLayout(new GridLayout(5, 1, 0, 40));
		loginPg.add(new JPanel());

		JPanel ID = new JPanel();
		ID.setLayout(new BorderLayout());
		JLabel IDlabel = new JLabel("           ���̵�:           ");
		ID.add(IDlabel, BorderLayout.WEST);
		IDinput = new JTextField();
		ID.add(IDinput);
		ID.add(new JLabel("           "), BorderLayout.EAST);
		loginPg.add(ID);

		JPanel PW = new JPanel();
		PW.setLayout(new BorderLayout());
		JLabel PWlabel = new JLabel("           ��й�ȣ:       ");
		PW.add(PWlabel, BorderLayout.WEST);
		PWinput = new JPasswordField();
		PW.add(PWinput);
		PW.add(new JLabel("           "), BorderLayout.EAST);
		loginPg.add(PW);

		// ���̵�, ��й�ȣ �Է¹��� �� ����
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

		JButton confirm = new JButton("�α���");
		getRootPane().setDefaultButton(confirm);
		confirm.setActionCommand("tryLogin");
		confirm.addActionListener(this);
		buttons.add(confirm);

		JButton register = new JButton("ȸ������");
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

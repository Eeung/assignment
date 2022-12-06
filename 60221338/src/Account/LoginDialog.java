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
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screen.width / 5, screen.height / 4 + screen.height / 27);
		setMinimumSize(new Dimension(screen.width / 5, screen.height / 4 + screen.height / 27));

		// �α��� ������ ����
		JPanel loginPg = new JPanel();
		add(loginPg);
		loginPg.setLayout(new BorderLayout(0, 20));
		loginPg.add(new JPanel(), BorderLayout.NORTH);

		JPanel inputArea = new JPanel(new BorderLayout(5, 15));

		JPanel label = new JPanel(new GridBagLayout());
		JLabel IDlabel = new JLabel("���̵�:");
		addCon(IDlabel, 0, 0, label);
		JLabel PWlabel = new JLabel("��й�ȣ: ");
		addCon(PWlabel, 0, 1, label);
		inputArea.add(label, BorderLayout.WEST);

		JPanel textField = new JPanel(new GridBagLayout());
		IDinput = new JTextField();
		addCon(IDinput, 0, 0, textField);
		PWinput = new JPasswordField();
		addCon(PWinput, 0, 1, textField);
		inputArea.add(textField);

		loginPg.add(inputArea);

		// ���̵�, ��й�ȣ �Է¹��� �� ����
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

		JButton confirm = new JButton("�α���");
		getRootPane().setDefaultButton(confirm);
		confirm.setActionCommand("tryLogin");
		confirm.addActionListener(this);
		buttons.add(confirm);

		JButton register = new JButton("ȸ������");
		register.setActionCommand("signUp");
		register.addActionListener(this);
		buttons.add(register, BorderLayout.EAST);

		JButton findInfo = new JButton("ã��");
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

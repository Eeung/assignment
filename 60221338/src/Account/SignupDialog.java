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
public class SignupDialog extends JDialog implements ActionListener {
	private JTextField IDinput, PWinput, NMinput;
	private JTextField informations[] = { IDinput, PWinput, NMinput };

	// ȸ������
	private void signup() {
		String[] info = new String[informations.length];

		int i = 0;
		for (JTextField input : informations) {
			// ��ĭ Ȯ��
			if (input.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�Է����� ���� ������ �ֽ��ϴ�. �ٽ� �ѹ� Ȯ�� ��Ź�帳�ϴ�.");
				return;
			}

			info[i] = input.getText();
			i++;
		}

		// �ߺ� Ȯ��
		SAccount sAccount = new SAccount();
		if (sAccount.idDuplicationTest(IDinput.getText())) {
			// �ߺ� ����
			sAccount.signup(info);
			JOptionPane.showMessageDialog(null, "ȸ������ �Ǿ����ϴ�.");
			dispose();
		} else {
			// �ߺ�
			JOptionPane.showMessageDialog(null, "�̹� �ִ� ���̵��Դϴ�.");
			IDinput.setText("");
		}
	}

	public SignupDialog() {
		setTitle("ȸ������");
		setModal(true);
		setSize(640, 480);
		setMinimumSize(new Dimension(640, 400));

		// ȸ������ ������ ����
		JPanel signupPg = new JPanel();
		add(signupPg);
		signupPg.setLayout(new GridLayout(6, 1, 0, 40));
		signupPg.add(new JPanel());

		JPanel ID = new JPanel();
		ID.setLayout(new BorderLayout());
		JLabel IDlabel = new JLabel("           ���̵�:           ");
		ID.add(IDlabel, BorderLayout.WEST);
		informations[0] = IDinput = new JTextField();
		ID.add(IDinput);
		ID.add(new JLabel("           "), BorderLayout.EAST);
		signupPg.add(ID);

		JPanel PW = new JPanel();
		PW.setLayout(new BorderLayout());
		JLabel PWlabel = new JLabel("           ��й�ȣ:       ");
		PW.add(PWlabel, BorderLayout.WEST);
		informations[1] = PWinput = new JPasswordField();
		PW.add(PWinput);
		PW.add(new JLabel("           "), BorderLayout.EAST);
		signupPg.add(PW);

		JPanel NM = new JPanel();
		NM.setLayout(new BorderLayout());
		JLabel NMlabel = new JLabel("           �̸�:               ");
		NM.add(NMlabel, BorderLayout.WEST);
		informations[2] = NMinput = new JTextField();
		NM.add(NMinput);
		NM.add(new JLabel("           "), BorderLayout.EAST);
		signupPg.add(NM);

		// ���̵�, ��й�ȣ �Է¹��� �� ����
		for (int i = 0; i < informations.length; i++) {
			informations[i].addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent ke) {
					JTextField src = (JTextField) ke.getSource();
					if (src.getText().length() >= 15)
						ke.consume();
				}
			});
		}

		JButton register = new JButton("ȸ������");
		register.setActionCommand("signUp");
		register.addActionListener(this);
		signupPg.add(register);

		signupPg.add(new JPanel());

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = ((JButton) e.getSource()).getActionCommand();

		switch (actionCommand) {
		case "signUp":
			signup();
			break;
		}
	}

}

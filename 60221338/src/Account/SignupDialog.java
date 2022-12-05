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
	private JTextField informations[] = { NMinput };

	// ȸ������
	private void signup() {
		String[] info = new String[informations.length + 2];

		String ID = IDinput.getText();
		String PW = PWinput.getText();
		if (ID.equals("") || PW.equals("")) {
			JOptionPane.showMessageDialog(null, "�Է����� ���� ������ �ֽ��ϴ�. �ٽ� �ѹ� Ȯ�� ��Ź�帳�ϴ�.");
			return;
		} else if (ID.length() < 4) {
			JOptionPane.showMessageDialog(null, "�Է��Ͻ� ���̵� �ʹ� ª���ϴ�.");
			return;
		} else if (PW.length() < 4) {
			JOptionPane.showMessageDialog(null, "�Է��Ͻ� ��й�ȣ�� �ʹ� ª���ϴ�.");
			return;
		}
		info[0] = ID;
		info[1] = PW;

		int i = 2;
		for (JTextField input : informations) {
			String entered = input.getText();
			// ��ĭ Ȯ��
			if (entered.equals("")) {
				JOptionPane.showMessageDialog(null, "�Է����� ���� ������ �ֽ��ϴ�. �ٽ� �ѹ� Ȯ�� ��Ź�帳�ϴ�.");
				return;
			}

			info[i] = entered;
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
		IDinput = new JTextField();
		ID.add(IDinput);
		ID.add(new JLabel("           "), BorderLayout.EAST);
		signupPg.add(ID);

		JPanel PW = new JPanel();
		PW.setLayout(new BorderLayout());
		JLabel PWlabel = new JLabel("           ��й�ȣ:       ");
		PW.add(PWlabel, BorderLayout.WEST);
		PWinput = new JPasswordField();
		PW.add(PWinput);
		PW.add(new JLabel("           "), BorderLayout.EAST);
		signupPg.add(PW);

		JPanel NM = new JPanel();
		NM.setLayout(new BorderLayout());
		JLabel NMlabel = new JLabel("           �̸�:               ");
		NM.add(NMlabel, BorderLayout.WEST);
		informations[0] = NMinput = new JTextField();
		NM.add(NMinput);
		NM.add(new JLabel("           "), BorderLayout.EAST);
		signupPg.add(NM);

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
		for (int i = 0; i < informations.length; i++) { // ��Ÿ ���� ����
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

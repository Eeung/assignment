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

	// 회원가입
	private void signup() {
		String[] info = new String[informations.length];

		int i = 0;
		for (JTextField input : informations) {
			// 빈칸 확인
			if (input.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "입력하지 않은 정보가 있습니다. 다시 한번 확인 부탁드립니다.");
				return;
			}

			info[i] = input.getText();
			i++;
		}

		// 중복 확인
		SAccount sAccount = new SAccount();
		if (sAccount.idDuplicationTest(IDinput.getText())) {
			// 중복 없음
			sAccount.signup(info);
			JOptionPane.showMessageDialog(null, "회원가입 되었습니다.");
			dispose();
		} else {
			// 중복
			JOptionPane.showMessageDialog(null, "이미 있는 아이디입니다.");
			IDinput.setText("");
		}
	}

	public SignupDialog() {
		setTitle("회원가입");
		setModal(true);
		setSize(640, 480);
		setMinimumSize(new Dimension(640, 400));

		// 회원가입 페이지 설정
		JPanel signupPg = new JPanel();
		add(signupPg);
		signupPg.setLayout(new GridLayout(6, 1, 0, 40));
		signupPg.add(new JPanel());

		JPanel ID = new JPanel();
		ID.setLayout(new BorderLayout());
		JLabel IDlabel = new JLabel("           아이디:           ");
		ID.add(IDlabel, BorderLayout.WEST);
		informations[0] = IDinput = new JTextField();
		ID.add(IDinput);
		ID.add(new JLabel("           "), BorderLayout.EAST);
		signupPg.add(ID);

		JPanel PW = new JPanel();
		PW.setLayout(new BorderLayout());
		JLabel PWlabel = new JLabel("           비밀번호:       ");
		PW.add(PWlabel, BorderLayout.WEST);
		informations[1] = PWinput = new JPasswordField();
		PW.add(PWinput);
		PW.add(new JLabel("           "), BorderLayout.EAST);
		signupPg.add(PW);

		JPanel NM = new JPanel();
		NM.setLayout(new BorderLayout());
		JLabel NMlabel = new JLabel("           이름:               ");
		NM.add(NMlabel, BorderLayout.WEST);
		informations[2] = NMinput = new JTextField();
		NM.add(NMinput);
		NM.add(new JLabel("           "), BorderLayout.EAST);
		signupPg.add(NM);

		// 아이디, 비밀번호 입력문자 수 제한
		for (int i = 0; i < informations.length; i++) {
			informations[i].addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent ke) {
					JTextField src = (JTextField) ke.getSource();
					if (src.getText().length() >= 15)
						ke.consume();
				}
			});
		}

		JButton register = new JButton("회원가입");
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

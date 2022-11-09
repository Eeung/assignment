import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Account.LoginDialog;
import directory.DirectoryPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {
	private LoginDialog logD;
	private JPanel mainPg, preface;
	private DirectoryPanel sugangPg;
	private JLabel welcome;
	private JButton LoginB;
	private Dimension window = new Dimension();

	public MainFrame() {
		// ���� ������ ����
		setTitle("�׽�Ʈ");
		setSize(1280, 720);
		window = getSize();
		setMinimumSize(window);

		// �г� ����
		mainPg = new JPanel();
		add(mainPg);
		mainPg.setVisible(true);
		mainPg.setLayout(new BorderLayout());

		preface = new JPanel();
		mainPg.add(preface, "North");
		preface.setBackground(Color.WHITE);
		preface.setLocation(0, 0);
		preface.setLayout(new FlowLayout(FlowLayout.RIGHT));

		welcome = new JLabel("�α����� �ʿ��մϴ�.");
		preface.add(welcome);

		// �α��� ��ư ����
		LoginB = new JButton("�α���");
		preface.add(LoginB);
		LoginB.setActionCommand("intoLoginPage");
		LoginB.addActionListener(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	// �α��� �������� ���� �����ӿ� ǥ��
	public void showLoginPage() {
		logD = new LoginDialog();
		// setEnabled(false);

		if (logD.name != null) {
			welcome.setText(logD.name + "��, ȯ���մϴ�.");
			LoginB.setText("�α׾ƿ�");
			LoginB.setActionCommand("doLogout");
			showClassPage();
		}
	}

	public void showClassPage() {
		sugangPg = new DirectoryPanel();
		mainPg.add(sugangPg, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = ((JButton) e.getSource()).getActionCommand();

		switch (actionCommand) {
		case "intoLoginPage":
			showLoginPage();
			break;

		case "doLogout":
			welcome.setText("�α����� �ʿ��մϴ�.");
			LoginB.setText("�α���");
			LoginB.setActionCommand("intoLoginPage");
			sugangPg.setVisible(false);
			mainPg.remove(sugangPg);
			break;
		}
	}

}

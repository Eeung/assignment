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
		// 메인 프레임 설정
		setTitle("테스트");
		setSize(1280, 720);
		window = getSize();
		setMinimumSize(window);

		// 패널 설정
		mainPg = new JPanel();
		add(mainPg);
		mainPg.setVisible(true);
		mainPg.setLayout(new BorderLayout());

		preface = new JPanel();
		mainPg.add(preface, "North");
		preface.setBackground(Color.WHITE);
		preface.setLocation(0, 0);
		preface.setLayout(new FlowLayout(FlowLayout.RIGHT));

		welcome = new JLabel("로그인이 필요합니다.");
		preface.add(welcome);

		// 로그인 버튼 생성
		LoginB = new JButton("로그인");
		preface.add(LoginB);
		LoginB.setActionCommand("intoLoginPage");
		LoginB.addActionListener(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	// 로그인 페이지를 메인 프레임에 표시
	public void showLoginPage() {
		logD = new LoginDialog();
		// setEnabled(false);

		if (logD.name != null) {
			welcome.setText(logD.name + "님, 환영합니다.");
			LoginB.setText("로그아웃");
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
			welcome.setText("로그인이 필요합니다.");
			LoginB.setText("로그인");
			LoginB.setActionCommand("intoLoginPage");
			sugangPg.setVisible(false);
			mainPg.remove(sugangPg);
			break;
		}
	}

}

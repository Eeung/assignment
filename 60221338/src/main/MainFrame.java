package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import directory.SugangPanel;
import language.Text;
import main.Main.ActionHandler;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private JPanel mainPg, preface;
	private SugangPanel sugangPg;
	private JLabel welcome;
	private JButton LoginB;
	private Dimension window = new Dimension();
	private String language[] = { "한국어", "English" };

	public MainFrame(ActionHandler action) {
		// 메인 프레임 설정
		setTitle(Text.getTitle());
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
		preface.setLayout(new BorderLayout());

		JPanel nc = new JPanel();
		nc.setBackground(Color.WHITE);
		nc.setLayout(new FlowLayout(FlowLayout.RIGHT));
		preface.add(nc);

		JComboBox<String> langCombo = new JComboBox<String>(language);
		langCombo.addActionListener(action);
		langCombo.setActionCommand("changeLanguage");
		preface.add(langCombo, BorderLayout.WEST);

		welcome = new JLabel(Text.getNeedLogin());
		nc.add(welcome);

		// 로그인 버튼 생성
		LoginB = new JButton(Text.getLoginButton());
		nc.add(LoginB);
		LoginB.setActionCommand("intoLoginPage");
		LoginB.addActionListener(action);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void showLoginPage(String name) {
		Text.setName(name);
		welcome.setText(Text.getWelcome());
		LoginB.setText(Text.getLogoutButton());
		LoginB.setActionCommand("doLogout");
		showClassPage();
	}

	public void logout() {
		welcome.setText(Text.getNeedLogin());
		LoginB.setText(Text.getLoginButton());
		LoginB.setActionCommand("intoLoginPage");
		sugangPg.setVisible(false);
		mainPg.remove(sugangPg);
	}

	public void showClassPage() {
		sugangPg = new SugangPanel();
		mainPg.add(sugangPg, BorderLayout.CENTER);
	}

	public void sugangInit(String id) {
		sugangPg.init(id);
	}

	public void changeLanguage() {
		setTitle(Text.getTitle());
		if (LoginB.getActionCommand().toString().equals("intoLoginPage")) {
			welcome.setText(Text.getNeedLogin());
			LoginB.setText(Text.getLoginButton());
		} else {
			welcome.setText(Text.getWelcome());
			LoginB.setText(Text.getLogoutButton());
		}

	}
}

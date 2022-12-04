package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import directory.SugangPanel;
import main.Main.ActionHandler;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private JPanel mainPg, preface;
	private SugangPanel sugangPg;
	private JLabel welcome;
	private JButton LoginB;
	private Dimension window = new Dimension();

	public MainFrame(ActionHandler action) {
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
		LoginB.addActionListener(action);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void showLoginPage(String name) {
		welcome.setText(name + "��, ȯ���մϴ�.");
		LoginB.setText("�α׾ƿ�");
		LoginB.setActionCommand("doLogout");
		showClassPage();
	}

	public void logout() {
		welcome.setText("�α����� �ʿ��մϴ�.");
		LoginB.setText("�α���");
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
}

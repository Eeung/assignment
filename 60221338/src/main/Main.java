package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Account.LoginDialog;

public class Main {
	private LoginDialog logD;
	private ActionHandler action;
	private MainFrame mainframe;

	public Main() {
	}

	public void initialize() {
		action = new ActionHandler();
		mainframe = new MainFrame(action);
	}

	public void run() {
		if (logD.name != null) {
			mainframe.showLoginPage(logD.name[0]);
			mainframe.sugangInit(logD.name[1]);
		}
	}

	public void finish() {
	}

	public class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String actionCommand = ((JButton) e.getSource()).getActionCommand();

			switch (actionCommand) {
			case "intoLoginPage":
				logD = new LoginDialog();
				run();
				break;
			case "doLogout":
				mainframe.logout();
				break;
			}
		}
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.initialize();
		main.finish();
	}
}
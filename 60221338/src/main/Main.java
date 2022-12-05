package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import Account.LoginDialog;
import language.Text;

public class Main {
	private LoginDialog logD;
	private ActionHandler action;
	private MainFrame mainframe;

	public Main() {
	}

	public void initialize() {
		new Text("ÇÑ±¹¾î");
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
		@SuppressWarnings("rawtypes")
		@Override
		public void actionPerformed(ActionEvent e) {
			String actionCommand = "";
			try {
				actionCommand = ((JButton) e.getSource()).getActionCommand();
			} catch (ClassCastException ex) {
				actionCommand = ((JComboBox) e.getSource()).getActionCommand();
			}

			switch (actionCommand) {
			case "intoLoginPage":
				logD = new LoginDialog();
				run();
				break;
			case "doLogout":
				mainframe.logout();
				break;
			case "changeLanguage":
				String language = ((JComboBox) e.getSource()).getSelectedItem().toString();
				Text.readFile(language);
				mainframe.changeLanguage();
			}
		}
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.initialize();
		main.finish();
	}
}
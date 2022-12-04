package Account;

import javax.swing.JTextField;

public class SRegister {
	private EAccount eAccount;

	public SRegister() {
		eAccount = new EAccount();
	}

	public void signup(JTextField informations[]) {
		int size = informations.length;
		String[] info = new String[size];
		for (int i = 0; i < size; i++) {
			info[i] = informations[i].getText();
		}
		eAccount.writeAccount(info);
	}

	public boolean idDuplicationTest(String id) {
		VLogin vLogin = eAccount.getLoginInfo();

		while (vLogin != null) {

			if (vLogin.getid().equals(id)) {
				return false;
			}

			vLogin = eAccount.getLoginInfo();
		}
		return true;
	}
}

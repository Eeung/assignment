package Account;

import javax.swing.JTextField;

public class SRegister {
	EAccount eAccount;

	public SRegister() {
		eAccount = new EAccount();
	}

	public void signup(JTextField informations[]) {
		String info = "";
		for (int i = 0; i < informations.length; i++) {
			info += informations[i].getText() + " ";
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

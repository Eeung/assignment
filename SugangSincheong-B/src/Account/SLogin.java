package Account;

public class SLogin {

	public String login(String id, String password) {
		EAccount eAccount = new EAccount();
		VLogin vLogin = eAccount.getLoginInfo();

		while (vLogin != null) {
			// ���� ������ ������

			if (vLogin.getid().equals(id)) {
				if (vLogin.getPassword().equals(password)) {
					// ���� ���� ������ �ִٸ�
					return vLogin.getName();
				} else {
					// ���� ��й�ȣ�� �ٸ��ٸ�
					return "non-Pass";
				}
			}

			vLogin = eAccount.getLoginInfo();

		}
		// ���� ���� ������ �ϳ��� ���ٸ�
		return "not-exist";
	}

}

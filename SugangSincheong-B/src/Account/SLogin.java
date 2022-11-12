package Account;

public class SLogin {

	public String login(String id, String password) {
		EAccount eAccount = new EAccount();
		VLogin vLogin = eAccount.getLoginInfo();

		while (vLogin != null) {
			// 전부 돌려볼 때까지

			if (vLogin.getid().equals(id)) {
				if (vLogin.getPassword().equals(password)) {
					// 만약 같은 정보가 있다면
					return vLogin.getName();
				} else {
					// 만약 비밀번호가 다르다면
					return "non-Pass";
				}
			}

			vLogin = eAccount.getLoginInfo();

		}
		// 만약 같은 정보가 하나도 없다면
		return "not-exist";
	}

}

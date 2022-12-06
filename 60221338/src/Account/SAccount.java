package Account;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class SAccount {
	EAccount eAccount;

	public SAccount() {
		eAccount = new EAccount();
	}

	public String login(String id, String password) {
		VAccount vAccount = eAccount.getLoginInfo();
		password = encrypt(password);

		// 전부 돌려볼 때까지
		while (vAccount != null) {

			if (vAccount.getid().equals(id)) {
				if (vAccount.getPassword().equals(password)) {
					// 만약 같은 정보가 있다면

					return vAccount.getName();
				} else {
					// 만약 비밀번호가 다르다면
					return "non-Pass";
				}
			}
			// 반복
			vAccount = eAccount.getLoginInfo();
		}
		// 만약 같은 정보가 하나도 없다면
		return "not-exist";
	}

	// 정보를 받고, 새로운 계정을 쓰기
	public void signup(String info[]) {
		info[1] = encrypt(info[1]);
		eAccount.writeAccount(info);
	}

	// 비밀번호 암호화
	public String encrypt(String password) {
		StringBuilder builder = new StringBuilder();
		try {
			MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
			msgDigest.update(password.getBytes());

			byte[] msgByte = msgDigest.digest();

			for (byte b : msgByte)
				builder.append(String.format("%02x", b));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	// 아이디 중복 확인
	public boolean idDuplicationTest(String id) {
		VAccount vAccount = eAccount.getLoginInfo();

		while (vAccount != null) {

			if (vAccount.getid().equals(id)) {
				return false;
			}

			vAccount = eAccount.getLoginInfo();
		}
		return true;
	}

	public String[] FindIds(String name) {
		VAccount vAccount = eAccount.getLoginInfo();
		ArrayList<String> names = new ArrayList<String>();

		while (vAccount != null) {
			if (name.equals(vAccount.getName()))
				names.add(vAccount.getid());
			vAccount = eAccount.getLoginInfo();
		}

		if (names.size() == 0)
			return null;
		String[] result = new String[names.size()];
		int i = 0;
		for (String NM : names) {
			result[i++] = NM;
		}
		return result;
	}
}

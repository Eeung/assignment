package Account;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SAccount {
	EAccount eAccount;

	public SAccount() {
		eAccount = new EAccount();
	}

	public String login(String id, String password) {
		VAccount vAccount = eAccount.getLoginInfo();
		password = encrypt(password);

		// ���� ������ ������
		while (vAccount != null) {

			if (vAccount.getid().equals(id)) {
				if (vAccount.getPassword().equals(password)) {
					// ���� ���� ������ �ִٸ�

					return vAccount.getName();
				} else {
					// ���� ��й�ȣ�� �ٸ��ٸ�
					return "non-Pass";
				}
			}
			// �ݺ�
			vAccount = eAccount.getLoginInfo();
		}
		// ���� ���� ������ �ϳ��� ���ٸ�
		return "not-exist";
	}

	// ������ �ް�, ���ο� ������ ����
	public void signup(String info[]) {
		info[1] = encrypt(info[1]);
		eAccount.writeAccount(info);
	}

	// ��й�ȣ ��ȣȭ
	public String encrypt(String password) {
		StringBuilder builder = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());

			byte[] msgStr = md.digest();

			for (byte b : msgStr)
				builder.append(String.format("%02x", b));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	// ���̵� �ߺ� Ȯ��
	public boolean idDuplicationTest(String id) {
		VAccount vLogin = eAccount.getLoginInfo();

		while (vLogin != null) {

			if (vLogin.getid().equals(id)) {
				return false;
			}

			vLogin = eAccount.getLoginInfo();
		}
		return true;
	}
}

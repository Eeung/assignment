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

	// ���̵� �ߺ� Ȯ��
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
}

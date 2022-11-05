package login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class EAccount {
	BufferedReader bufReader;

	public EAccount() {
		try {
			File file = new File("data/account.txt");
			bufReader = new BufferedReader(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public VLogin getLoginInfo() {
		try {
			StringTokenizer st = new StringTokenizer(bufReader.readLine(), " ");
			VLogin vLogin = new VLogin(st.nextToken(), st.nextToken(), st.nextToken());
			// file read

			return vLogin;
		} catch (NullPointerException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

}

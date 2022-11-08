package Account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class EAccount {
	private File file;
	private BufferedReader bufReader;

	public EAccount() {
		try {
			file = new File("account/account.txt");
			bufReader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public VLogin getLoginInfo() {
		try {
			StringTokenizer st = new StringTokenizer(bufReader.readLine(), " ");
			VLogin vLogin = new VLogin(st.nextToken(), st.nextToken(), st.nextToken());
			// file read

			return vLogin;
		} catch (Exception e) {
			return null;
		}
	}

	public void writeAccount(String accountInfo) {
		try {
			BufferedWriter bufWriter = new BufferedWriter(new FileWriter(file, true));

			bufWriter.newLine();
			bufWriter.write(accountInfo);
			bufWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

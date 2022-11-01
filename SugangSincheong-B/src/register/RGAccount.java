package register;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RGAccount {
	String Receivedinfo;

	public RGAccount(String info) {
		try {
			Receivedinfo = info;
			writeAccount();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeAccount() throws IOException {
		File file = new File("data/account.txt");

		BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
		bw.newLine();
		bw.write(Receivedinfo);
		bw.close();
	}

}

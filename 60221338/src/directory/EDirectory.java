package directory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;
import java.util.Vector;

public class EDirectory {

	@SuppressWarnings("resource")
	public Vector<VDirectory> getDirectories(String fileName) {
		Vector<VDirectory> vDirectories = new Vector<VDirectory>();
		try {
			File file = new File("data/" + fileName);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String Line = br.readLine();
			while (Line != null) {
				VDirectory vDirectory = null;
				StringTokenizer st = new StringTokenizer(Line);

				switch (st.countTokens()) {
				case 3:
					vDirectory = new VDirectory(st.nextToken(), st.nextToken(), st.nextToken());
					break;
				case 5:
					vDirectory = new VDirectory(Line.split(" "));
					break;
				}

				vDirectories.add(vDirectory);
				Line = br.readLine();
			}

		} catch (FileNotFoundException e) {
			return getUserData(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vDirectories;
	}

	@SuppressWarnings("resource")
	public Vector<VDirectory> getUserData(String path) {
		Vector<VDirectory> vDirectories = new Vector<VDirectory>();
		try {
			File file = new File("account/" + path + ".txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String Line = br.readLine();
			while (Line != null) {
				VDirectory vDirectory = new VDirectory(Line.split(" "));

				vDirectories.add(vDirectory);
				Line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vDirectories;
	}

	public void writeLecture(String inform, String path) {
		try {
			File file = new File("account/" + path + ".txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

			bw.write(inform);
			bw.newLine();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public void cancelLectures(String codes[], String path) {
		try {
			File file = new File("account/" + path + ".txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String Line = br.readLine();
			String buffer = "";
			int i = 0, size = codes.length;
			while (Line != null) {
				StringTokenizer st = new StringTokenizer(Line);
				if (size > i && st.nextToken().equals(codes[i])) {
					i++;
					Line = br.readLine();
					continue;
				}
				buffer += Line + "\n";
				Line = br.readLine();
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(buffer);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

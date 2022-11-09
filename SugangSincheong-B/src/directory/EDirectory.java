package directory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
				StringTokenizer st = new StringTokenizer(Line);
				VDirectory vDirectory = new VDirectory(st.nextToken(), st.nextToken(), st.nextToken());
				vDirectories.add(vDirectory);
				Line = br.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vDirectories;
	}

}

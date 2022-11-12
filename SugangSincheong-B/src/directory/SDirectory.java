package directory;

import java.util.Vector;

public class SDirectory {
	private EDirectory eDirectory;

	public SDirectory() {
		eDirectory = new EDirectory();
	}

	public Vector<VDirectory> getDirectories(String fileName) {
		return eDirectory.getDirectories(fileName);
	}
}

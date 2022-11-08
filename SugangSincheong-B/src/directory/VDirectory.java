package directory;

public class VDirectory {
	private String id, name, fileName;

	public VDirectory(String ID, String Name, String FileName) {
		id = ID;
		name = Name;
		fileName = FileName;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getFileName() {
		return fileName;
	}

}

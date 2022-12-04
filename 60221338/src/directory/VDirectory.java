package directory;

public class VDirectory {
	private String code, name, fileName;
	private String[] information = null;

	public VDirectory(String Code, String Name, String FileName) {
		code = Code;
		name = Name;
		fileName = FileName;
		information = null;
	}

	public VDirectory(String[] Information) {
		information = new String[Information.length];
		information = Information;
		fileName = null;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getFileName() {
		return fileName;
	}

	public String[] getInformation() {
		return information;
	}
}

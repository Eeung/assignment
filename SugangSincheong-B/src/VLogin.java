public class VLogin {
	private String id;
	private String password;
	private String name;

	public VLogin(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}

	public String getid() {
		return this.id;
	}

	public String getPassword() {
		return this.password;
	}

	public String getName() {
		return this.name;
	}

}

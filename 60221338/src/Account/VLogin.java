package Account;

public class VLogin {
	private String id;
	private String password;
	private String name;

	public VLogin(String userInfo[]) {
		this.id = userInfo[0];
		this.password = userInfo[1];
		this.name = userInfo[2];
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

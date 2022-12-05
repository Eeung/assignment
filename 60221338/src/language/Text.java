package language;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Text {
	private static String title, needLogin, loginButton, welcome, logoutButton;
	private static String text[] = { title, needLogin, loginButton, welcome, logoutButton };
	private static String recentLang, name;

	public Text(String language) {
		readFile(language);
	}

	@SuppressWarnings("resource")
	public static void readFile(String language) {
		try {
			recentLang = language;
			File file = new File("language/" + language + ".txt");
			BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));

			String Line = bf.readLine();
			int i = 0;
			while (Line != null) {
				text[i] = Line;
				i++;
				Line = bf.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setName(String Name) {
		name = Name;
	}

	public static String getTitle() {
		return text[0];
	}

	public static String getNeedLogin() {
		return text[1];
	}

	public static String getLoginButton() {
		return text[2];
	}

	public static String getWelcome() {
		switch (recentLang) {
		case "ÇÑ±¹¾î":
			return name + text[3];
		case "English":
			return text[3] + name + ".";
		}
		return null;
	}

	public static String getLogoutButton() {
		return text[4];
	}

}

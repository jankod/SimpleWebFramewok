package hr.ja.fr.commands;

public class JQuery {

	private static JQuery instance = new JQuery();

	private JQuery() {
	}

	public static JQuery $() {
		return instance;
	}

}

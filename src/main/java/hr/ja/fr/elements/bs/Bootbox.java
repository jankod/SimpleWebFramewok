package hr.ja.fr.elements.bs;

import hr.ja.fr.commands.DOMCommand;
import hr.ja.fr.elements.ElementCommands;

public class Bootbox {

	public static void alert(String msg) {
		ElementCommands.add(new DOMCommand("bootbox_alert", msg));
	}

	public static void alert(String title, String msg) {
		ElementCommands.add(new DOMCommand("bootbox_alert", title, msg));
	}

}

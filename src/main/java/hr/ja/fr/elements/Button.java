package hr.ja.fr.elements;

import hr.ja.fr.events.ClickNotifier;

public class Button extends EL implements ClickNotifier<Button> {

	public Button(String value) {
		super("button");
		appendText(value);
	}

}

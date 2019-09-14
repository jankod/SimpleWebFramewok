package hr.ja.fr.events;

import hr.ja.fr.elements.EL;

public abstract class ClickEvent<T extends EL> {

	public abstract T getSource();
	
}

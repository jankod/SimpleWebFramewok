package hr.ja.fr.events;

import hr.ja.fr.elements.EL;

public interface ButtonClickListener<T extends EL> extends EventListener {

	public void onClick(ClickEvent<T> event);

}

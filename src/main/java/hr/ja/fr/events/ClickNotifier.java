package hr.ja.fr.events;


import hr.ja.fr.elements.EL;
import hr.ja.fr.events.ElementEvent.EventType;

public interface ClickNotifier<T extends EL> {

	public default void addClickListener(ButtonClickListener<T> listener) {
		EL element = (EL) this;
		EventUtil.addListener(element, (EventListener) listener, EventType.CLICK);
	}

}

package hr.ja.fr.events;

import org.apache.commons.lang.RandomStringUtils;

import hr.ja.fr.elements.EL;
import lombok.Data;

@Data
public class ElementEvent {

	private EL element;
	private EventListener listener;
	private EventType eventType;

	private String listenerId;

	public ElementEvent(EL element, EventListener listener, EventType eventType) {
		this.element = element;
		this.listener = listener;
		this.eventType = eventType;
		listenerId = RandomStringUtils.randomAlphanumeric(7);
	}

	public String getListenerId() {
		return listenerId;
	}
	
	public static enum EventType {
		CLICK
	}

}
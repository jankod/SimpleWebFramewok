package hr.ja.fr.events;

import java.util.ArrayList;
import java.util.List;

import hr.ja.fr.elements.EL;
import hr.ja.fr.events.ElementEvent.EventType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EventCommands {

	public static void addListener(EL element, EventListener listener, EventType eventType) {
		THREAD_LOCAL.get().add(new ElementEvent(element, listener, eventType));
	}
	
	

	private static final ThreadLocal<List<ElementEvent>> THREAD_LOCAL = new ThreadLocal<>();

	public static void clear() {
		ArrayList<ElementEvent> list = new ArrayList<>();
		THREAD_LOCAL.set(list);
	}

	public static List<ElementEvent> getEvents() {
		return THREAD_LOCAL.get();
	}

}

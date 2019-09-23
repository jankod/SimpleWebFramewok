package hr.ja.fr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import hr.ja.fr.commands.CommandJs;
import hr.ja.fr.elements.Button;
import hr.ja.fr.events.ButtonClickEvent;
import hr.ja.fr.events.ButtonClickListener;
import hr.ja.fr.events.ElementEvent;
import hr.ja.fr.events.ElementEvent.EventType;
import hr.ja.fr.manager.PageEventManager;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
public class ServerPage implements Serializable {

	@Getter
	@Setter
	private Page page;

	private List<ElementEvent> listeners;

	@Getter
	@Setter
	private ServerSession userSession;

	public ServerPage(Page page, List<ElementEvent> listeners) {
		this.page = page;
		this.listeners = listeners;
	}

	public void addEventListeners(List<ElementEvent> newListeners) {
		if (listeners == null) {
			listeners = new ArrayList<ElementEvent>();
		}
		listeners.addAll(newListeners);
//		log.debug("Dodajem nove listenere " + newListeners.size());
	}

	public String createCommandsJs() {
		ObjectMapper mapper = new ObjectMapper();

		List<CommandJs> commands = new ArrayList<CommandJs>();
//		log.debug("imam commands {}", tagListeners.size());
		for (ElementEvent elEvent : listeners) {
			CommandJs command = PageEventManager.createCommand(elEvent);
			commands.add(command);
		}
		return WebUtil.toJson(commands);
	}

	public void callEventListeners(String elementId, String listenerId, String body) {
		if (listeners == null) {
			log.error("null je");
			return;
		}
		for (ElementEvent t : listeners) {
			if (t.getListenerId().equals(listenerId)) {
				if (t.getEventType() == EventType.CLICK) {
					ButtonClickListener listener = (ButtonClickListener) t.getListener();
					listener.onClick(new ButtonClickEvent((Button) t.getElement()));
					break;
				}
				throw new RuntimeException("What listener?? " + t);

			}
		}

	}

}

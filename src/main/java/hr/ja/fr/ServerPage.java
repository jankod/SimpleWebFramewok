package hr.ja.fr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hr.ja.fr.commands.CommandJs;
import hr.ja.fr.elements.Button;
import hr.ja.fr.events.ButtonClickEvent;
import hr.ja.fr.events.ButtonClickListener;
import hr.ja.fr.events.ElementEvent;
import hr.ja.fr.events.ElementEvent.EventType;
import hr.ja.fr.events.EventListener;
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

	private List<ElementEvent> tagListeners;

	@Getter
	@Setter
	private ServerSession userSession;

	public ServerPage(Page page, List<ElementEvent> tagListeners) {
		this.page = page;
		this.tagListeners = tagListeners;
	}

	public String createCommandsJs() {
		ObjectMapper mapper = new ObjectMapper();

		List<CommandJs> commands = new ArrayList<CommandJs>();
//		log.debug("imam commands {}", tagListeners.size());
		for (ElementEvent elEvent : tagListeners) {
			CommandJs command = PageEventManager.createCommand(elEvent);
			commands.add(command);
		}
		return WebUtil.toJson(commands);
	}

	public void callEvenetListener(String elementId, String listenerId, String body) {
		if (tagListeners == null) {
			log.error("null je");
			return;
		}
		for (ElementEvent t : tagListeners) {
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

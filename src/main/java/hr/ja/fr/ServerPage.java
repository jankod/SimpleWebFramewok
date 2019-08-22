package hr.ja.fr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hr.ja.fr.CommandJs.EventType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Data
class CommandJs {
	String elementId;
	EventType eventType;
	String listenerId;
	String html;
	
	public static enum EventType{
		CLICK,
		TAG_ADD
	}
}

@NoArgsConstructor
@Slf4j
public class ServerPage implements Serializable {

	@Getter
	@Setter
	private Page page;

	private List<TagListenerPair> tagListeners;

	@Getter
	@Setter
	private ServerSession userSession;

	public ServerPage(Page page, List<TagListenerPair> tagListeners) {
		this.page = page;
		this.tagListeners = tagListeners;
	}

	public String createCommandsJs() {
		ObjectMapper mapper = new ObjectMapper();

		List<CommandJs> commands = new ArrayList<CommandJs>();
//		log.debug("imam commands {}", tagListeners.size());
		for (TagListenerPair tag : tagListeners) {
			CommandJs c = new CommandJs();
			c.eventType = tag.getEventType();
			c.elementId = tag.getTag().getId();
			c.listenerId = tag.getListenerId();
			commands.add(c);
		}

		try {
			return mapper.writeValueAsString(commands);
		} catch (JsonProcessingException e) {
			log.error("", e);
			return "";
		}
	}

	public void callEvenetListener(String elementId, String listenerId, String body) {
		if (tagListeners == null) {
			log.error("null je");
			return;
		}
		for (TagListenerPair t : tagListeners) {
			if (t.getListenerId().equals(listenerId)) {
				if (t.getEventType() == EventType.CLICK) {
					ButtonClickListener<Button> listener = (ButtonClickListener<Button>) t.getListener();
					listener.onClick(new ButtonClickEvent((Button) t.getTag()));
					break;
				}
				throw new RuntimeException("What listener?? "+ t);

			}
		}

	}

}

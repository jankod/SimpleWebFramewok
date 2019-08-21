package hr.ja.fr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Data
class CommandJs {
	String elementId;
	// String pageId;
	String eventType;
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
		log.debug("imam commands {}", tagListeners.size());
		for (TagListenerPair tag : tagListeners) {
			CommandJs c = new CommandJs();
			c.eventType = tag.getEventType();
			c.elementId = tag.getTag().getId();
			commands.add(c);
		}

		try {
			return mapper.writeValueAsString(commands);
		} catch (JsonProcessingException e) {
			log.error("", e);
			return "";
		}
	}

}

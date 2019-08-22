package hr.ja.fr;

import org.apache.commons.lang.RandomStringUtils;

import hr.ja.fr.CommandJs.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class TagListenerPair {

	private MyTag tag;
	private TagEventListener listener;
	private String listenerId;

	public CommandJs.EventType getEventType() {
		if (listener instanceof ButtonClickListener) {
			return EventType.CLICK;
		}
		throw new RuntimeException("What event??  " + listener.getClass());
	}

	public TagListenerPair(MyTag myTag, TagEventListener l) {
		tag = myTag;
		listener = l;
		listenerId = RandomStringUtils.randomAlphabetic(9);
	}
}
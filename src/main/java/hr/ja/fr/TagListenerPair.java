package hr.ja.fr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@Slf4j
public class TagListenerPair {
	MyTag tag;
	TagEventListener listener;

	public String getEventType() {
		if (listener instanceof ButtonClickListener) {
			return "click";
		}
		log.error("What event??  {}", listener.getClass());
		return "????";
	}
}
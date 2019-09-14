package hr.ja.fr;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClickUtil {

	private static List<TagListenerPair> tagListeners = new ArrayList<>();

	public static void addListener(MyTag myTag, TagEventListener listener) {
		log.debug("tagID {} listener", myTag.getId());
		tagListeners.add(new TagListenerPair(myTag, listener));
	}

	public static <T extends Page> T createPageCatchListeners(Callable<T> c) {
		try {
//			log.debug("Start create page");
			T page = c.call();
//			log.debug("End create page");

			return page;
		} catch (final Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public static List<TagListenerPair> getTagListeners() {
		return tagListeners;
	}

	public static void removeTagListeners() {
		tagListeners.clear();
	}

}

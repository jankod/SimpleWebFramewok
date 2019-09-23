package hr.ja.fr.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import hr.ja.fr.ServerPage;
import hr.ja.fr.ServerSession;
import hr.ja.fr.WebUtil;
import hr.ja.fr.commands.CommandJs;
import hr.ja.fr.commands.DOMCommand;
import hr.ja.fr.elements.ElementCommands;
import hr.ja.fr.events.ElementEvent;
import hr.ja.fr.events.ElementEvent.EventType;
import hr.ja.fr.events.EventCommands;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageEventManager {

	public String renderPageEvents(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String pageId = req.getHeader("pageId");
		String elementId = req.getHeader("elementId");
		String listenerId = req.getHeader("listenerId");

		String body = IOUtils.toString(req.getReader());
//		log.debug("body {}", body);
//		log.debug("Dobio ajax, pageId {} elementId {}", pageId, elementId);

		ServerSession sess = WebUtil.getSession(req);
		ServerPage page = sess.getPage(pageId);

		ElementCommands.clear();
		EventCommands.clear();
		page.callEventListeners(elementId, listenerId, body);
		List<ElementEvent> newEvents = EventCommands.getEvents();
		List<CommandJs> commands = newEvents.stream().map(PageEventManager::createCommand).collect(Collectors.toList());
		List<CommandJs> commandsEvents = ElementCommands.getCommands();

		page.addEventListeners(newEvents);
		ArrayList<CommandJs> result = new ArrayList<>();
		result.addAll(commandsEvents);
		result.addAll(commands);
		return WebUtil.toJson(result); // json;
	}

	public static CommandJs createCommand(ElementEvent elEvent) {
		if (elEvent.getEventType() == EventType.CLICK) {
			// ClickAttachCommand c = new ClickAttachCommand(elEvent);
			return new DOMCommand("setClickEvent", elEvent.getElement().id(), elEvent.getListenerId());
		}
		throw new RuntimeException("What is that command???");
	}

}

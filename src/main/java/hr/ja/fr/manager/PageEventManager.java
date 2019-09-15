package hr.ja.fr.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.ja.fr.ServerPage;
import hr.ja.fr.ServerSession;
import hr.ja.fr.WebUtil;
import hr.ja.fr.commands.CommandJs;
import hr.ja.fr.commands.DOMCommand;
import hr.ja.fr.elements.ElementCommands;
import hr.ja.fr.events.ElementEvent;
import hr.ja.fr.events.EventCommands;
import hr.ja.fr.events.ElementEvent.EventType;
import lombok.extern.slf4j.Slf4j;
import spark.Request;
import spark.Response;

@Slf4j
public class PageEventManager {

	public String renderPageEvents(Request req, Response res) {
		String pageId = req.headers("pageId");
		String elementId = req.headers("elementId");
		String listenerId = req.headers("listenerId");

		String body = req.body();
//		log.debug("body {}", body);
//		log.debug("Dobio ajax, pageId {} elementId {}", pageId, elementId);

		ServerSession sess = WebUtil.getSession(req);
		ServerPage page = sess.getPage(pageId);

		ElementCommands.clear();
		EventCommands.clear();
		page.callEvenetListeners(elementId, listenerId, body);
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

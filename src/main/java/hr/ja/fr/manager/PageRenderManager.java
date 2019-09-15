package hr.ja.fr.manager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import hr.ja.fr.events.ElementEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import hr.ja.fr.Page;
import hr.ja.fr.ServerPage;
import hr.ja.fr.ServerSession;
import hr.ja.fr.WebUtil;
import hr.ja.fr.elements.ElementCommands;
import hr.ja.fr.events.EventCommands;
import lombok.extern.slf4j.Slf4j;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

@Slf4j
public class PageRenderManager {

	private static VelocityTemplateEngine velo = new VelocityTemplateEngine();

	public String renderPage(Class<? extends Page> pageClass, Request req, Response res) {
		try {
			ServerSession sess = WebUtil.getSession(req);

			ServerPage page;
			page = createNewPageInstance(pageClass);

			sess.addPage(page);

			Map<String, Object> model = new HashMap<>();
			model.put("pageId", page.getPage().getPageId());

			model.put("commandJs", page.createCommandsJs());
			model.put("bodyHtml", renderPageBody(page.getPage()));
			return render(model, "index.vm");

		} catch (Exception e) {
			log.error("", e);
			return "Server error: " + e.getMessage();
		}
	}

	private String renderPageBody(Page page) {
		StringBuilder b = new StringBuilder();
		page.getTags().forEach(e -> b.append(e.toString()));
		return b.toString();
	}

	public ServerPage createNewPageInstance(Class<? extends Page> p) throws NoSuchMethodException, SecurityException {
		Constructor<? extends Page> c = p.getConstructor();
		c.setAccessible(true);
		Page instance = null;
		
		
		EventCommands.clear();
		try {
			instance = c.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			log.error("", e);
		}

		ServerPage serverPage = new ServerPage(instance, EventCommands.getEvents());

		return serverPage;
	}

	public String render(Map<String, Object> model, String templatePath) {

		return velo.render(new ModelAndView(model, templatePath));
	}

}

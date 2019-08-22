package hr.ja.fr;

import static spark.Spark.init;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import spark.ModelAndView;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;

@Slf4j
public class WebFramework {
	private static ServerSessionManager sessionManager = new ServerSessionManager();

	private static VelocityTemplateEngine velo = new VelocityTemplateEngine();

	public static String render(Map<String, Object> model, String templatePath) {
		return velo.render(new ModelAndView(model, templatePath));
	}

	ArrayList<Class<? extends Page>> pages = new ArrayList<>();

	public void addPage(Class<? extends Page> page) {
		pages.add(page);
	}

	private ServerPage createNewPageInstance(Class<? extends Page> p) throws NoSuchMethodException, SecurityException {
		Constructor<? extends Page> c = p.getConstructor();
		c.setAccessible(true);

		Page page = ClickUtil.createPageCatchListeners(() -> {
			Page instance = c.newInstance();
			return instance;
		});
		List<TagListenerPair> tagListeners = ClickUtil.getTagListeners();
//		log.debug("nassao {} listenera", tagListeners.size());
		ServerPage serverPage = new ServerPage(page, new ArrayList<>(tagListeners));
		ClickUtil.removeTagListeners();

		return serverPage;

	}

	private String getRoutePath(Class<? extends Page> p) {
		// log.debug("{} {}", p, p.getSuperclass());
		Route rute = p.getAnnotation(Route.class);
		assert rute != null : "Route annotation je null od page " + p;
		return rute.value();
	}

	private String renderPageBody(Page page) {
		StringBuilder b = new StringBuilder();
		page.getTags().forEach(e -> b.append(e.toString()));
		return b.toString();
	}

	public void start(int port) {
		try {
			Spark.port(port);
			Spark.staticFiles.location("/public"); // Static files
			init();

			Spark.post("/ajax", (req, res) -> {
				String pageId = req.headers("pageId");
				String elementId = req.headers("elementId");
				String listenerId = req.headers("listenerId");

				String body = req.body();
				log.debug("body {}", body);
//				log.debug("Dobio ajax, pageId {} elementId {}", pageId, elementId);

				ServerSession sess = sessionManager.getSession(req);
				ServerPage page = sess.getPage(pageId);
				page.callEvenetListener(elementId, listenerId, body);
				String json = TagUtil.getCommandsJson();
				TagUtil.getCommands().clear();

				return json;
			});

			for (Class<? extends Page> pageClass : pages) {
				String route = getRoutePath(pageClass);

				Spark.get(route, (req, res) -> {

					ServerSession sess = sessionManager.getSession(req);

					ServerPage page = createNewPageInstance(pageClass);

					sess.addPage(page);

					Map<String, Object> model = new HashMap<>();
					model.put("pageId", page.getPage().getPageId());

					model.put("commandJs", page.createCommandsJs());
					model.put("bodyHtml", renderPageBody(page.getPage()));
					return render(model, "index.vm");

				});
			}

			log.debug("Started on port " + port);
			Spark.awaitInitialization();
			Spark.awaitStop();
			log.debug("Stopped...");
		} catch (Throwable e) {
			log.error("", e);
			Spark.stop();
		}
	}

}

package hr.ja.fr;

import static spark.Spark.init;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hr.ja.fr.manager.PageEventManager;
import hr.ja.fr.manager.PageRenderManager;
import lombok.extern.slf4j.Slf4j;
import spark.ModelAndView;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;

@Slf4j
public class WebFramework {

	ArrayList<Class<? extends Page>> pages = new ArrayList<>();

	public void addPage(Class<? extends Page> page) {
		pages.add(page);
	}

	public void start(int port) {
		try {
			PageRenderManager pageRenderManager = new PageRenderManager();
			PageEventManager pageEventManager = new PageEventManager();

			Spark.port(port);
			Spark.staticFiles.location("/public"); // Static files
			init();
			for (Class<? extends Page> pageClass : pages) {

				String route = getRoutePath(pageClass);

				Spark.get(route, (req, res) -> {
					return pageRenderManager.renderPage(pageClass, req, res);
				});
			}

			Spark.post("/page_event", (req, res) -> {
				return pageEventManager.renderPageEvents(req, res);
			});

			log.debug("Started on port " + port);
			Spark.awaitInitialization();
			Spark.awaitStop();
			log.debug("Stopped...");
		} catch (Throwable e) {
			log.error("", e);
			Spark.stop();
		}
	}

	private String getRoutePath(Class<? extends Page> p) {
		Route rute = p.getAnnotation(Route.class);
		assert rute != null : "Route annotation je null od page " + p;
		return rute.value();
	}
}

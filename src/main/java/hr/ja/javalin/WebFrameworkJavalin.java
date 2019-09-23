package hr.ja.javalin;

import java.util.ArrayList;

import hr.ja.fr.Page;
import hr.ja.fr.Route;
import hr.ja.fr.manager.PageEventManager;
import hr.ja.fr.manager.PageRenderManager;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.template.JavalinVelocity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebFrameworkJavalin {

	ArrayList<Class<? extends Page>> pages = new ArrayList<>();

	public void addPage(Class<? extends Page> page) {
		pages.add(page);
	}

	public void start(int port) {
		try {

			PageRenderManager pageRenderManager = new PageRenderManager();
			PageEventManager pageEventManager = new PageEventManager();

			Javalin app = Javalin

					.create(c->{
						c.enableWebjars();
						c.addStaticFiles("/public");
						c.enableDevLogging();
					})

					.start(port);

			for (Class<? extends Page> pageClass : pages) {

				String route = getRoutePath(pageClass);

				app.get(route, (ctx) -> {
					ctx.html(pageRenderManager.renderPage(pageClass, ctx.req, ctx.res));
				});
			}

			app.post("/page_event", (c) -> {
				c.result(pageEventManager.renderPageEvents(c.req, c.res));
			});

			log.debug("Started on port " + port);
//			Spark.awaitInitialization();
//			Spark.awaitStop();
			log.debug("Stopped...");
		} catch (Throwable e) {
			log.error("", e);
//			Spark.stop();
		}
	}

	private String getRoutePath(Class<? extends Page> p) {
		Route rute = p.getAnnotation(Route.class);
		assert rute != null : "Route annotation je null od page " + p;
		return rute.value();
	}

}

package hr.ja.test.events;

import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinPebble;
import io.javalin.plugin.rendering.template.JavalinVelocity;
import lombok.extern.slf4j.Slf4j;

import static io.javalin.plugin.rendering.template.TemplateUtil.model;

@Slf4j
public class DemoEvents {

	public static void main(String[] args) {
		Javalin app = Javalin

				.create(c -> {
					c.enableWebjars();
					c.addStaticFiles("/public");
					c.enableDevLogging();
				})
				.start(81);

		JavalinRenderer.register(JavalinVelocity.INSTANCE);
		app.get("/", h -> {
			h.render("/demo_events.vm", model("pero", "ja sam janko per"));
			return;

		});

	}
}

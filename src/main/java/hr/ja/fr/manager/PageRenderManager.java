package hr.ja.fr.manager;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.ja.fr.layout.Layout;
import hr.ja.fr.layout.LayoutPage;
import org.apache.commons.io.IOUtils;

import hr.ja.fr.Page;
import hr.ja.fr.ServerPage;
import hr.ja.fr.ServerSession;
import hr.ja.fr.WebUtil;
import hr.ja.fr.events.EventCommands;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageRenderManager {



	public String renderPage(Class<? extends Page> pageClass, HttpServletRequest req, HttpServletResponse res) {
		try {
			ServerSession sess = WebUtil.getSession(req);

			ServerPage page;
			page = createNewPageInstance(pageClass);

			sess.addPage(page);

			Map<String, Object> model = new HashMap<>();
			model.put("pageId", page.getPage().getPageId());

			model.put("commandJs", page.createCommandsJs());
			model.put("bodyHtml", renderPageBody(page.getPage()));
			return render(model, "OLD index.vm");

		} catch (Exception e) {
			log.error("", e);
			return "Server error: " + e.getMessage();
		}
	}

	private String renderPageBody(Page page) {
		StringBuilder b = new StringBuilder();
		page.getElements().forEach(e -> b.append(e.toString()));
		return b.toString();
	}

	public ServerPage createNewPageInstance(Class<? extends Page> p) throws NoSuchMethodException, SecurityException {
		Constructor<? extends Page> c = p.getConstructor();
		c.setAccessible(true);
		Page instance = null;

		LayoutPage layout = p.getDeclaredAnnotation(LayoutPage.class);
//		log.debug("found layout {}", layout);
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

	public String render(Map<String, Object> model, String templatePath) throws IOException {

		String r = "/public/index.vm";
		URL resource = PageRenderManager.class.getResource(r);
		if(resource == null) {
			log.error("canot find {}", r);
		}
		
		String res = IOUtils.toString(resource, StandardCharsets.UTF_8);
		Set<Entry<String, Object>> entrySet = model.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			res = res.replace("$"+entry.getKey(), entry.getValue().toString());
		}
		return res;
	}

}

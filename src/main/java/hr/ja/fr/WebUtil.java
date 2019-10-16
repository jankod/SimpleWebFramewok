package hr.ja.fr;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassPathUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.wicket.core.util.resource.ClassPathResourceFinder;
import org.apache.wicket.util.resource.IResourceStream;

@Slf4j
public class WebUtil {

	public static ServerSession getSession(HttpServletRequest req) {
		HttpSession sess = req.getSession(true);

		if (sess.getAttribute("ss") == null) {
			ServerSession m = new ServerSession();
			sess.setAttribute("ss", m);
		}

		return (ServerSession) sess.getAttribute("ss");

	}

	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.setSerializationInclusion(Include.NON_NULL);
	}

	public static String toJson(Object o) {
		try {
			return mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			log.error(" ", e);
			return "???";
		}
	}

	public static String loadTemplate(Class<?> class1) throws IOException {
		String name = class1.getName().replace('.', '/') + ".html";
		log.debug("Try {} {}", class1, name);
		InputStream in = ClassLoader.getSystemResourceAsStream(name);
		log.debug("nasao {}", in);

		if (in == null) {
			ClassPathResourceFinder finder = new ClassPathResourceFinder(null);
			IResourceStream iResourceStream = finder.find(class1, name);
			log.debug("nasao {}", iResourceStream);
			in = class1.getResourceAsStream(name);

//			String full = ClassPathUtils.toFullyQualifiedPath(class1, class1.getSimpleName() + ".html");
//			log.debug("full {}", full);
			if (in == null) {
				in = ClassLoader.getSystemResourceAsStream(name);
				log.debug("nasao {}", in);

//            in =  ClassLoader.getSystemClassLoader().getResourceAsStream("." + name);
				if (in == null) {
					throw new IOException("Can not load template " + name);
				}
			}
		}
		String res = IOUtils.toString(in, StandardCharsets.UTF_8);
		return res;
	}

}

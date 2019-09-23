package hr.ja.fr;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


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
		InputStream in = class1.getResourceAsStream(class1.getSimpleName() + ".html");
		String res = IOUtils.toString(in, StandardCharsets.UTF_8);
		return res;
	}

}

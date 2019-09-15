package hr.ja.fr;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import spark.Request;
import spark.Session;

@Slf4j
public class WebUtil {

	public static ServerSession getSession(Request req) {
		Session sparkSession = req.session(true);

		if (sparkSession.attribute("ss") == null) {
			ServerSession m = new ServerSession();
			sparkSession.attribute("ss", m);
		}

		return sparkSession.attribute("ss");

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
		InputStream in = class1.getResourceAsStream(class1.getSimpleName()+".html");
		String res = IOUtils.toString(in, StandardCharsets.UTF_8);
		return res;
	}

}

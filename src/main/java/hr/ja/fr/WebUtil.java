package hr.ja.fr;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hr.ja.fr.events.EventCommands;
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

}

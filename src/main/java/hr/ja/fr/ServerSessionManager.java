package hr.ja.fr;

import java.util.HashMap;

import spark.Request;
import spark.Session;

public class ServerSessionManager {

	private HashMap<String, ServerSession> sessions = new HashMap<>();

	public ServerSession getSession(Request req) {

		Session sparkSession = req.session(true);
		String sessionId = sparkSession.id();

		ServerSession sess ;
		if (sessions.containsKey(sessionId)) {
			sess = sessions.get(sessionId);
		} else {
			sess = new ServerSession(sessionId);
			sessions.put(sessionId, sess);
		}
		
		// TODO zapisati kad je zadnji put zatrazena sessija
		return sess;

	}

}

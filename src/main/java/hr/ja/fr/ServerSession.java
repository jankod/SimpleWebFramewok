package hr.ja.fr;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor
public class ServerSession implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, ServerPage> pages = new HashMap<>();

	private String sessionId;
	
	public ServerSession(String sessionId) {
		this.sessionId = sessionId;
	
	}

	public void addPage(ServerPage up) {
		up.setUserSession(this);
		pages.put(up.getPage().getPageId(), up);
	}

}


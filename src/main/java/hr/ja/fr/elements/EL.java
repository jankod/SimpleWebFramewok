package hr.ja.fr.elements;

import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Element;

public class EL {

	protected Element el;

	private static int idCounter = 1;

	public EL(String tag) {
		el = new Element(tag);
		enshureId();
		DataUtil.newEl(this);
	}

	public EL addClass(String className) {
		DataUtil.addClass(this, className);
		el.addClass(className);
		return this;
	}

	public static void appendToBody(EL b) {
		DataUtil.appendToBody(b);
	}

	public String id() {
		return el.attr("id");
	}

	public EL appendText(String text) {
		DataUtil.appendText(this, text);
		el.appendText(text);
		return this;
	}

	public EL appendChild(EL child) {
		DataUtil.appendChild(this, child);
		el.appendChild(child.el);
		return this;
	}



	private String enshureId() {
		String id = el.attr("id");
		if (StringUtils.isEmpty(id)) {
			id = idCounter++ + "";
			el.attr("id", id);
		}
		return id;
	}
	
	@Override
	public String toString() {
		return el.toString();
	}

}

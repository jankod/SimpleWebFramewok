package hr.ja.fr.elements;

import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Element;

public class EL {

	protected Element el;

	private static int idCounter = 1;

	public EL(String tag) {
		el = new Element(tag);
		enshureId();
		ElementCommands.newEl(this);
	}

	public EL addClass(String className) {
		ElementCommands.addClass(this, className);
		el.addClass(className);
		return this;
	}

	public static void appendToBody(EL b) {
		ElementCommands.appendToBody(b);
	}

	public String id() {
		return el.attr("id");
	}

	public EL html(String html) {
		el.html(html);
		ElementCommands.html(this, html);
		return this;
	}

	public EL appendText(String text) {
		ElementCommands.appendText(this, text);
		el.appendText(text);
		return this;
	}

	public EL text(String text) {
		ElementCommands.text(this, text);
		el.text(text);
		return this;
	}

	public EL appendChild(EL child) {
		ElementCommands.appendChild(this, child);
		el.appendChild(child.el);
		return this;
	}
	
	public void attr(String name, String value) {
		el.attr(name, value);
		ElementCommands.attr(this, name, value);
	}

	
	public void clear() {
		el.children().remove();
	}

	protected String enshureId() {
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

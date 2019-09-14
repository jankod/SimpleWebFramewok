package hr.ja.fr;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Element;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyTag implements TagManipulation {

//	@Getter
//	private List<MyTag> childTags = new ArrayList<>(2);

	private MyElement element;

	public MyTag(String name) {
		element = new MyElement(name);
		enshureId();
	}

	protected MyElement getElement() {
		return element;
	}

	public MyTag(String name, String text) {
		this(name);
		element.appendText(text);
	}

	public void add(MyTag tag) {
		element.add(tag);
		TagUtil.add(this, tag);
	}

	public MyElement getParent() {
		MyElement parent = (MyElement) element.parent();
		return parent;
	}

	private static final AtomicInteger idCounter = new AtomicInteger(1);

	String enshureId() {
		String id = element.attr("id");
		if (StringUtils.isEmpty(id)) {
			id = idCounter.getAndIncrement() + "";
			element.attr("id", id);
		}
		return id;
	}

	public String getId() {
		return element.attr("id");
	}

	public MyTag html(String html) {
		element.append(html);
		TagUtil.append(html, this);
		return this;
	}

	public MyTag text(String text) {
		element.text(text);
		TagUtil.text(text, this);
		return this;
	}

	@Override
	public String toString() {
		return element.toString();
	}

}

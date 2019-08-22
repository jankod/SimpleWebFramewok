package hr.ja.fr;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyTag {

	public static void main(String[] args) {
		MyTag div = new MyTag("div", "ovo je text unutrasniji");
		div.html("<b>evo ga </b>");
		System.out.println(div);

	}

	@Getter
	private List<MyTag> childTags = new ArrayList<>(2);

	private MyElement element;

	@Getter
	@Setter
	private MyTag parrent;

	public MyTag(String name) {
		element = new MyElement(name);
		enshureId();
	}

	public MyTag(String name, String text) {
		this(name);
		element.appendText(text);
		
	}

	public void add(MyTag tag) {
		childTags.add(tag);
		tag.setParrent(this);
		TagUtil.add(this, tag);
	}

	String enshureId() {
		String id = element.attr("id");
		if (StringUtils.isEmpty(id)) {
			id = RandomStringUtils.randomAlphabetic(8);
			element.attr("id", id);
		}
		return id;
	}

	public String getId() {
		return element.attr("id");
	}

	public MyTag html(String html) {
		element.append(html);
		return this;
	}

	public MyTag text(String text) {
		element.text(text);
		return this;
	}

	@Override
	public String toString() {
		return element.toString();
	}



}

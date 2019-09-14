package hr.ja.fr;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Page {

	@Getter()
	private String pageId = RandomStringUtils.randomAlphabetic(6);

	@Getter(AccessLevel.PACKAGE)
	private List<MyTag> tags = new ArrayList<MyTag>();

	public void add(MyTag myTag) {
		tags.add(myTag);
		TagUtil.addToBody(myTag);
	}
}
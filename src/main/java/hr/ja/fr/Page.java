package hr.ja.fr;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;

import hr.ja.fr.elements.EL;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Page {

	@Getter()
	private String pageId = RandomStringUtils.randomAlphabetic(6);

	@Getter()
	private List<EL> tags = new ArrayList<EL>();

	public void add(EL element) {
		tags.add(element);
	}
}
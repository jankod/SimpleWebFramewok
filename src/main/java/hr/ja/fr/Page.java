package hr.ja.fr;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.jsoup.Jsoup;

import hr.ja.fr.elements.EL;
import hr.ja.fr.elements.ElementCommands;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Page {

	@Getter()
	private String pageId = RandomStringUtils.randomAlphabetic(6);

	@Getter()
	private List<EL> elements = new ArrayList<EL>();

	public void add(EL element) {
		elements.add(element);
		//element.setAttach(true);
		ElementCommands.appendToBody(element);
	}

}
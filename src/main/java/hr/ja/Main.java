package hr.ja;

import hr.ja.fr.Page;
import hr.ja.fr.Route;
import hr.ja.fr.WebFramework;
import hr.ja.fr.elements.Button;
import lombok.extern.slf4j.Slf4j;

public class Main {

	public static void main(String[] args) {

		WebFramework w = new WebFramework();
		w.addPage(Page1.class);
		w.start(8080);
	}
}


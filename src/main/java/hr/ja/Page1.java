package hr.ja;

import hr.ja.fr.Page;
import hr.ja.fr.Route;
import hr.ja.fr.elements.Button;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Route("/")
class Page1 extends Page {

	private String value = "prvo";

	public Page1() {

		Button b = new Button("ovo je button");
		
		b.addClickListener(e -> {
			log.debug("click je bio: {} ", e.getSource());
		});

		add(b);

	}

}

package hr.ja;

import hr.ja.fr.Page;
import hr.ja.fr.Route;
import hr.ja.fr.elements.Button;
import hr.ja.fr.elements.Div;
import hr.ja.fr.elements.Window;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Route("/")
class Page1 extends Page {

	private String value = "prvo";

	public Page1() {

		Button button = new Button("ovo je button");
		Div div = new Div("podaci");
		div.html("<b>daj neki novi</b>");
		button.addClickListener(e -> {
			log.debug("click je bio: {} ", e.getSource());

			Button b = new Button("Novi button");
			div.appendChild(b);
			b.addClickListener(event -> {
				log.debug("click na novi button " + event.getSource());
				Window.alert("Kliknuo na novi button " + event.getSource());
				div.clear();
			});
		});

		add(div);
		add(button);

	}

}

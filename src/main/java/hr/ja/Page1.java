package hr.ja;

import hr.ja.fr.Page;
import hr.ja.fr.Route;
import hr.ja.fr.elements.Button;
import hr.ja.fr.elements.Div;
import hr.ja.fr.elements.Window;
import hr.ja.fr.elements.bs.BsButton;
import hr.ja.fr.elements.bs.BsModal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Route("/")
class Page1 extends Page {

	private int counter = 1;

	BsModal modal;

	public Page1() {
		modal = new BsModal(new Div("Ja sam modal"));

		Button button = new BsButton("inctrement counter");

		Div div = new Div("podaci");
		div.html("<b>daj neki novi</b>");

		Div counterDiv = new Div(" Counter " + counter);

		button.addClickListener(e -> {
//			log.debug("click je bio: {} ", e.getSource());
			modal.show();
			
			counter++;
			counterDiv.text("Counter " + counter);

		});

		add(counterDiv);
		add(div);
		add(button);

	}

}

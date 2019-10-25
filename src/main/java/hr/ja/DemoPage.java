package hr.ja;

import hr.ja.fr.Page;
import hr.ja.fr.Route;
import hr.ja.fr.Title;
import hr.ja.fr.elements.Div;
import hr.ja.fr.elements.Window;
import hr.ja.fr.elements.bs.Bootbox;
import hr.ja.fr.elements.bs.BsButton;
import hr.ja.fr.elements.bs.BsModal;
import hr.ja.fr.layout.LayoutPage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Route("/")
@LayoutPage(DemoLayout.class)
@Title("demo page")
public class DemoPage extends Page {

	BsModal modal;
	private int counter = 1;

	public DemoPage() {

		modal = new BsModal(new Div("Ovo je modal"));

		BsButton btn = new BsButton("show modal");
		btn.addClickListener(event -> {
			// modal.getElement().html("<b>evo novoga :) " + counter++ + "</b>");

			modal.setContent(new Div("Ovo je modal " + counter++));
			log.debug("demo");
			modal.show();
		});
		
		add(btn);

	}

}

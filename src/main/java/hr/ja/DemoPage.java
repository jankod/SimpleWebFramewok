package hr.ja;

import hr.ja.fr.Page;
import hr.ja.fr.Route;
import hr.ja.fr.elements.Button;
import hr.ja.fr.elements.Div;
import hr.ja.fr.elements.Window;
import hr.ja.fr.elements.bs.Bootbox;
import hr.ja.fr.elements.bs.BsButton;
import hr.ja.fr.elements.bs.BsModal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Route("/")
class DemoPage extends Page {

	private int counter = 1;

	BsModal modal;

	Button btn = new BsButton("Ja sam modal");
	Button button = new BsButton("inctrement counter");

	public DemoPage() {
		btn.addClickListener(l -> {
			log.debug("ja sam but from dialog " + btn.hashCode());
			Window.alert("ja sam iz modala!!!");
		});
		modal = new BsModal(btn);

		Div div = new Div("podaci 22");
		div.html("<b>evo ga niuje bio pa zasto nije bio</b>");

		Div counterDiv = new Div(" Counter " + counter);

		
		
		button.addClickListener(e -> {
//			log.debug("click je bio: {} ", e.getSource());
//			modal.show();

			counter++;
			counterDiv.text("Counter " + counter);
			Bootbox.alert("Ovo je naslov neki", "Ovo je poruka");
			Bootbox.alert("Ovo je samo poruka");

		});
		button.addClickListener(l -> {
//			log.debug("button name " + button.getText());
//			button.text("novo " + org.apache.commons.lang3.RandomStringUtils.randomNumeric(4));
		});

		add(counterDiv);
		add(div);
		add(button);

	}

}

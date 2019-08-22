package hr.ja;

import hr.ja.fr.Button;
import hr.ja.fr.MyTag;
import hr.ja.fr.Page;
import hr.ja.fr.Route;
import hr.ja.fr.WebFramework;
import lombok.extern.slf4j.Slf4j;

public class Main {

	public static void main(String[] args) {

		WebFramework w = new WebFramework();
		w.addPage(Page1.class);
		w.start(8080);
	}
}

@Slf4j
@Route("/")
class Page1 extends Page {

	private String value = "prvo";

	public Page1() {
		MyTag myTag = new MyTag("div", "ovo je div neki");
		add(myTag);
//		log.debug("imam ID " + getPageId());

		Button b = new Button("ovo je button");
		b.addClickListener(e -> {
			myTag.add(new MyTag("br", "novi tag"));
			log.debug("click je bio: {} ", e.getSource());
		});
		add(b);

	}

}

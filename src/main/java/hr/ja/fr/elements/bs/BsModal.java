package hr.ja.fr.elements.bs;

import java.io.IOException;

import hr.ja.fr.WebUtil;
import hr.ja.fr.elements.Div;
import hr.ja.fr.elements.EL;
import hr.ja.fr.elements.ElementCommands;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BsModal extends Div {

	public BsModal(EL content) {
		try {
			
			String template = WebUtil.loadTemplate(getClass());
			template = template.replace("${content}", content.toString());
			this.el = el.html(template).children().first();
			this.enshureId();
//			ElementCommands.newEl(this);
			
		} catch (IOException e) {
			log.error("", e);
		}

	}

	public void show() {
		ElementCommands.newEl(this);
		ElementCommands.callJquery(this, "modal");
		// .modal(options);
	}

	public static void main(String[] args) {
		System.out.println(new BsModal(new Div("ja sam sadržaj")));
	}

}

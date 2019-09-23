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
			
		} catch (IOException e) {
			log.error("", e);
		}

	}

	public void show() {
		ElementCommands.newEl(this);
		ElementCommands.callJquery(this, "modal", "1", "2", "tri");
		ElementCommands.exec(this, "$('#"+id()+"').modal( 'hide' ).data( 'bs.modal', null );");
		
		// $('#45').modal( 'hide' ).data( 'bs.modal', null );

		
		// .modal(options);
	}

	public static void main(String[] args) {
		System.out.println(new BsModal(new Div("ja sam sadržaj")));
	}

}

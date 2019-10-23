package hr.ja.fr.elements.bs;

import java.io.IOException;

import org.apache.commons.lang3.RandomUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.html.HtmlElement;

import hr.ja.fr.WebUtil;
import hr.ja.fr.elements.Div;
import hr.ja.fr.elements.EL;
import hr.ja.fr.elements.ElementCommands;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BsModal extends Div {

// FIXME clear modal html dom after 
	public BsModal(EL content) {
		try {

			String template = WebUtil.loadTemplate(getClass());
			template = template.replace("${content}", content.toString());
			// el = Jsoup.parseBodyFragment(template);
			// this.wrap(template);

			// EL el = new EL(el);
			this.el = el.html(template).children().first();
			this.ensureId();
			// this.children().first().attr("id", RandomUtils.nextInt()+"");
			// super.htmlNoEvent(template);

		} catch (IOException e) {
			log.error("", e);
		}

	}

	public void show() {
		ElementCommands.newEl(this);
		ElementCommands.callJquery(this, "modal");
		// ElementCommands.exec(this, "$('#"+id()+"').children().modal( 'hide' ).data(
		// 'bs.modal', null );");

		// $('#45').modal( 'hide' ).data( 'bs.modal', null );

		// .modal(options);
	}

	public static void main(String[] args) {
		System.out.println(new BsModal(new Div("ja sam sadržaj")));
	}

}

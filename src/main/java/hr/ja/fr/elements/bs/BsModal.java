package hr.ja.fr.elements.bs;

import java.io.IOException;

import hr.ja.fr.components.Component;
import org.apache.commons.lang3.RandomUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import hr.ja.fr.WebUtil;
import hr.ja.fr.elements.Div;
import hr.ja.fr.elements.EL;
import hr.ja.fr.elements.ElementCommands;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Slf4j
public class BsModal extends Component {

	private boolean send = false;

	private EL el;

	private EL content;

	// FIXME clear modal html dom after
	public BsModal(EL content) {
		this.content = content;
		try {

			String template = WebUtil.loadTemplate(getClass());

			// template = template.replace("${content}", content.toString());
			// el = Jsoup.parseBodyFragment(template).body();
			// this.el = el.html(template).children().first();
			// this.el.ensureId();
			this.el = EL.fromRootHtml(template);
			this.el.getElement().getElementsByClass("modal-body").get(0).appendChild(content.getElement());
		} catch (IOException e) {
			log.error("", e);
		}

	}

	public void show() {
		if (!send)
			ElementCommands.newEl(this.el);

		ElementCommands.callJquery(this.el, "modal");
		send = true;
	}

	public void setContent(EL content) {
		this.content = content;
		Element modalBody = this.el.getElement().getElementsByClass("modal-body").get(0);
		modalBody.children().clear();
		modalBody.appendChild(content.getElement());
	}

	public static void main(String[] args) {
		System.out.println(new BsModal(new Div("ja sam sadržaj")));
	}

	@Override
	public EL getElement() {
		return el;
	}

}

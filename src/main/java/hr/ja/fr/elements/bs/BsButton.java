package hr.ja.fr.elements.bs;

import hr.ja.fr.elements.Button;

public class BsButton extends Button{

	public BsButton(String text) {
		super(text);
		this.el.attr("type", "button");
		this.el.addClass("btn btn-outline-primary");
	}

}

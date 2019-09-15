package hr.ja.test.newelement;

import hr.ja.fr.elements.Button;
import hr.ja.fr.elements.ElementCommands;
import hr.ja.fr.elements.Div;
import hr.ja.fr.elements.EL;

public class Test1 {

	public static void main(String[] args) {
		Button b = new Button("janko");

		b.addClass("btn");

		Div div = new Div("Ovo je neki div");

		div.appendChild(new Div("Ovo je div 2"));

		EL.appendToBody(b);
		EL.appendToBody(div);

//		DataUtil.print();

	}
}

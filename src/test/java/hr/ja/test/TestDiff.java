package hr.ja.test;

import hr.ja.fr.Button;
import hr.ja.fr.Div;

public class TestDiff {

	public static void main(String[] args) {

		Button b = new Button("pero");

		Div div = new Div("Ovo je div neki");
		b.add(div);
		b.addClickListener(event -> {
			System.out.println("evo ga");
		});
		System.out.println("Parent: "+ div.getParent());
	}
}

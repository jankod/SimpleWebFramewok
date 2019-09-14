package hr.ja.fr;

import org.jsoup.nodes.Element;

public class MyElement extends Element {

	
	public MyElement(String tag) {
		super(tag);
	}

	public void add(MyTag element) {
		this.addChildren(element.getElement());
	}
}

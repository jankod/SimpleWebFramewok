package hr.ja.test.newelement;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;


import lombok.extern.slf4j.Slf4j;

public class Test1 {

	public static void main(String[] args) {
		Button b = new Button("janko");
		
		b.addClass("btn");
		
		Div div = new Div("Ovo je neki div");

		div.appendChild(new Div("Ovo je div 2"));
		
		

		EL.appendToBody(b);
		EL.appendToBody(div);

		DataUtil.print();

	}
}

class Command {
	public String exec;

	public Command(String exec) {
		this.exec = exec;
	}

}

@Slf4j
class DataUtil {

	public static ThreadLocal<List<Command>> data = ThreadLocal.withInitial(() -> {
		return new ArrayList<Command>();
	});


	public static void print() {
		for (Command command : data.get()) {
			System.out.println("man." + command.exec);
		}
	}

	public static void appendChild(EL el, Node child) {
		//log.debug("{} appendChild {}", el.getClass().getSimpleName(), child.getClass().getSimpleName() + " ,value: " + child);

		if(child instanceof TextNode)  {
			exec("appendText('{}', `{}`);", el.id(), child);
		}else {
			exec("appendChild('{}', `{}`);", el.id(), child.attr("id"));	
		}
		
		
		
	}

	public static void appendToBody(EL el) {
		exec("appendToBody('{}');", el.id());
	}

	public static void newEl(EL el) {
		// log.debug("new EL {}", el);
//		exec("$(\""+el+"\");");
//		exec("let el" + el.id() + "= document.createElement(\"" + el.tag() + "\");");
//		exec("el" + el.id() + ".setAttribute(\"id\", \"" + el.id() + "\");");

		exec("createElement('{}', `{}`);", el.id(), el);
	}

	private static void exec(String exec, Object... args) {
		FormattingTuple arrayFormat = MessageFormatter.arrayFormat(exec, args);
		data.get().add(new Command(arrayFormat.getMessage()));
	}

	public static void appendText(EL el, String text) {
		exec("appendText('{}', `{}`);", el.id(), text);
	}

	public static void addClass(EL el, String className) {
		exec("addClass('{}', '{}')", el.id(), className);
	}

}

class EL extends Element {

	private static int idCounter = 1;

	public EL(String tag) {
		super(tag);
		enshureId();
		DataUtil.newEl(this);
	}
	
	@Override
	public Element addClass(String className) {
		DataUtil.addClass(this, className);
		return super.addClass(className);
	}

	public static void appendToBody(EL b) {
		DataUtil.appendToBody(b);
	}

	public String id() {
		return attr("id");
	}

	@Override
	public Element appendText(String text) {
		DataUtil.appendText(this, text);
		return super.appendText(text);
	}

	
	@Override
	public Element appendChild(Node child) {
		DataUtil.appendChild(this, child);
		return super.appendChild(child);
	}

	private String enshureId() {
		String id = attr("id");
		if (StringUtils.isEmpty(id)) {
			id = idCounter++ + "";
			attr("id", id);
		}
		return id;
	}

}

class Div extends EL {

	public Div(String text) {
		super("div");
		text(text);

	}

}

class Button extends EL {

	public Button(String value) {
		super("button");
		text(value);
	}

}

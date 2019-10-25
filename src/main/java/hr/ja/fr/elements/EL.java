package hr.ja.fr.elements;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import lombok.Getter;
import lombok.Setter;

public class EL  {


    public static void main(String[] args) {

        String html = "<b>pero</b>";
        //EL el = Jsoup


        Element document = Jsoup.parseBodyFragment(html).body().child(0);

        System.out.println(document);
    }


	protected Element el;
	
	
    private static long idCounter = 1;

    
    public Element getElement() {
    	return el;
    }
    public EL(String tag) {
    	el = new Element(tag);
        ensureId();
        ElementCommands.newEl(this);
    }

    public EL(Element rootEl) {
        el = rootEl;
        ensureId();
        ElementCommands.newEl(this);
    }

    public static EL fromRootHtml(String rootHtml) {
        Element rootEl = Jsoup.parseBodyFragment(rootHtml).body().child(0);
        return new EL(rootEl);
    }


	public EL addClass(String className) {
        ElementCommands.addClass(this, className);
        el.addClass(className);
        return this;
    }

    public static void appendToBody(EL b) {
        ElementCommands.appendToBody(b);
    }

    public String id() {
        return el.attr("id");
    }

    public EL htmlNoEvent(String html) {
        el.html(html);
        return this;
    }

    public EL html(String html) {
        el.html(html);
        ElementCommands.html(this, html);
        return this;
    }


    public EL appendText(String text) {
        ElementCommands.appendText(this, text);
        el.appendText(text);
        return this;
    }

    public EL text(String text) {
        ElementCommands.text(this, text);
        el.text(text);
        return this;
    }

    //public String getText() {
    //  return el.text();
    //}

    @Override
    public String toString() {
    	return el.toString();
    }


    public EL appendChild(EL child) {
        ElementCommands.appendChild(this, child);
        el.appendChild(child.el);
        return this;
    }

    public EL attr(String name, String value) {
        el.attr(name, value);
        ElementCommands.attr(this, name, value);
        return this;
    }


    public void clear() {
        el.children().remove();
    }

    protected String ensureId() {
        String id = el.attr("id");
        if (StringUtils.isEmpty(id)) {
            id = idCounter++ + "";
            el.attr("id", id);
        }
        return id;
    }
}

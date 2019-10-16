package hr.ja.test;

import net.sf.cglib.proxy.Enhancer;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class DemoCglib {

    public static void main(String[] args) {


        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(DemoCglib.class);


        Element el = new Element("div");
        el.append("<b class='pero'>ovo je bold</b>");



    }

    private List<String> elements = new ArrayList<>();
    public void addElement(String ele) {
        elements.add(ele);
    }
}

package hr.ja.fr.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hr.ja.fr.commands.CommandJs;
import hr.ja.fr.commands.DOMCommand;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ElementCommands {

    public static ThreadLocal<List<CommandJs>> data = ThreadLocal.withInitial(() -> {
        return new ArrayList<CommandJs>();
    });

    public static void alert(String msg) {
        add(new DOMCommand("alert", msg));
    }

    public static void clear() {
        data.get().clear();
    }

    public static void newEl(EL el) {
        add(new DOMCommand("createElement", el.id(), el.toString()));
    }

    public static void appendChild(EL el, EL child) {
        add(new DOMCommand("appendChild", el.id(), child.id()));
    }

    public static void appendToBody(EL el) {
        add(new DOMCommand("appendToBody", el.id()));
    }

    public static void appendText(EL el, String text) {
        add(new DOMCommand("appendText", el.id(), text));
    }

    public static void addClass(EL el, String className) {
        add(new DOMCommand("addClass", el.id(), className));
    }

    public static void text(EL el, String text) {
        add(new DOMCommand("text", el.id(), text));
    }

    public static void attr(EL el, String name, String value) {
        add(new DOMCommand("attr", el.id(), name, value));
    }

    public static void callJquery(EL el, String function, Object... params) {
        ArrayList<Object> allParams = new ArrayList<>();
        allParams.add(el.id());
        allParams.add(function);
        allParams.addAll(Arrays.asList(params));
        add(new DOMCommand("callJquery", allParams.toArray()));
    }

    public static void html(EL el, String html) {
        add(new DOMCommand("html", el.id(), html));
    }

    public static void add(CommandJs c) {
        data.get().add(c);
    }

    public static List<CommandJs> getCommands() {
        return data.get();
    }

    public static void exec(EL el, String exec) {
        add(new DOMCommand("evalExec", el.id(), exec)
        );
    }

}
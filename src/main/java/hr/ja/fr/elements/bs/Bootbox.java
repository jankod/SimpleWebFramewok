package hr.ja.fr.elements.bs;

import hr.ja.fr.commands.DOMCommand;
import hr.ja.fr.elements.ElementCommands;
import org.apache.commons.lang3.Validate;

public class Bootbox {

    public static void alert(String title, String msg) {
        Validate.notEmpty(msg, "msg");
        Validate.notEmpty(title, "title");
        ElementCommands.add(new DOMCommand("bootbox_alert", title, msg));
    }

}

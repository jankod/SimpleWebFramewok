package hr.ja;

import hr.ja.fr.Page;
import hr.ja.fr.Route;
import hr.ja.fr.elements.Button;
import hr.ja.fr.elements.Div;
import hr.ja.fr.elements.Window;
import hr.ja.fr.elements.bs.Bootbox;
import hr.ja.fr.elements.bs.BsButton;
import hr.ja.fr.elements.bs.BsModal;
import hr.ja.fr.events.ButtonClickListener;
import hr.ja.fr.events.ClickEvent;
import hr.ja.fr.layout.LayoutPage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Route("/")
@LayoutPage(DemoLayout.class)
public class DemoPage extends Page {

    BsModal modal;

    public DemoPage() {

        modal = new BsModal(new Div("Ovo jew modal"));

        BsButton btn = new BsButton("show modal");
        btn.addClickListener(new ButtonClickListener<Button>() {

            @Override
            public void onClick(ClickEvent<Button> event)
			{

				log.debug("demo");
                modal.show();
            }
        });
        add(btn);

    }

}

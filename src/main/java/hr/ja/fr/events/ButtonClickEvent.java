package hr.ja.fr.events;

import hr.ja.fr.elements.Button;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ButtonClickEvent extends ClickEvent<Button> {

	@Getter
	private Button source;

}

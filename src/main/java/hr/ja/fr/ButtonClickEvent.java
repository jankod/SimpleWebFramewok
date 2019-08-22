package hr.ja.fr;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ButtonClickEvent extends ClickEvent<Button>{

	@Getter
	private Button source;

}

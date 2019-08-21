package hr.ja.fr;

public class Button extends MyTag implements ClickNotifier<Button>{

	public Button(String text) {	
		super("button");
		text(text);
	}


}
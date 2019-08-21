package hr.ja.fr;

public interface ClickNotifier<T extends MyTag> {

	public default void addClickListener(ButtonClickListener<T> l) {
		
		MyTag myTag = (MyTag)this;
		myTag.enshureId();
		ClickUtil.addListener(myTag, (TagEventListener) l);
	}

}

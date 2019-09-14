package hr.ja.fr;

public interface ClickNotifier<T extends MyTag> {

	public default void addClickListener(ButtonClickListener<T> listener) {
		MyTag myTag = (MyTag)this;
//		myTag.enshureId();
		ClickUtil.addListener(myTag, (TagEventListener) listener);
	}

}

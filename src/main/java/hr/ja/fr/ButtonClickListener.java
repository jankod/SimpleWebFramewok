package hr.ja.fr;

public interface ButtonClickListener<T extends MyTag> extends TagEventListener {

    public void onClick(ClickEvent<T> event);

}

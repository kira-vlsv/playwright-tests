package otus.components.popups;

public interface IPopup<T> {

    T popupShouldBeVisible(String... values);

    void popupShouldNotBeVisible(String... values);
}

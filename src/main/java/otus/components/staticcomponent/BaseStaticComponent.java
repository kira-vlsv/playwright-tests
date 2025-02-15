package otus.components.staticcomponent;

import com.microsoft.playwright.Page;
import otus.components.BaseComponent;

public abstract class BaseStaticComponent<T> extends BaseComponent<T> {

    public BaseStaticComponent(Page page) {
        super(page);
    }
}

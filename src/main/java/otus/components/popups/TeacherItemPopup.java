package otus.components.popups;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import otus.annotations.components.Component;
import otus.components.BaseComponent;

@Component("css:#__PORTAL__ > div.fade-enter-done")
public class TeacherItemPopup extends BaseComponent<TeacherItemPopup> implements IPopup<TeacherItemPopup> {

    public TeacherItemPopup(Page page) {
        super(page);
    }

    public TeacherItemPopup popupShouldBeVisible(String... values) {
        Locator popupLocator = getComponentEntity();
        popupLocator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(5000));
        assertThat(popupLocator.isVisible())
                .as("Popup should be visible")
                .isTrue();
        return this;
    }

    @Override
    public void popupShouldNotBeVisible(String... values) {
        Locator popupLocator = getComponentEntity();
        popupLocator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.HIDDEN)
                .setTimeout(5000));
        assertThat(popupLocator.isVisible())
                .as("Popup should not be visible")
                .isFalse();
    }

    public TeacherItemPopup verifyTeacherName(String expectedName) {
        String actualName = getTeacherName();
        assertThat(actualName)
                .as("Verify teacher's name in popup")
                .isEqualTo(expectedName);
        return this;
    }

    public TeacherItemPopup clickNextButton() {
        String currentName = getTeacherName();
        getComponentEntity().locator("button").all().getLast().click();
        String newName = getTeacherName();
        assertThat(newName)
                .as("Checking name has changed after pressing the '>' button")
                .isNotEqualTo(currentName);
        return this;
    }

    public TeacherItemPopup clickPreviousButton() {
        String currentName = getTeacherName();
        getComponentEntity().locator("button").nth(1).click();
        String newName = getTeacherName();
        assertThat(newName)
                .as("Checking name has changed after pressing the '<' button")
                .isNotEqualTo(currentName);
        return this;
    }

    public String getTeacherName() {
        return getComponentEntity().locator(".swiper-slide-active h3").innerText();
    }
}

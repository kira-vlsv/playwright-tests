package otus.pages;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import otus.annotations.Path;

@Path("/custom_courses")
public class CourseLandingPage extends BasePage<CourseLandingPage> {

    public CourseLandingPage(Page page) {
        super(page);
    }

    private final Locator courseCategoriesSection = page.locator("#rec512235354");

    public CourseLandingPage verifyTitle(String name) {
        assertThat(page.title())
                .as("Page should be displayed")
                .isEqualTo(name);
        return this;
    }

    public CourseLandingPage verifyTrainingDirectionsDisplayed() {
        assertThat(courseCategoriesSection.isVisible())
                .as("Catalog categories are displayed on the page")
                .isTrue();
        return this;
    }

    public void clickOnCourseCategoryByIndex(int index) {
        courseCategoriesSection.scrollIntoViewIfNeeded();
        courseCategoriesSection.locator("a[href]").nth(index).click();
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }
}

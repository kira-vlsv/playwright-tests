package otus.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import otus.annotations.Path;

@Path("/uslugi-kompaniyam")
public class CompanyServicePage extends BasePage<CompanyServicePage> {

    public CompanyServicePage(Page page) {
        super(page);
    }

    public Page clickOnDevelopCourseDetailsButton() {
        Page newPage = page.context().waitForPage(() -> page.click("button:has-text('Подробнее')"));
        newPage.waitForLoadState(LoadState.DOMCONTENTLOADED);
        return newPage;
    }
}

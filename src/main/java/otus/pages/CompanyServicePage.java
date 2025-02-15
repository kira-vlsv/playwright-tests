package otus.pages;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import otus.annotations.Path;

@Path("/uslugi-kompaniyam")
public class CompanyServicePage extends BasePage<CompanyServicePage> {

    public CompanyServicePage(Page page) {
        super(page);
    }

    public void clickOnDevelopCourseDetailsButton() {
        BrowserContext context = page.context();
        Page newPage = context.waitForPage(() -> page.click("button:has-text('Подробнее')"));
        newPage.waitForLoadState(LoadState.DOMCONTENTLOADED);
        newPage.bringToFront();
    }
}

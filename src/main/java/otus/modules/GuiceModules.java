package otus.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import otus.components.popups.TeacherItemPopup;
import otus.components.staticcomponent.CategorySideFilter;
import otus.components.staticcomponent.DurationSideFilter;
import otus.components.staticcomponent.LevelSideFilter;
import otus.factory.BrowserFactory;
import otus.pages.CatalogCoursesPage;
import otus.pages.CompanyServicePage;
import otus.pages.CourseLandingPage;
import otus.pages.CoursePage;

public class GuiceModules extends AbstractModule {

    public GuiceModules() {
        Playwright playwright = Playwright.create();
        BrowserFactory browserFactory = new BrowserFactory(playwright);
        Browser browser = browserFactory.create();
        BrowserContext browserContext = browser.newContext();
        this.page = browserContext.newPage();
    }

    private Page page;

    @Singleton
    @Provides
    public CoursePage getCoursePage() {
        return new CoursePage(page);
    }

    @Singleton
    @Provides
    public TeacherItemPopup getTeacherItemPopup() {
        return new TeacherItemPopup(page);
    }

    @Singleton
    @Provides
    public CatalogCoursesPage getCatalogCoursesPage() {
        return new CatalogCoursesPage(page);
    }

    @Singleton
    @Provides
    public CategorySideFilter getCategorySideFilter() {
        return new CategorySideFilter(page);
    }

    @Singleton
    @Provides
    public LevelSideFilter getLevelSideFilter() {
        return new LevelSideFilter(page);
    }

    @Singleton
    @Provides
    public DurationSideFilter getDurationSideFilter() {
        return new DurationSideFilter(page);
    }

    @Singleton
    @Provides
    public CompanyServicePage getCompanyServicePage() {
        return new CompanyServicePage(page);
    }

    @Singleton
    @Provides
    public CourseLandingPage getCourseLandingPage() {
        return new CourseLandingPage(page);
    }

}

package otus.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import otus.components.popups.TeacherItemPopup;
import otus.components.staticcomponent.CategorySideFilter;
import otus.components.staticcomponent.DurationSideFilter;
import otus.components.staticcomponent.LevelSideFilter;
import otus.factory.BrowserFactory;
import otus.pages.CatalogCoursesPage;
import otus.pages.CompanyServicePage;
import otus.pages.CourseLandingPage;
import otus.pages.CoursePage;
import otus.pages.SubscriptionPage;
import otus.pages.SubscriptionPaymentPage;

public class GuiceModules extends AbstractModule {

    private BrowserContext browserContext;
    private PageProvider pageProvider;

    public GuiceModules() {
        Playwright playwright = Playwright.create();
        BrowserFactory browserFactory = new BrowserFactory(playwright);
        Browser browser = browserFactory.create();
        this.browserContext = browser.newContext();

        browserContext.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSources(true).setSnapshots(true));
        this.pageProvider = new PageProvider(browserContext);
    }

    @Singleton
    @Provides
    public PageProvider getPageProvider() {
        return pageProvider;
    }

    @Singleton
    @Provides
    public BrowserContext getBrowserContext() {
        return browserContext;
    }

    @Singleton
    @Provides
    public CoursePage getCoursePage() {
        return new CoursePage(getPageProvider().getCurrentPage());
    }

    @Singleton
    @Provides
    public TeacherItemPopup getTeacherItemPopup() {
        return new TeacherItemPopup(getPageProvider().getCurrentPage());
    }

    @Singleton
    @Provides
    public CatalogCoursesPage getCatalogCoursesPage() {
        return new CatalogCoursesPage(getPageProvider().getCurrentPage());
    }

    @Singleton
    @Provides
    public CategorySideFilter getCategorySideFilter() {
        return new CategorySideFilter(getPageProvider().getCurrentPage());
    }

    @Singleton
    @Provides
    public LevelSideFilter getLevelSideFilter() {
        return new LevelSideFilter(getPageProvider().getCurrentPage());
    }

    @Singleton
    @Provides
    public DurationSideFilter getDurationSideFilter() {
        return new DurationSideFilter(getPageProvider().getCurrentPage());
    }

    @Singleton
    @Provides
    public CompanyServicePage getCompanyServicePage() {
        return new CompanyServicePage(getPageProvider().getCurrentPage());
    }

    @Singleton
    @Provides
    public CourseLandingPage getCourseLandingPage() {
        return new CourseLandingPage(getPageProvider().getCurrentPage());
    }

    @Singleton
    @Provides
    public SubscriptionPage getSubscriptionPage() {
        return new SubscriptionPage(getPageProvider().getCurrentPage());
    }

    @Singleton
    @Provides
    public SubscriptionPaymentPage getSubscriptionPaymentPage() {
        return new SubscriptionPaymentPage(getPageProvider().getCurrentPage());
    }


}

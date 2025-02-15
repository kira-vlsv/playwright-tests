package otus.factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {

    private static final String BROWSER_NAME = System.getProperty("browser").trim().replaceAll("[^a-zA-Z]", "").toLowerCase();
    private static final String BROWSER_VERSION = System.getProperty("browserVersion");

    private Playwright playwright;

    public BrowserFactory(Playwright playwright) {
        this.playwright = playwright;
    }

    public Browser create() {
        switch (BROWSER_NAME) {
            case "chrome":
                return this.playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        }
        return null;
    }
}

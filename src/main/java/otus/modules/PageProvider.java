package otus.modules;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

public class PageProvider {

    private Page currentPage;

    public PageProvider(BrowserContext browserContext) {
        this.currentPage = browserContext.newPage();
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void updatePage(Page newPage) {
        if (currentPage != null) {
            currentPage.close();
        }
        this.currentPage = newPage;
    }
}

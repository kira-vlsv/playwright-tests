package otus.pages;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import otus.annotations.Path;

@Path("/subscription")
public class SubscriptionPage extends BasePage<SubscriptionPage> {
    public SubscriptionPage(Page page) {
        super(page);
    }

    private final Locator subscriptionSection = page.locator("section:has(h2:has-text('Варианты подписки'))");

    public SubscriptionPage verifySubscriptionOptionsDisplayed() {
        subscriptionSection.scrollIntoViewIfNeeded();
        assertThat(subscriptionSection.isVisible())
                .as("Subscription options are visible")
                .isTrue();
        return this;
    }

    public SubscriptionPage expandSubDetailsByName(String subName) {
        subscriptionSection.locator(String.format("h4:has-text('%s')", subName))
                .locator("xpath=ancestor::div[2]")
                .locator("button:has-text('Подробнее')").click();
        return this;
    }

    public SubscriptionPage collapseSubDetailsByName(String subName) {
        subscriptionSection.locator(String.format("h4:has-text('%s')", subName))
                .locator("xpath=ancestor::div[2]")
                .locator("button:has-text('Свернуть')").click();
        return this;
    }

    public SubscriptionPage verifySubDetailsExpanded(String subName) {
        var collapseBtn = subscriptionSection.locator(String.format("h4:has-text('%s')", subName))
                .locator("xpath=ancestor::div[2]")
                .locator("button:has-text('Свернуть')");
        assertThat(collapseBtn.isVisible())
                .as("Subscription details is expanded")
                .isTrue();
        return this;
    }

    public void clickOnBuySubBtnByName(String subName) {
        subscriptionSection.scrollIntoViewIfNeeded();
        subscriptionSection.locator(String.format("h4:text-is('%s')", subName))
                .locator("xpath=ancestor::div[2]")
                .locator("button:text-is('Купить')").click();
    }


}

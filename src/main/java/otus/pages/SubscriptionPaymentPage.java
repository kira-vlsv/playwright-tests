package otus.pages;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.microsoft.playwright.Page;
import otus.annotations.Path;

@Path("/lk/payment-2/subscription/1398")
public class SubscriptionPaymentPage extends BasePage<SubscriptionPaymentPage> {
    public SubscriptionPaymentPage(Page page) {
        super(page);
    }

    public SubscriptionPaymentPage verifyPaymentPageDisplayed() {
        assertThat(page.locator("h1:has-text('Выберите способ оплаты')").isVisible())
                .as("Page is displayed")
                .isTrue();
        return this;
    }

    public Integer getCourseCost() {
        String price = page.locator("span[class^='styles__PriceContainer']").allInnerTexts().getFirst();
        return Integer.parseInt(price);
    }
}

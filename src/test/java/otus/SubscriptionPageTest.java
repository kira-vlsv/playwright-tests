package otus;

import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import otus.annotations.UITest;
import otus.pages.SubscriptionPage;
import otus.pages.SubscriptionPaymentPage;

@UITest
public class SubscriptionPageTest {

    @Inject
    private SubscriptionPage subscriptionPage;

    @Inject
    private SubscriptionPaymentPage subPaymentPage;

    @Test
    public void testSubscriptionFlow() {
        subscriptionPage.open()
                .verifySubscriptionOptionsDisplayed()
                .expandSubDetailsByName("Standard")
                .verifySubDetailsExpanded("Standard")
                .collapseSubDetailsByName("Standard")
                .clickOnBuySubBtnByName("Standard");
    }
}

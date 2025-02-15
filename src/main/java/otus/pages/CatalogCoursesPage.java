package otus.pages;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import otus.annotations.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Path("/catalog/courses")
public class CatalogCoursesPage extends BasePage<CatalogCoursesPage> {

    public CatalogCoursesPage(Page page) {
        super(page);
    }

    private final Locator coursesSection = page.locator("section:has(h1)");
    private final List<Locator> courseItems = page.locator("section:has(h1) a[href*='lessons']").all();

    public CatalogCoursesPage verifyCoursesDurationInRange(int min, int max) {
        courseItems.forEach(item -> {
            String durationText = getCourseDuration(item);
            Pattern pattern = Pattern.compile("(\\d+)\\s+месяцев");
            Matcher matcher = pattern.matcher(durationText);
            int duration = Integer.parseInt(matcher.group(1));
            assertThat(duration)
                    .as("Verify that course duration %d is in range [%d, %d]", duration, min, max)
                    .isBetween(min, max);
        });
        return this;
    }

    public String getCourseDuration(Locator courseItem) {
        return courseItem.allInnerTexts().stream().filter(t -> t.contains("месяц")).findFirst().orElseThrow();
    }

    public List<String> getCurrentCatalogState() {
        return coursesSection.locator("h6").allInnerTexts();
    }

    public CatalogCoursesPage verifyCoursesListChanged(List<String> previousTitles) {
        page.waitForCondition(() -> !getCurrentCatalogState().equals(previousTitles));
        return this;
    }

    public CatalogCoursesPage resetFilters() {
        List<String> currentCourseTitles = getCurrentCatalogState();
        page.locator("button:has-text('Очистить фильтры')").click();
        page.waitForCondition(() -> !getCurrentCatalogState().equals(currentCourseTitles));
        return this;
    }
}

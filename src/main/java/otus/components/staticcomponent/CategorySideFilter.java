package otus.components.staticcomponent;

import static org.assertj.core.api.Assertions.assertThat;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import otus.annotations.components.Component;
import otus.constants.CourseCategory;
import java.util.ArrayList;
import java.util.List;

@Component("xpath://p[text() = 'Направление']/ancestor::div[2]")
public class CategorySideFilter extends BaseStaticComponent<CategorySideFilter> {

    public CategorySideFilter(Page page) {
        super(page);
    }

    public List<Locator> getCategories() {
        return getComponentEntity().locator("xpath=.//div[@value]").all();
    }

    public List<CourseCategory> getSelectedCategories() {
        List<CourseCategory> selectedCategories = new ArrayList<>();
        for (Locator category : getCategories()) {
            String value = category.getAttribute("value");
            if (Boolean.parseBoolean(value)) {
                String label = category.innerText().trim();
                selectedCategories.add(CourseCategory.fromLabel(label));
            }
        }
        return selectedCategories;
    }

    public CategorySideFilter selectCourseCategory(CourseCategory courseCategory) {
        getComponentEntity().scrollIntoViewIfNeeded();
        Locator category = getCategories().stream().filter(l -> l.innerText().contains(courseCategory.getLabel())).findFirst().orElseThrow();
        category.locator("input[type=checkbox]").click();
        return this;
    }

    public CategorySideFilter checkSelectedCategories(List<CourseCategory> expectedCategories) {
        assertThat(getSelectedCategories())
                .as("Categories do not match")
                .isEqualTo(expectedCategories);
        return this;
    }
}

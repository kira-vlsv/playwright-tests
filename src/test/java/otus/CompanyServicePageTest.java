package otus;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.junit.jupiter.api.Test;
import otus.annotations.UITest;
import otus.components.staticcomponent.CategorySideFilter;
import otus.constants.CourseCategory;
import otus.modules.PageProvider;
import otus.pages.CompanyServicePage;
import otus.pages.CourseLandingPage;
import java.util.List;

@UITest
public class CompanyServicePageTest {

    @Inject
    private CompanyServicePage companyServicePage;

    @Inject
    private Provider<CategorySideFilter> categorySideFilterProvider;

    @Inject
    private Provider<CourseLandingPage> courseLandingPageProvider;

    @Inject
    private PageProvider pageProvider;

    @Test
    public void testCustomCoursesLandingFlow() {
        companyServicePage.open();
        var newTab = companyServicePage.clickOnDevelopCourseDetailsButton();
        pageProvider.updatePage(newTab);
        CourseLandingPage courseLandingPage = courseLandingPageProvider.get();
        courseLandingPage.verifyTitle("Разработка индивидуальных программ обучения для бизнеса")
                .verifyTrainingDirectionsDisplayed()
                .clickOnCourseCategoryByIndex(2);
        CategorySideFilter categorySideFilter = categorySideFilterProvider.get();
        categorySideFilter.checkSelectedCategories(List.of(CourseCategory.INFRASTRUCTURE));
    }
}

package otus;

import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import otus.annotations.UITest;
import otus.components.staticcomponent.CategorySideFilter;
import otus.constants.CourseCategory;
import otus.pages.CompanyServicePage;
import otus.pages.CourseLandingPage;
import java.util.List;

@UITest
public class CompanyServicePageTest {

    @Inject
    private CompanyServicePage companyServicePage;

    @Inject
    private CategorySideFilter categorySideFilter;

    @Inject
    private CourseLandingPage courseLandingPage;

    @Test
    public void testCustomCoursesLandingFlow() {
        companyServicePage.open();
        companyServicePage.clickOnDevelopCourseDetailsButton();
        courseLandingPage.verifyTitle("Разработка индивидуальных программ обучения для бизнеса")
                .verifyTrainingDirectionsDisplayed()
                .clickOnCourseCategoryByIndex(2);
        categorySideFilter.checkSelectedCategories(List.of(CourseCategory.INFRASTRUCTURE));
    }
}

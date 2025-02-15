package otus;

import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import otus.annotations.UITest;
import otus.components.staticcomponent.CategorySideFilter;
import otus.components.staticcomponent.DurationSideFilter;
import otus.components.staticcomponent.LevelSideFilter;
import otus.constants.CourseCategory;
import otus.constants.CourseLevel;
import otus.pages.CatalogCoursesPage;
import java.util.List;

@UITest
public class CatalogCoursesPageTest {

    @Inject
    private CatalogCoursesPage catalogCoursesPage;

    @Inject
    private CategorySideFilter categorySideFilter;

    @Inject
    private LevelSideFilter levelSideFilter;

    @Inject
    private DurationSideFilter durationSideFilter;

    @Test
    public void testCoursesCatalogFilterFlow() {
        catalogCoursesPage.open();
        categorySideFilter.checkSelectedCategories(List.of(CourseCategory.ALL));
        levelSideFilter.checkSelectedLevels(List.of(CourseLevel.ANY));
        durationSideFilter.selectDurationRange(3, 6);
        catalogCoursesPage.verifyCoursesDurationInRange(3, 6);
        var catalogListText = catalogCoursesPage.getCurrentCatalogState();
        categorySideFilter.selectCourseCategory(CourseCategory.ARCHITECTURE);
        catalogCoursesPage.verifyCoursesListChanged(catalogListText)
                .resetFilters();
    }
}

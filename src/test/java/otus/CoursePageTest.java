package otus;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import otus.annotations.UITest;
import otus.components.popups.TeacherItemPopup;
import otus.pages.CoursePage;

@UITest
public class CoursePageTest {

    @Inject
    private CoursePage coursePage;

    @Inject
    private TeacherItemPopup teacherItemPopup;

    @Test
    public void testTeacherPopupNavigationFlow() {
        var teacherName = coursePage.open("clickhouse")
                .verifyTeacherListDisplayed()
                .scrollTeacherListUsingDragAndDrop()
                .verifyTeacherListScrolled()
                .clickTeacherItemByName(3);
        teacherItemPopup.popupShouldBeVisible()
                .verifyTeacherName(teacherName)
                .clickPreviousButton()
                .clickNextButton()
                .verifyTeacherName(teacherName);
    }
}

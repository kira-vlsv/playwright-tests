package otus.pages;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import otus.annotations.PathTemplate;

@PathTemplate("/lessons/%s")
public class CoursePage extends BasePage<CoursePage> {

    public CoursePage(Page page) {
        super(page);
    }

    String initialTeacherSwiperTransform;

    private final Locator teacherSection = page.locator("section:has(h2:has-text('Преподаватели'))");
    private static final String TEACHER_ITEMS_LOCATOR = "div.swiper-slide";

    public CoursePage verifyTeacherListDisplayed() {
        Locator teacherItems = teacherSection.locator(TEACHER_ITEMS_LOCATOR);
        teacherItems.first().waitFor(new Locator.WaitForOptions().setTimeout(5000));
        assertThat(teacherItems.count())
                .as("Verify teacher items are displayed")
                .isGreaterThan(0);
        return this;
    }

    public CoursePage scrollTeacherListUsingDragAndDrop() {
        teacherSection.scrollIntoViewIfNeeded();
        initialTeacherSwiperTransform = captureSwiperWrapperTransform();

        BoundingBox box = teacherSection.boundingBox();
        assertThat(box).isNotNull();
        double startX = box.x + box.width / 2;
        double startY = box.y + box.height / 2;
        double endX = startX - 200;

        page.mouse().move(startX, startY);
        page.mouse().down();
        page.mouse().move(endX, startY, new Mouse.MoveOptions().setSteps(10));
        page.mouse().up();

        return this;
    }

    public CoursePage verifyTeacherListScrolled() {
        String newTransform = captureSwiperWrapperTransform();
        assertThat(newTransform)
                .as("Verify section is scrolled")
                .isNotEqualTo(initialTeacherSwiperTransform);
        return this;
    }

    private String captureSwiperWrapperTransform() {
        return (String) teacherSection.locator("div.swiper-wrapper")
                .evaluate("el => window.getComputedStyle(el).transform");
    }

    public String clickTeacherItemByName(Integer index) {
        String teacherName = getTeacherName(index);
        teacherSection.locator(TEACHER_ITEMS_LOCATOR).filter(
                new Locator.FilterOptions().setHasText(teacherName)).click();
        return teacherName;
    }

    public String getTeacherName(int index) {
        return teacherSection.locator(TEACHER_ITEMS_LOCATOR).nth(index).innerText().split("\n")[0];
    }
}

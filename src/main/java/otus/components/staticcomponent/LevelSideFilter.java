package otus.components.staticcomponent;

import static org.assertj.core.api.Assertions.assertThat;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import otus.annotations.components.Component;
import otus.constants.CourseLevel;
import java.util.ArrayList;
import java.util.List;

@Component("xpath://p[text() = 'Уровень']/ancestor::div[2]")
public class LevelSideFilter extends BaseStaticComponent<LevelSideFilter> {

    public LevelSideFilter(Page page) {
        super(page);
    }

    public List<Locator> getLevels() {
        return getComponentEntity().locator("xpath=.//div[@value]").all();
    }

    public List<CourseLevel> getSelectedLevels() {
        List<CourseLevel> selectedLevels = new ArrayList<>();
        for (Locator level : getLevels()) {
            String value = level.getAttribute("value");
            if (Boolean.parseBoolean(value)) {
                String label = level.innerText().trim();
                selectedLevels.add(CourseLevel.fromLabel(label));
            }
        }
        return selectedLevels;
    }

    public LevelSideFilter checkSelectedLevels(List<CourseLevel> expectedLevels) {
        assertThat(getSelectedLevels())
                .as("Levels do not match")
                .isEqualTo(expectedLevels);
        return this;
    }
}

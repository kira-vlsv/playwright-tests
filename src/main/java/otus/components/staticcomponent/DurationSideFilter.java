package otus.components.staticcomponent;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import otus.annotations.components.Component;

@Component("xpath://p[text() = 'Продолжительность']/ancestor::div[2]")
public class DurationSideFilter extends BaseStaticComponent<DurationSideFilter> {
    public DurationSideFilter(Page page) {
        super(page);
    }

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 15;

    private final Locator leftHandle = getComponentEntity().locator("div[role='slider']").first();
    private final Locator rightHandle = getComponentEntity().locator("div[role='slider']").nth(1);

    public DurationSideFilter selectDurationRange(int min, int max) {
        getComponentEntity().scrollIntoViewIfNeeded();
        validateRange(min, max);
        Locator track = leftHandle.locator("xpath=..");
        BoundingBox trackBox = track.boundingBox();

        double pxPerMonth = trackBox.width / (MAX_VALUE - MIN_VALUE);

        double leftTargetX = trackBox.x + (min - MIN_VALUE) * pxPerMonth;
        double rightTargetX = trackBox.x + (max - MIN_VALUE) * pxPerMonth;
        double centerY = trackBox.y + trackBox.height / 2;

        dragSliderHandle(leftHandle, leftTargetX, centerY);
        dragSliderHandle(rightHandle, rightTargetX, centerY);

        return this;
    }

    private void dragSliderHandle(Locator handle, double targetX, double centerY) {
        BoundingBox box = handle.boundingBox();

        double startX = box.x + box.width / 2;
        double startY = box.y + box.height / 2;

        page.mouse().move(startX, startY);
        page.mouse().down();
        page.mouse().move(targetX, centerY, new Mouse.MoveOptions().setSteps(5));
        page.mouse().up();
    }

    private void validateRange(int min, int max) {
        if (min < MIN_VALUE || max > MAX_VALUE || min > max) {
            throw new IllegalArgumentException(
                    String.format("Invalid range: %d - %d (valid range: [%d..%d])",
                            min, max, MIN_VALUE, MAX_VALUE));
        }
    }

    public int[] getSelectedDurationRange() {
        int leftVal = Integer.parseInt(leftHandle.getAttribute("aria-valuenow"));
        int rightVal = Integer.parseInt(rightHandle.getAttribute("aria-valuenow"));
        return new int[]{leftVal, rightVal};
    }
}

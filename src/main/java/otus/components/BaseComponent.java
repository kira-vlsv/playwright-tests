package otus.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import otus.annotations.components.Component;
import otus.annotations.components.ComponentTemplate;
import otus.exceptions.InvalidComponentSelectorException;
import otus.exceptions.InvalidPathParametersException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseComponent<T> {

    protected final Page page;

    public BaseComponent(Page page) {
        this.page = page;
    }

    public String getFormattedSelector(String... values) {
        ComponentTemplate componentTemplate = getClass().getAnnotation(ComponentTemplate.class);
        if (componentTemplate != null) {
            int expectedParameters = countNumberOfParameters(componentTemplate.value());
            if (values.length != expectedParameters) {
                throw new InvalidPathParametersException(expectedParameters, values.length);
            }
            return String.format(componentTemplate.value(), (Object[]) values);
        }

        Component component = getClass().getAnnotation(Component.class);
        if (component != null) {
            return component.value();
        }

        throw new InvalidComponentSelectorException("No valid component annotation found on class: " + getClass().getName());
    }

    private Locator parseSelector(String selector) {
        Pattern pattern = Pattern.compile("^([^:]+):(.+)$");
        Matcher matcher = pattern.matcher(selector);

        if (matcher.matches()) {
            String type = matcher.group(1);
            String value = matcher.group(2);

            return switch (type) {
                case "css" -> page.locator(value);
                case "xpath" -> page.locator("xpath=" + value);
                case "id" -> page.locator("#" + value);
                case "name" -> page.locator("[name='" + value + "']");
                default -> throw new InvalidComponentSelectorException("Unsupported selector type: " + type);
            };
        } else {
            throw new InvalidComponentSelectorException("Invalid selector format: " + selector);
        }
    }

    public Locator getComponentEntity(String... values) {
        String selector = getFormattedSelector(values);
        return parseSelector(selector);
    }

    public int countNumberOfParameters(String template) {
        Pattern pattern = Pattern.compile("%s");
        Matcher matcher = pattern.matcher(template);
        int expectedParameters = 0;
        while (matcher.find()) {
            expectedParameters++;
        }
        return expectedParameters;
    }
}

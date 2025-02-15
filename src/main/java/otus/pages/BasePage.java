package otus.pages;

import com.microsoft.playwright.Page;
import otus.annotations.Path;
import otus.annotations.PathTemplate;
import otus.exceptions.InvalidPathException;
import otus.exceptions.InvalidPathParametersException;
import otus.exceptions.MissingPathTemplateException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BasePage<T> {

    protected Page page;

    private final String baseUrl = System.getProperty("baseUrl").replaceAll("/$", "");

    public BasePage(Page page) {
        this.page = page;
    }

    public T open() {
        page.navigate(baseUrl + getPath());
        return (T) this;
    }

    public T open(String... path) {
        page.navigate(baseUrl + getPath(path));
        return (T) this;
    }

    private String getPath() {
        Path path = getClass().getAnnotation(Path.class);
        if (path == null) {
            throw new InvalidPathException();
        }
        return path.value().replaceAll("^(?!/)", "/");
    }

    private String getPath(String... path) {
        PathTemplate pathTemplate = getClass().getAnnotation(PathTemplate.class);
        if (pathTemplate == null) {
            throw new MissingPathTemplateException();
        }
        String template = pathTemplate.value();
        int expectedParameters = countNumberOfParameters(template);
        if (path.length != expectedParameters) {
            throw new InvalidPathParametersException(expectedParameters, path.length);
        }
        return String.format(template, (Object[]) path);
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

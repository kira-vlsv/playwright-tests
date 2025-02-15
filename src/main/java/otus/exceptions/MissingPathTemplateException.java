package otus.exceptions;

public class MissingPathTemplateException extends RuntimeException {

    public MissingPathTemplateException() {
        super("PathTemplate annotation is missing");
    }
}

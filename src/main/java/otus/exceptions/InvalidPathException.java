package otus.exceptions;

public class InvalidPathException extends RuntimeException {

    public InvalidPathException() {
        super("Page's path can not be null");
    }

}

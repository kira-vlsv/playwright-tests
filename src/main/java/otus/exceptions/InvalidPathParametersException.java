package otus.exceptions;

public class InvalidPathParametersException extends RuntimeException {

    public InvalidPathParametersException(int expected, int actual) {
        super("Incorrect number of path parameters. Expected " + expected + ", but got " + actual);
    }
}

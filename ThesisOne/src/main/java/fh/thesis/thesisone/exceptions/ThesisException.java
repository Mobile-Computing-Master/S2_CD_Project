package fh.thesis.thesisone.exceptions;

public class ThesisException extends RuntimeException {
    public ThesisException() {
    }

    public ThesisException(String message) {
        super(message);
    }

    public ThesisException(String message, Throwable cause) {
        super(message, cause);
    }
}

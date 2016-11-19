package pl.pollub.service.model;

public enum ErrorType {

    GENERAL(0),
    MOVIE_NOT_FOUND(1),
    REQUEST_PER_SECOND_EXCEEDED(2);

    int code;

    ErrorType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

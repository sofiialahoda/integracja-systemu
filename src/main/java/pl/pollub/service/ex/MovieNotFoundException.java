package pl.pollub.service.ex;

public class MovieNotFoundException extends Exception {
    private String id;

    public MovieNotFoundException(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

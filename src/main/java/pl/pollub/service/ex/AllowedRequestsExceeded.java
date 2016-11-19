package pl.pollub.service.ex;

public class AllowedRequestsExceeded extends RuntimeException {

    private String host;

    public AllowedRequestsExceeded(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }
}

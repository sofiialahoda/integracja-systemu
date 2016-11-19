package pl.pollub.service.config;

import java.util.concurrent.TimeUnit;

public class DDosConfig {

    public static final long ALLOWED_REQUESTS_PER_SECOND = 5;
    public static final long BLACK_LIST_TIMEOUT = 5;
    public static final TimeUnit BLACK_LIST_TIME_UNIT = TimeUnit.SECONDS;

}
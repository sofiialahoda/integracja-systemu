package pl.pollub.service.ddos;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pl.pollub.service.config.DDosConfig;
import pl.pollub.service.ex.AllowedRequestsExceeded;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class DDosRequestInterceptor extends HandlerInterceptorAdapter {

    private final Cache<String, Long> blackList = CacheBuilder.newBuilder()
            .expireAfterWrite(DDosConfig.BLACK_LIST_TIMEOUT, DDosConfig.BLACK_LIST_TIME_UNIT)
            .removalListener(new RemovalListener<String, Long>() {
                @Override
                public void onRemoval(RemovalNotification<String, Long> notification) {
                    String ip = notification.getKey();
                    requests.remove(ip);
                }
            }).build();

    private final Map<String, Integer> requests = new ConcurrentHashMap<>();

    public DDosRequestInterceptor() {
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(requests::clear, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getRequestURI().contains("requests")) {
            if (isAllowedRequestsExceeded(request))
                throw new AllowedRequestsExceeded(request.getRemoteHost());
            String ip = request.getRemoteHost();
            if (requests.containsKey(ip)) {
                requests.put(ip, requests.get(ip) + 1);
            } else {
                requests.put(ip, 1);
            }
        }
        return super.preHandle(request, response, handler);
    }

    private boolean isAllowedRequestsExceeded(ServletRequest request) {
        if (isBlackListMember(request)) {
            return true;
        }

        boolean result = requests.getOrDefault(request.getRemoteHost(), 0) >= DDosConfig.ALLOWED_REQUESTS_PER_SECOND;
        if (result) {
            addToBlackList(request);
        }

        return result;
    }

    private boolean isBlackListMember(ServletRequest request) {
        return blackList.getIfPresent(request.getRemoteHost()) != null;
    }

    private void addToBlackList(ServletRequest request) {
        blackList.put(request.getRemoteHost(), System.currentTimeMillis());
    }
}

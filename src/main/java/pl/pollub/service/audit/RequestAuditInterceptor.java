package pl.pollub.service.audit;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestAuditInterceptor extends HandlerInterceptorAdapter {

    private final RequestAuditService service;

    public RequestAuditInterceptor(RequestAuditService service) {
        this.service = service;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (!request.getRequestURI().contains("requests")) {
            service.insert(request, response.getStatus());
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}

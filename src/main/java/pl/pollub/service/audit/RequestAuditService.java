package pl.pollub.service.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollub.service.model.RequestAudit;
import pl.pollub.service.repository.RequestAuditRepository;

import javax.servlet.http.HttpServletRequest;

@Service
public class RequestAuditService {
    @Autowired
    private RequestAuditRepository repository;

    public RequestAudit insert(HttpServletRequest request, int statusCode) {
        RequestAudit audit = new RequestAudit();
        audit.setIp(request.getRemoteHost());
        audit.setPort(request.getRemotePort());
        audit.setSessionId(request.getRequestedSessionId());
        audit.setMethod(request.getMethod());
        audit.setUrl(request.getRequestURL().toString());
        audit.setParams(parseParams(request));
        audit.setTimestamp(System.currentTimeMillis());
        audit.setResponseCode(statusCode);
        return repository.save(audit);
    }

    private String parseParams(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        request.getParameterMap().keySet().forEach(name -> {
            String value = request.getParameter(name);
            builder.append(name).append("=").append(value).append("&");
        });
        String result = builder.toString();
        if (result.endsWith("&"))
            return result.substring(0, result.length() - 1);
        else
            return result;
    }
}

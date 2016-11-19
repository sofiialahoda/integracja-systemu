package pl.pollub.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pl.pollub.service.audit.RequestAuditInterceptor;
import pl.pollub.service.audit.RequestAuditService;
import pl.pollub.service.ddos.DDosRequestInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private RequestAuditService service;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DDosRequestInterceptor());
        registry.addInterceptor(new RequestAuditInterceptor(service));
        super.addInterceptors(registry);
    }
}

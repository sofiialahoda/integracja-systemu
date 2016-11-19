package pl.pollub.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.service.model.AuditList;
import pl.pollub.service.repository.RequestAuditRepository;

@RestController
public class RequestAuditController {

    @Autowired
    private RequestAuditRepository repository;

    @RequestMapping("/requests")
    public AuditList getAuditList() {
        return new AuditList(repository.findAll());
    }
}

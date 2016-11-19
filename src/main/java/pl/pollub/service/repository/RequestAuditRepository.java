package pl.pollub.service.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pollub.service.model.RequestAudit;

import java.util.List;

public interface RequestAuditRepository extends CrudRepository<RequestAudit, Long> {
    List<RequestAudit> findAll();
}

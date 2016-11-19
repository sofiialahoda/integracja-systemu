package pl.pollub.service.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "requests")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuditList implements Serializable {

    @XmlElement(name = "request")
    private List<RequestAudit> requests = new ArrayList<>();

    public AuditList() {
    }

    public AuditList(List<RequestAudit> requests) {
        this.requests = requests;
    }

    public List<RequestAudit> getRequests() {
        return requests;
    }

    public void setRequests(List<RequestAudit> requests) {
        this.requests = requests;
    }
}
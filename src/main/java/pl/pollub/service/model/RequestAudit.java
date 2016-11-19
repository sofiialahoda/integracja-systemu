package pl.pollub.service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class RequestAudit implements Serializable {

    @Id
    @XmlElement
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @XmlElement
    private String ip;
    @XmlElement
    private int port;
    @XmlElement
    private String sessionId;
    @XmlElement
    private String method;
    @XmlElement
    private String url;
    @XmlElement
    private String params;
    @XmlElement
    private long timestamp;
    @XmlElement
    private long responseCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(long responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        return "RequestAudit{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", url='" + url + '\'' +
                ", timestamp=" + timestamp +
                ", responseCode=" + responseCode +
                '}';
    }
}
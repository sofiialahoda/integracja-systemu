package pl.pollub.service.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.NONE)
public class Error implements Serializable {

    @XmlElement
    private int code;

    @XmlElement
    private ErrorType type;

    @XmlElement
    private String message;

    public Error() {
    }

    public Error(ErrorType type, String message) {
        this.code = type.getCode();
        this.type = type;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public ErrorType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}

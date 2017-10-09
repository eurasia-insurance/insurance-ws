package tech.lapsa.eurasia36.ws.jaxb.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

@XmlRootElement(name = "sendRequestResultShort")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlSendRequestResultShort implements Serializable {
    private static final long serialVersionUID = 4876311519054938134L;

    @XmlAttribute
    protected String message;

    public XmlSendRequestResultShort() {
    }

    public XmlSendRequestResultShort(String message) {
	this.message = message;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

}
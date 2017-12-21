package tech.lapsa.insurance.ws.jaxb.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

@XmlRootElement(name = "sendRequestResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlSendRequestResponse implements Serializable {
    private static final long serialVersionUID = 4876311519054938134L;

    @XmlAttribute
    protected String message;

    public XmlSendRequestResponse() {
    }

    public XmlSendRequestResponse(final String message) {
	this.message = message;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(final String message) {
	this.message = message;
    }

}
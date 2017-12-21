package tech.lapsa.insurance.ws.jaxb.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

@XmlRootElement(name = "sendRequestResponseFull")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlSendRequestResponseFull extends XmlSendRequestResponse {
    private static final long serialVersionUID = 1L;

    @XmlAttribute
    protected Integer requestId;

    public XmlSendRequestResponseFull() {
    }

    public XmlSendRequestResponseFull(final String message) {
	super(message);
    }

    public XmlSendRequestResponseFull(final String message, final Integer requestId) {
	super(message);
	this.requestId = requestId;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public Integer getRequestId() {
	return requestId;
    }

    public void setRequestId(final Integer requestId) {
	this.requestId = requestId;
    }
}

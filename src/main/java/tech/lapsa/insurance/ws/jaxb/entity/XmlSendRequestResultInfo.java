package tech.lapsa.insurance.ws.jaxb.entity;

import java.net.URI;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.builder.ToStringBuilder;

import tech.lapsa.java.jaxb.adapter.XmlURIAdapter;

@XmlRootElement(name = "sendRequestResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlSendRequestResultInfo extends XmlSendRequestResultShort {
    private static final long serialVersionUID = -5641687980684467014L;

    @XmlAttribute
    protected Integer requestId;

    @XmlAttribute
    protected String invoiceId;

    @XmlAttribute
    @XmlJavaTypeAdapter(XmlURIAdapter.class)
    protected URI paymentLink;

    public XmlSendRequestResultInfo() {
    }

    public XmlSendRequestResultInfo(String message, Integer requestId, String ebillId, URI paymentLink) {
	super(message);
	this.requestId = requestId;
	this.invoiceId = ebillId;
	this.paymentLink = paymentLink;
    }

    public XmlSendRequestResultInfo(String message, Integer requestId) {
	super(message);
	this.requestId = requestId;
    }

    public XmlSendRequestResultInfo(String message) {
	super(message);
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public Integer getRequestId() {
	return requestId;
    }

    public void setRequestId(Integer requestId) {
	this.requestId = requestId;
    }

    public String getInvoiceId() {
	return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
	this.invoiceId = invoiceId;
    }

    public URI getPaymentLink() {
	return paymentLink;
    }

    public void setPaymentLink(URI paymentLink) {
	this.paymentLink = paymentLink;
    }
}

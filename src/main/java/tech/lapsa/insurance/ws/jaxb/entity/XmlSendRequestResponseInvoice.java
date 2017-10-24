package tech.lapsa.insurance.ws.jaxb.entity;

import java.net.URI;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.builder.ToStringBuilder;

import tech.lapsa.java.jaxb.adapter.XmlURIAdapter;

@XmlRootElement(name = "sendRequestResponseInvoice")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlSendRequestResponseInvoice extends XmlSendRequestResponseFull {
    private static final long serialVersionUID = 1L;

    @XmlAttribute
    protected String invoiceId;

    @XmlAttribute
    @XmlJavaTypeAdapter(XmlURIAdapter.class)
    protected URI paymentLink;

    public XmlSendRequestResponseInvoice() {
    }

    public XmlSendRequestResponseInvoice(String message) {
	super(message);
    }

    public XmlSendRequestResponseInvoice(String message, Integer requestId) {
	super(message, requestId);
    }

    public XmlSendRequestResponseInvoice(String message, Integer requestId, String ebillId, URI paymentLink) {
	super(message, requestId);
	this.invoiceId = ebillId;
	this.paymentLink = paymentLink;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
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

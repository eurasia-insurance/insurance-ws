package tech.lapsa.insurance.ws.jaxb.entity;

import java.io.Serializable;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

import tech.lapsa.javax.validation.NotNullValue;

@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRequestInfo implements Serializable {
    private static final long serialVersionUID = 2218260293601031771L;

    @NotNullValue
    @Valid
    protected XmlRequesterInfo requester;

    @Valid
    protected XmlUTMInfo utm;

    public XmlRequestInfo() {
    }

    public XmlRequestInfo(final XmlRequesterInfo requester, final XmlUTMInfo utm) {
	this.requester = requester;
	this.utm = utm;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public XmlRequesterInfo getRequester() {
	return requester;
    }

    public void setRequester(final XmlRequesterInfo requester) {
	this.requester = requester;
    }

    public XmlUTMInfo getUtm() {
	return utm;
    }

    public void setUtm(final XmlUTMInfo utm) {
	this.utm = utm;
    }
}

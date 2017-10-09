package tech.lapsa.eurasia36.ws.jaxb.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

@XmlRootElement(name = "callbackRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlCallbackRequestInfo extends XmlRequestInfo {
    private static final long serialVersionUID = 4083775290786447946L;

    @XmlAttribute
    protected String comments;

    public XmlCallbackRequestInfo() {
    }

    public XmlCallbackRequestInfo(XmlRequesterInfo requester, XmlUTMInfo utm, String comments) {
	super(requester, utm);
	this.comments = comments;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public String getComments() {
	return comments;
    }

    public void setComments(String comments) {
	this.comments = comments;
    }
}

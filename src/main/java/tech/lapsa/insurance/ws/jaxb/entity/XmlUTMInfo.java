package tech.lapsa.insurance.ws.jaxb.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lapsa.validation.NotEmptyString;
import com.lapsa.validation.NotNullValue;

@XmlRootElement(name = "utm")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlUTMInfo implements Serializable {
    private static final long serialVersionUID = 3667018835983732101L;

    @XmlAttribute
    @NotNullValue
    @NotEmptyString
    protected String source;

    @XmlAttribute
    protected String medium;

    @XmlAttribute
    protected String campaign;

    @XmlAttribute
    protected String content;

    @XmlAttribute
    protected String term;

    public XmlUTMInfo() {
    }

    public XmlUTMInfo(String source, String medium, String campaign, String content, String term) {
	this.source = source;
	this.medium = medium;
	this.campaign = campaign;
	this.content = content;
	this.term = term;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public String getSource() {
	return source;
    }

    public void setSource(String source) {
	this.source = source;
    }

    public String getMedium() {
	return medium;
    }

    public void setMedium(String medium) {
	this.medium = medium;
    }

    public String getCampaign() {
	return campaign;
    }

    public void setCampaign(String campaign) {
	this.campaign = campaign;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public String getTerm() {
	return term;
    }

    public void setTerm(String term) {
	this.term = term;
    }
}

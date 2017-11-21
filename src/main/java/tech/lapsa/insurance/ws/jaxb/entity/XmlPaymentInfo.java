package tech.lapsa.insurance.ws.jaxb.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

@XmlRootElement(name = "payment")
@XmlAccessorType(XmlAccessType.FIELD)
@Deprecated
// TODO REFACT : Exclude XmlPaymentInfo from API
public class XmlPaymentInfo implements Serializable {
    private static final long serialVersionUID = -7070033829293894103L;

    @XmlAttribute
    protected String method;

    public XmlPaymentInfo() {
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    @Deprecated
    public XmlPaymentInfo(String method) {
	this.method = method;
    }

    @Deprecated
    public String getMethod() {
	return method;
    }

    @Deprecated
    public void setMethod(String method) {
	this.method = method;
    }
}

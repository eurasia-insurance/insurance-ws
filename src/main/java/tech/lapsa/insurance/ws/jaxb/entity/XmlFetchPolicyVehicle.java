package tech.lapsa.insurance.ws.jaxb.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

import tech.lapsa.javax.validation.NotNullValue;

@XmlRootElement(name = "fetchPolicyVehicle")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlFetchPolicyVehicle implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlAttribute
    @NotNullValue
    protected String regNumber;

    public XmlFetchPolicyVehicle() {
    }

    public XmlFetchPolicyVehicle(String regNumber) {
	this.regNumber = regNumber;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public String getRegNumber() {
	return regNumber;
    }

    public void setRegNumber(String regNumber) {
	this.regNumber = regNumber;
    }
}

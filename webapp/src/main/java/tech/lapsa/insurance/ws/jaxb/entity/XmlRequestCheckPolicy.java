package tech.lapsa.insurance.ws.jaxb.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import tech.lapsa.javax.validation.NotEmptyString;
import tech.lapsa.javax.validation.NotNullValue;

@XmlRootElement(name = "checkPolicyRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRequestCheckPolicy implements Serializable {

    private static final long serialVersionUID = 1L;

    // policyNumber

    @XmlAttribute
    @NotNullValue
    @NotEmptyString
    private String policyNumber;

    public String getPolicyNumber() {
	return policyNumber;
    }

    public void setPolicyNumber(final String policyNumber) {
	this.policyNumber = policyNumber;
    }

    // constructors

    public XmlRequestCheckPolicy() {
    }

    public XmlRequestCheckPolicy(final String policyNumber) {
	this.policyNumber = policyNumber;
    }

}

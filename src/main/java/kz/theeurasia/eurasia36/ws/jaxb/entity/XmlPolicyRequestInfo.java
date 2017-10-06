package kz.theeurasia.eurasia36.ws.jaxb.entity;

import java.io.Serializable;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lapsa.insurance.elements.InsuranceRequestType;
import com.lapsa.insurance.validation.ValidInsuranceRequestType;
import com.lapsa.validation.NotNullValue;

@XmlRootElement(name = "policyRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlPolicyRequestInfo extends XmlRequestInfo implements Serializable {
    private static final long serialVersionUID = -4291392430710344637L;

    @NotNullValue
    @Valid
    protected XmlPolicyInfo policy;

    @Valid
    protected XmlPaymentInfo payment;

    @NotNullValue
    @ValidInsuranceRequestType(permited = { InsuranceRequestType.UNCOMPLETE, InsuranceRequestType.EXPRESS })
    private InsuranceRequestType type;

    public XmlPolicyRequestInfo() {
    }

    public XmlPolicyRequestInfo(XmlRequesterInfo requester, XmlUTMInfo utm, XmlPolicyInfo policy,
	    XmlPaymentInfo payment, InsuranceRequestType type) {
	super(requester, utm);
	this.policy = policy;
	this.payment = payment;
	this.type = type;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public XmlPolicyInfo getPolicy() {
	return policy;
    }

    public void setPolicy(XmlPolicyInfo policy) {
	this.policy = policy;
    }

    public XmlPaymentInfo getPayment() {
	return payment;
    }

    public void setPayment(XmlPaymentInfo payment) {
	this.payment = payment;
    }

    public InsuranceRequestType getType() {
	return type;
    }

    public void setType(InsuranceRequestType type) {
	this.type = type;
    }
}

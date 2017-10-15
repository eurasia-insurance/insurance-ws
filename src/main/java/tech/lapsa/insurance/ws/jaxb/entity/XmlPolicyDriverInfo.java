package tech.lapsa.insurance.ws.jaxb.entity;

import java.io.Serializable;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lapsa.insurance.elements.InsuranceClassType;
import com.lapsa.insurance.elements.InsuredAgeClass;
import com.lapsa.insurance.elements.InsuredExpirienceClass;
import com.lapsa.insurance.validation.ValidInsuranceAgeClass;
import com.lapsa.insurance.validation.ValidInsuranceClassType;
import com.lapsa.insurance.validation.ValidInsuranceExpirienceClass;

import tech.lapsa.insurance.ws.jaxb.validator.PolicyDriverSettingsValid;
import tech.lapsa.javax.validation.NotNullValue;

@XmlRootElement(name = "policyDriver")
@XmlAccessorType(XmlAccessType.FIELD)
@PolicyDriverSettingsValid
public class XmlPolicyDriverInfo extends XmlPolicyDriverShort implements Serializable {
    private static final long serialVersionUID = -87503763058281542L;

    @XmlAttribute
    @NotNullValue
    @ValidInsuranceClassType
    protected InsuranceClassType insuranceClass;

    @XmlAttribute
    @NotNullValue
    @ValidInsuranceAgeClass
    protected InsuredAgeClass ageClass;

    @XmlAttribute
    @NotNullValue
    @ValidInsuranceExpirienceClass
    protected InsuredExpirienceClass expirienceClass;

    @XmlAttribute
    @NotNullValue
    protected Boolean privileger;

    @Valid
    protected XmlPersonalData personal;

    public XmlPolicyDriverInfo() {
    }

    public XmlPolicyDriverInfo(String idNumber) {
	super(idNumber);
    }

    public XmlPolicyDriverInfo(String idNumber, InsuranceClassType insuranceClass, XmlPersonalData personal) {
	super(idNumber);
	this.insuranceClass = insuranceClass;
	this.personal = personal;
    }

    public XmlPolicyDriverInfo(String idNumber, InsuranceClassType insuranceClass, InsuredAgeClass ageClass,
	    InsuredExpirienceClass expirienceClass, Boolean privileger, XmlPersonalData personal) {
	super(idNumber);
	this.insuranceClass = insuranceClass;
	this.ageClass = ageClass;
	this.expirienceClass = expirienceClass;
	this.personal = personal;
	this.privileger = privileger;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public InsuranceClassType getInsuranceClass() {
	return insuranceClass;
    }

    public void setInsuranceClass(InsuranceClassType insuranceClass) {
	this.insuranceClass = insuranceClass;
    }

    public XmlPersonalData getPersonal() {
	return personal;
    }

    public void setPersonal(XmlPersonalData personal) {
	this.personal = personal;
    }

    public InsuredAgeClass getAgeClass() {
	return ageClass;
    }

    public void setAgeClass(InsuredAgeClass ageClass) {
	this.ageClass = ageClass;
    }

    public InsuredExpirienceClass getExpirienceClass() {
	return expirienceClass;
    }

    public void setExpirienceClass(InsuredExpirienceClass expirienceClass) {
	this.expirienceClass = expirienceClass;
    }

    public Boolean getPrivileger() {
	return privileger;
    }

    public void setPrivileger(Boolean privileger) {
	this.privileger = privileger;
    }
}

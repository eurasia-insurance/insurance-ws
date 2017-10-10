package tech.lapsa.eurasia36.ws.jaxb.entity;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lapsa.validation.NotNullValue;

import tech.lapsa.eurasia36.ws.jaxb.validator.PolicyDriverAndVehicleCountValid;
import tech.lapsa.eurasia36.ws.jaxb.validator.PolicyParametersValid;

@XmlRootElement(name = "policyShort")
@XmlAccessorType(XmlAccessType.FIELD)
@PolicyParametersValid
@PolicyDriverAndVehicleCountValid
public class XmlPolicyShort implements Serializable {
    private static final long serialVersionUID = -4203799236492537107L;

    @XmlElementWrapper
    @XmlElementRef
    @NotNullValue
    @Valid
    @Size(min = 1, max = 10)
    protected XmlPolicyDriverInfo[] drivers;

    @XmlElementWrapper
    @XmlElementRef
    @NotNullValue
    @Valid
    @Size(min = 1, max = 10)
    protected XmlPolicyVehicleInfo[] vehicles;

    @Valid
    protected XmlPeriodInfo period;

    public XmlPolicyShort() {
    }

    public XmlPolicyShort(XmlPolicyDriverInfo[] drivers, XmlPolicyVehicleInfo[] vehicles, XmlPeriodInfo period) {
	this.drivers = drivers;
	this.vehicles = vehicles;
	this.period = period;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public XmlPolicyDriverInfo[] getDrivers() {
	return drivers;
    }

    public void setDrivers(XmlPolicyDriverInfo[] drivers) {
	this.drivers = drivers;
    }

    public XmlPolicyVehicleInfo[] getVehicles() {
	return vehicles;
    }

    public void setVehicles(XmlPolicyVehicleInfo[] vehicles) {
	this.vehicles = vehicles;
    }

    public XmlPeriodInfo getPeriod() {
	return period;
    }

    public void setPeriod(XmlPeriodInfo period) {
	this.period = period;
    }
}

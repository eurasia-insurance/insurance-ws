package tech.lapsa.insurance.ws.jaxb.entity;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

import tech.lapsa.insurance.ws.jaxb.validator.PolicyDriverAndVehicleCountValid;
import tech.lapsa.insurance.ws.jaxb.validator.PolicyParametersValid;
import tech.lapsa.javax.validation.NotNullValue;

@XmlRootElement(name = "fetchPolicy")
@XmlAccessorType(XmlAccessType.FIELD)
@PolicyParametersValid
@PolicyDriverAndVehicleCountValid
public class XmlFetchPolicy implements Serializable {
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

    public XmlFetchPolicy() {
    }

    public XmlFetchPolicy(final XmlPolicyDriverInfo[] drivers, final XmlPolicyVehicleInfo[] vehicles,
	    final XmlPeriodInfo period) {
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

    public void setDrivers(final XmlPolicyDriverInfo[] drivers) {
	this.drivers = drivers;
    }

    public XmlPolicyVehicleInfo[] getVehicles() {
	return vehicles;
    }

    public void setVehicles(final XmlPolicyVehicleInfo[] vehicles) {
	this.vehicles = vehicles;
    }

    public XmlPeriodInfo getPeriod() {
	return period;
    }

    public void setPeriod(final XmlPeriodInfo period) {
	this.period = period;
    }
}

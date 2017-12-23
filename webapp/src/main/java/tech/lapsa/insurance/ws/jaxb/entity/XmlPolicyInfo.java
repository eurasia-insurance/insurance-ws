package tech.lapsa.insurance.ws.jaxb.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

import tech.lapsa.insurance.ws.jaxb.validator.PolicyCostValid;
import tech.lapsa.javax.validation.NotNullValue;
import tech.lapsa.javax.validation.NotZeroAmount;

@XmlRootElement(name = "policy")
@XmlAccessorType(XmlAccessType.FIELD)
@PolicyCostValid
public class XmlPolicyInfo extends XmlFetchPolicy implements Serializable {
    private static final long serialVersionUID = -7828937206472435703L;

    @XmlAttribute
    @NotNullValue
    @NotZeroAmount
    protected Double cost;

    public XmlPolicyInfo() {
    }

    public XmlPolicyInfo(final XmlPolicyDriverInfo[] drivers, final XmlPolicyVehicleInfo[] vehicles,
	    final XmlPeriodInfo period,
	    final Double cost) {
	super(drivers, vehicles, period);
	this.cost = cost;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public Double getCost() {
	return cost;
    }

    public void setCost(final Double cost) {
	this.cost = cost;
    }

}

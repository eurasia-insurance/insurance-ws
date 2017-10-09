package tech.lapsa.eurasia36.ws.jaxb.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lapsa.validation.NotNullValue;
import com.lapsa.validation.NotZeroAmount;

import tech.lapsa.eurasia36.ws.jaxb.validator.PolicyCostValid;

@XmlRootElement(name = "policy")
@XmlAccessorType(XmlAccessType.FIELD)
@PolicyCostValid
public class XmlPolicyInfo extends XmlPolicyShort implements Serializable {
    private static final long serialVersionUID = -7828937206472435703L;

    @XmlAttribute
    @NotNullValue
    @NotZeroAmount
    protected Double cost;

    public XmlPolicyInfo() {
    }

    public XmlPolicyInfo(XmlPolicyDriverInfo[] drivers, XmlPolicyVehicleInfo[] vehicles, XmlPeriodInfo period,
	    Double cost) {
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

    public void setCost(Double cost) {
	this.cost = cost;
    }

}

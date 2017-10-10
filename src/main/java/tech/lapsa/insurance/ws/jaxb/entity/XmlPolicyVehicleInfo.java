package tech.lapsa.insurance.ws.jaxb.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lapsa.insurance.elements.VehicleAgeClass;
import com.lapsa.insurance.elements.VehicleClass;
import com.lapsa.insurance.validation.ValidPolicyVehicleAgeClass;
import com.lapsa.insurance.validation.ValidPolicyVehicleClass;
import com.lapsa.kz.country.KZArea;
import com.lapsa.kz.country.validators.ValidKZArea;
import com.lapsa.validation.NotNullValue;

import tech.lapsa.insurance.ws.jaxb.validator.PolicyVehicleSettingsValid;

@XmlRootElement(name = "policyVehicle")
@XmlAccessorType(XmlAccessType.FIELD)
@PolicyVehicleSettingsValid
public class XmlPolicyVehicleInfo implements Serializable {
    private static final long serialVersionUID = -6882618705945025909L;

    @XmlAttribute
    @NotNullValue
    @ValidKZArea
    protected KZArea area;

    @XmlAttribute
    @NotNullValue
    protected Boolean majorCity;

    @XmlAttribute
    @NotNullValue
    @ValidPolicyVehicleClass
    protected VehicleClass typeClass;

    @XmlAttribute
    @NotNullValue
    @ValidPolicyVehicleAgeClass
    protected VehicleAgeClass ageClass;

    @XmlAttribute
    @NotNullValue
    protected Boolean temporaryEntry;

    public XmlPolicyVehicleInfo() {
    }

    public XmlPolicyVehicleInfo(KZArea area, Boolean majorCity, VehicleClass typeClass, VehicleAgeClass ageClass,
	    Boolean temporaryEntry) {
	this.area = area;
	this.majorCity = majorCity;
	this.typeClass = typeClass;
	this.ageClass = ageClass;
	this.temporaryEntry = temporaryEntry;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public KZArea getArea() {
	return area;
    }

    public void setArea(KZArea area) {
	this.area = area;
    }

    public Boolean getMajorCity() {
	return majorCity;
    }

    public void setMajorCity(Boolean majorCity) {
	this.majorCity = majorCity;
    }

    public VehicleClass getTypeClass() {
	return typeClass;
    }

    public void setTypeClass(VehicleClass typeClass) {
	this.typeClass = typeClass;
    }

    public VehicleAgeClass getAgeClass() {
	return ageClass;
    }

    public void setAgeClass(VehicleAgeClass ageClass) {
	this.ageClass = ageClass;
    }

    public Boolean getTemporaryEntry() {
	return temporaryEntry;
    }

    public void setTemporaryEntry(Boolean temporaryEntry) {
	this.temporaryEntry = temporaryEntry;
    }

}

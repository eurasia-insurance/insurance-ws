package tech.lapsa.insurance.ws.jaxb.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lapsa.insurance.elements.VehicleAgeClass;
import com.lapsa.insurance.elements.VehicleClass;
import com.lapsa.insurance.validation.ValidPolicyVehicleAgeClass;
import com.lapsa.insurance.validation.ValidPolicyVehicleClass;
import com.lapsa.kz.country.KZArea;
import com.lapsa.kz.country.validators.ValidKZArea;

import tech.lapsa.insurance.ws.jaxb.validator.PolicyVehicleSettingsValid;
import tech.lapsa.javax.validation.NotNullValue;
import tech.lapsa.kz.vehicle.VehicleRegNumber;
import tech.lapsa.kz.vehicle.converter.jaxb.XmlVehicleRegNumberAdapter;
import tech.lapsa.kz.vehicle.validators.ValidVehicleRegNumber;

@XmlRootElement(name = "policyVehicle")
@XmlAccessorType(XmlAccessType.FIELD)
@PolicyVehicleSettingsValid
public class XmlPolicyVehicleInfo implements Serializable {
    private static final long serialVersionUID = 1L;

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

    @XmlAttribute
    @ValidVehicleRegNumber
    @XmlJavaTypeAdapter(XmlVehicleRegNumberAdapter.class)
    protected VehicleRegNumber regNumber;

    @XmlAttribute
    protected String name;

    @XmlAttribute
    protected Integer year;

    @XmlAttribute
    protected String vin;

    public XmlPolicyVehicleInfo() {
    }

    public XmlPolicyVehicleInfo(final KZArea area, final Boolean majorCity, final VehicleClass typeClass,
	    final VehicleAgeClass ageClass,
	    final Boolean temporaryEntry) {
	this.area = area;
	this.majorCity = majorCity;
	this.typeClass = typeClass;
	this.ageClass = ageClass;
	this.temporaryEntry = temporaryEntry;
    }

    public XmlPolicyVehicleInfo(final VehicleRegNumber regNumber) {
	this.regNumber = regNumber;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public KZArea getArea() {
	return area;
    }

    public void setArea(final KZArea area) {
	this.area = area;
    }

    public Boolean getMajorCity() {
	return majorCity;
    }

    public void setMajorCity(final Boolean majorCity) {
	this.majorCity = majorCity;
    }

    public VehicleClass getTypeClass() {
	return typeClass;
    }

    public void setTypeClass(final VehicleClass typeClass) {
	this.typeClass = typeClass;
    }

    public VehicleAgeClass getAgeClass() {
	return ageClass;
    }

    public void setAgeClass(final VehicleAgeClass ageClass) {
	this.ageClass = ageClass;
    }

    public Boolean getTemporaryEntry() {
	return temporaryEntry;
    }

    public void setTemporaryEntry(final Boolean temporaryEntry) {
	this.temporaryEntry = temporaryEntry;
    }

    // infos

    public VehicleRegNumber getRegNumber() {
	return regNumber;
    }

    public void setRegNumber(final VehicleRegNumber regNumber) {
	this.regNumber = regNumber;
    }

    public String getName() {
	return name;
    }

    public void setName(final String name) {
	this.name = name;
    }

    public Integer getYear() {
	return year;
    }

    public void setYear(final Integer year) {
	this.year = year;
    }

    public void setVin(final String vin) {
	this.vin = vin;
    }

    public String getVin() {
	return vin;
    }
}

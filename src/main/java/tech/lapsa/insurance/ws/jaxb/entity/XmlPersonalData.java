package tech.lapsa.insurance.ws.jaxb.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.builder.ToStringBuilder;

import tech.lapsa.java.jaxb.adapter.XmlLocalDateAdapter;
import tech.lapsa.javax.validation.TemporalPast;
import tech.lapsa.javax.validation.ValidHumanName;

@XmlRootElement(name = "personal")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlPersonalData implements Serializable {
    private static final long serialVersionUID = 2251857325639320600L;

    @XmlAttribute
    @ValidHumanName
    protected String name;

    @XmlAttribute
    @ValidHumanName
    protected String surename;

    @XmlAttribute
    @ValidHumanName
    protected String patronymic;

    @XmlAttribute
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    @TemporalPast
    protected LocalDate dayOfBirth;

    public XmlPersonalData() {
    }

    public XmlPersonalData(final String name, final String surename, final String patronymic,
	    final LocalDate dayOfBirth) {
	this.name = name;
	this.surename = surename;
	this.patronymic = patronymic;
	this.dayOfBirth = dayOfBirth;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public String getName() {
	return name;
    }

    public void setName(final String name) {
	this.name = name;
    }

    public String getSurename() {
	return surename;
    }

    public void setSurename(final String surename) {
	this.surename = surename;
    }

    public String getPatronymic() {
	return patronymic;
    }

    public void setPatronymic(final String patronymic) {
	this.patronymic = patronymic;
    }

    public LocalDate getDayOfBirth() {
	return dayOfBirth;
    }

    public void setDayOfBirth(final LocalDate dayOfBirth) {
	this.dayOfBirth = dayOfBirth;
    }
}

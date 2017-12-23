package tech.lapsa.insurance.ws.jaxb.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.builder.ToStringBuilder;

import tech.lapsa.java.jaxb.adapter.XmlLocalDateAdapter;
import tech.lapsa.javax.validation.LocalDateComparison;
import tech.lapsa.javax.validation.NotNullValue;
import tech.lapsa.javax.validation.TemporalFuture;
import tech.lapsa.javax.validation.TemporalLeftBeforeRight;

@XmlRootElement(name = "period")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlPeriodInfo implements Serializable {
    private static final long serialVersionUID = -911282003170159059L;

    @XmlAttribute
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    @TemporalFuture(allowNow = true)
    @NotNullValue
    protected LocalDate from;

    @XmlAttribute
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    @TemporalFuture(allowNow = false)
    @NotNullValue
    protected LocalDate to;

    @XmlTransient
    @TemporalLeftBeforeRight
    // method must be a getter (name begins with "get"). validation is not
    // processed if not
    public LocalDateComparison getComparison() {
	return new LocalDateComparison(from, to);
    }

    public XmlPeriodInfo() {
    }

    public XmlPeriodInfo(final LocalDate from, final LocalDate to) {
	this.from = from;
	this.to = to;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public LocalDate getFrom() {
	return from;
    }

    public void setFrom(final LocalDate from) {
	this.from = from;
    }

    public LocalDate getTo() {
	return to;
    }

    public void setTo(final LocalDate to) {
	this.to = to;
    }
}

package tech.lapsa.eurasia36.ws.jaxb.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lapsa.validation.LocalDateComparison;
import com.lapsa.validation.NotNullValue;
import com.lapsa.validation.TemporalFuture;
import com.lapsa.validation.TemporalLeftBeforeRight;

import tech.lapsa.java.jaxb.adapter.XmlLocalDateAdapter;

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

    public XmlPeriodInfo(LocalDate from, LocalDate to) {
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

    public void setFrom(LocalDate from) {
	this.from = from;
    }

    public LocalDate getTo() {
	return to;
    }

    public void setTo(LocalDate to) {
	this.to = to;
    }
}

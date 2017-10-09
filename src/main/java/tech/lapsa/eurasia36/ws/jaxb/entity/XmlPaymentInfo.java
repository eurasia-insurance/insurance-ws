package tech.lapsa.eurasia36.ws.jaxb.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lapsa.insurance.elements.PaymentMethod;
import com.lapsa.insurance.validation.ValidPaymentMethod;
import com.lapsa.validation.NotNullValue;

@XmlRootElement(name = "payment")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlPaymentInfo implements Serializable {
    private static final long serialVersionUID = -7070033829293894103L;

    @XmlAttribute
    @NotNullValue
    @ValidPaymentMethod(denied = PaymentMethod.UNDEFINED)
    protected PaymentMethod method;

    public XmlPaymentInfo() {
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public XmlPaymentInfo(PaymentMethod method) {
	this.method = method;
    }

    public PaymentMethod getMethod() {
	return method;
    }

    public void setMethod(PaymentMethod method) {
	this.method = method;
    }
}

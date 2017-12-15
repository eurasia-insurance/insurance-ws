package tech.lapsa.insurance.ws.jaxb.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.builder.ToStringBuilder;

import tech.lapsa.javax.validation.NotNullValue;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;
import tech.lapsa.kz.taxpayer.converter.jaxb.XmlTaxpayerNumberAdapter;
import tech.lapsa.kz.taxpayer.validators.ValidTaxpayerNumber;

@XmlRootElement(name = "fetchPolicyDriver")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlFetchPolicyDriver implements Serializable {
    private static final long serialVersionUID = 5729187347066360575L;

    @XmlAttribute
    @XmlJavaTypeAdapter(XmlTaxpayerNumberAdapter.class)
    @NotNullValue
    @ValidTaxpayerNumber
    protected TaxpayerNumber idNumber;

    public XmlFetchPolicyDriver() {
    }

    public XmlFetchPolicyDriver(final TaxpayerNumber idNumber) {
	this.idNumber = idNumber;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public TaxpayerNumber getIdNumber() {
	return idNumber;
    }

    public void setIdNumber(final TaxpayerNumber idNumber) {
	this.idNumber = idNumber;
    }

}

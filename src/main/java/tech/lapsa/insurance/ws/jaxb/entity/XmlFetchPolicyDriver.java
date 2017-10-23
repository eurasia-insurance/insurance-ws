package tech.lapsa.insurance.ws.jaxb.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lapsa.kz.idnumber.validators.ValidIdNumber;

@XmlRootElement(name = "fetchPolicyDriver")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlFetchPolicyDriver implements Serializable {
    private static final long serialVersionUID = 5729187347066360575L;

    @XmlAttribute
    @ValidIdNumber
    protected String idNumber;

    public XmlFetchPolicyDriver() {
    }

    public XmlFetchPolicyDriver(String idNumber) {
	this.idNumber = idNumber;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public String getIdNumber() {
	return idNumber;
    }

    public void setIdNumber(String idNumber) {
	this.idNumber = idNumber;
    }

}

package tech.lapsa.insurance.ws.jaxb.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import tech.lapsa.java.jaxb.adapter.XmlLocalDateAdapter;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;
import tech.lapsa.kz.taxpayer.converter.jaxb.XmlTaxpayerNumberAdapter;

@XmlRootElement(name = "checkPolicyResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlResponseCheckPolicy implements Serializable {

    private static final long serialVersionUID = 1L;

    // policyNumber

    @XmlAttribute
    private String policyNumber;

    public String getPolicyNumber() {
	return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
	this.policyNumber = policyNumber;
    }

    // agreementDate

    @XmlAttribute
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    private LocalDate agreementDate;

    public LocalDate getAgreementDate() {
	return agreementDate;
    }

    public void setAgreementDate(LocalDate agreementDate) {
	this.agreementDate = agreementDate;
    }

    // terminated

    @XmlAttribute
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    private LocalDate dateOfTermination;

    public LocalDate getDateOfTermination() {
	return dateOfTermination;
    }

    public void setDateOfTermination(LocalDate dateOfTermination) {
	this.dateOfTermination = dateOfTermination;
    }

    // validFrom

    @XmlAttribute
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    private LocalDate validFrom;

    public LocalDate getValidFrom() {
	return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
	this.validFrom = validFrom;
    }

    // validTill

    @XmlAttribute
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    private LocalDate validTill;

    public LocalDate getValidTill() {
	return validTill;
    }

    public void setValidTill(LocalDate validTill) {
	this.validTill = validTill;
    }

    // insurantName

    @XmlAttribute
    private String insurantName;

    public String getInsurantName() {
	return insurantName;
    }

    public void setInsurantName(String insurantName) {
	this.insurantName = insurantName;
    }

    // insurantIdNumber

    @XmlAttribute
    @XmlJavaTypeAdapter(XmlTaxpayerNumberAdapter.class)
    private TaxpayerNumber insurantIdNumber;

    public TaxpayerNumber insurantIdNumber() {
	return insurantIdNumber;
    }

    public void setInsurantIdNumber(TaxpayerNumber insurantIdNumber) {
	this.insurantIdNumber = insurantIdNumber;
    }

    // policyStatus

    public enum PolicyStatus {
	PENDING,
	VALID,
	EXPIRED,
	TERMINATED,
    }

    @XmlAttribute
    public PolicyStatus getPolicyStatus() {

	if (policyNumber == null
		|| agreementDate == null
		|| validFrom == null
		|| validTill == null)
	    return null;

	final LocalDate today = LocalDate.now();

	if (dateOfTermination != null && (dateOfTermination.isEqual(today) || today.isAfter(dateOfTermination)))
	    return PolicyStatus.TERMINATED;

	if (today.isBefore(validFrom))
	    return PolicyStatus.PENDING;

	if (today.isAfter(validTill))
	    return PolicyStatus.EXPIRED;

	return PolicyStatus.VALID;
    }

}

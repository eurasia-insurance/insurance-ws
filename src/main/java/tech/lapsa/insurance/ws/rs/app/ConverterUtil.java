package tech.lapsa.insurance.ws.rs.app;

import java.util.Arrays;

import com.lapsa.fin.FinCurrency;
import com.lapsa.insurance.domain.CallbackRequest;
import com.lapsa.insurance.domain.Driver;
import com.lapsa.insurance.domain.InsurancePeriodData;
import com.lapsa.insurance.domain.InsuranceRequest;
import com.lapsa.insurance.domain.PaymentData;
import com.lapsa.insurance.domain.PersonalData;
import com.lapsa.insurance.domain.Request;
import com.lapsa.insurance.domain.RequesterData;
import com.lapsa.insurance.domain.VehicleCertificateData;
import com.lapsa.insurance.domain.crm.UTMData;
import com.lapsa.insurance.domain.crm.User;
import com.lapsa.insurance.domain.policy.Policy;
import com.lapsa.insurance.domain.policy.PolicyDriver;
import com.lapsa.insurance.domain.policy.PolicyRequest;
import com.lapsa.insurance.domain.policy.PolicyVehicle;
import com.lapsa.insurance.elements.PaymentStatus;
import com.lapsa.insurance.elements.RequestSource;
import com.lapsa.kz.country.KZArea;
import com.lapsa.kz.country.KZCity;

import tech.lapsa.insurance.ws.jaxb.entity.XmlCallbackRequestInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPaymentInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPeriodInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPersonalData;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyDriverInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyDriverShort;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyRequestInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyShort;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyVehicleInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlRequestInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlRequesterInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlUTMInfo;
import tech.lapsa.java.commons.function.MyOptionals;

public class ConverterUtil {

    public static CallbackRequest convertCallbackRequest(XmlCallbackRequestInfo request, User createdBy)
	    throws WrongArgumentException {
	CallbackRequest response = new CallbackRequest();
	processConversionRequest(request, response, createdBy);
	processConversionCallbackRequest(request, response);
	return response;
    }

    public static PolicyRequest convertPolicyRequest(XmlPolicyRequestInfo request, User createdBy)
	    throws WrongArgumentException {
	PolicyRequest response = new PolicyRequest(RequestSource.API);
	processConversionRequest(request, response, createdBy);
	processConversionInsuranceRequest(request, response);
	processConversionPolicyRequest(request, response);
	return response;
    }

    public static RequesterData convertRequester(XmlRequesterInfo request) throws WrongArgumentException {
	RequesterData response = new RequesterData();
	processConversionRequester(request, response);
	return response;
    }

    public static UTMData convertUTM(XmlUTMInfo request) {
	if (request == null)
	    return null;
	UTMData response = new UTMData();
	processConversionUTM(request, response);
	return response;
    }

    private static PaymentData convertPayment(XmlPaymentInfo request) {
	if (request == null)
	    return null;
	PaymentData response = new PaymentData();
	processConversionPayment(request, response);
	return response;
    }

    public static XmlPolicyInfo convertPolicyShortToFull(XmlPolicyShort request) {
	XmlPolicyInfo response = new XmlPolicyInfo(Arrays.copyOf(request.getDrivers(), request.getDrivers().length),
		Arrays.copyOf(request.getVehicles(), request.getVehicles().length), request.getPeriod(),
		null);
	return response;
    }

    public static Policy convertPolicyShort(XmlPolicyShort request) throws WrongArgumentException {
	Policy response = new Policy();
	processConversionPolicyShort(request, response);
	return response;
    }

    public static InsurancePeriodData converPeriod(XmlPeriodInfo period) {
	if (period == null)
	    return null;
	InsurancePeriodData response = new InsurancePeriodData();
	processConversionPeriod(period, response);
	return response;
    }

    public static Policy convertPolicy(XmlPolicyInfo request)
	    throws WrongArgumentException {
	Policy response = new Policy();
	processConversionPolicyShort(request, response);
	processConversionPolicy(request, response);
	return response;
    }

    public static PolicyDriver convertDriver(XmlPolicyDriverInfo request)
	    throws WrongArgumentException {
	PolicyDriver response = new PolicyDriver();
	processConversionDriver(request, response);
	processConversionPolicyDriver(request, response);
	return response;
    }

    public static PolicyVehicle convertVehicle(XmlPolicyVehicleInfo request)
	    throws WrongArgumentException {
	PolicyVehicle response = new PolicyVehicle();
	processConversionPolicyVehicle(request, response);
	return response;
    }

    public static XmlPersonalData convertXmlPersonalData(PersonalData request) {
	XmlPersonalData response = new XmlPersonalData();
	response.setName(request.getName());
	response.setPatronymic(request.getPatronymic());
	response.setSurename(request.getSurename());
	response.setDayOfBirth(request.getDayOfBirth());
	return response;
    }

    public static XmlPolicyDriverInfo convertXmlPolicyDriver(PolicyDriver request) {
	XmlPolicyDriverInfo response = new XmlPolicyDriverInfo();
	processConversionXmlPolicyDriverShort(request, response);
	processConversionXmlPolicyDriverInfo(request, response);
	return response;
    }

    public static XmlPolicyVehicleInfo convertXmlPolicyVehicle(PolicyVehicle request) {
	XmlPolicyVehicleInfo response = new XmlPolicyVehicleInfo();
	processConversionXmlPolicyVehicleShort(request, response);
	processConversionXmlPolicyVehicleInfo(request, response);
	return response;
    }

    // PRIVATE

    private static void processConversionRequester(XmlRequesterInfo request, RequesterData response) {
	response.setName(request.getName());
	response.setEmail(request.getEmail());
	response.setPhone(request.getPhone());
	response.setPreferLanguage(request.getLanguage());
    }

    private static void processConversionUTM(XmlUTMInfo request, UTMData response) {
	response.setSource(request.getSource());
	response.setMedium(request.getMedium());
	response.setCampaign(request.getCampaign());
	response.setContent(request.getContent());
	response.setTerm(request.getTerm());
    }

    private static void processConversionPayment(XmlPaymentInfo request, PaymentData response) {
	response.setMethod(request.getMethod());
	response.setStatus(PaymentStatus.PENDING);
    }

    private static void processConversionPeriod(XmlPeriodInfo period, InsurancePeriodData response) {
	response.setFrom(period.getFrom());
	response.setTo(period.getTo());
    }

    private static void processConversionDriver(XmlPolicyDriverInfo request, Driver response) {
	response.setIdNumber(request.getIdNumber());
    }

    private static void processConversionPolicyDriver(XmlPolicyDriverInfo request, PolicyDriver response) {
	response.setInsuranceClassType(request.getInsuranceClass());
	response.setAgeClass(request.getAgeClass());
	response.setExpirienceClass(request.getExpirienceClass());
	response.setHasAnyPrivilege(request.getPrivileger() == null ? false : request.getPrivileger().booleanValue());
    }

    private static void processConversionPolicyVehicle(XmlPolicyVehicleInfo request, PolicyVehicle response)
	    throws WrongArgumentException {

	boolean temporaryEntry = request.getTemporaryEntry() == null ? false
		: request.getTemporaryEntry().booleanValue();
	response.setTemporaryEntry(temporaryEntry);

	if (!temporaryEntry) { // проверяем только, если не
			       // временный въезд
	    boolean majorCity = request.getMajorCity() == null ? false : request.getMajorCity().booleanValue();
	    response.setArea(request.getArea());
	    response.setForcedMajorCity(majorCity);
	    if ((request.getArea() == KZArea.GALM || request.getArea() == KZArea.GAST) && !majorCity)
		throw new WrongArgumentException(
			String.format("%1$s or %2$s should always be a major city", KZArea.GALM, KZArea.GAST));
	}

	response.setVehicleClass(request.getTypeClass());

	response.setVehicleAgeClass(request.getAgeClass());
    }

    private static void processConversionPolicy(XmlPolicyInfo request, Policy response) {
	response.getCalculation().setCalculatedPremiumCost(request.getCost());
	response.getCalculation().setPremiumCurrency(FinCurrency.KZT);
    }

    private static void processConversionPolicyShort(XmlPolicyShort request, Policy response)
	    throws WrongArgumentException {
	for (XmlPolicyDriverInfo driver : request.getDrivers())
	    response.addDriver(convertDriver(driver));

	for (XmlPolicyVehicleInfo vehicle : request.getVehicles())
	    response.addVehicle(convertVehicle(vehicle));

	if (request.getPeriod() != null)
	    response.setPeriod(converPeriod(request.getPeriod()));
    }

    private static void processConversionCallbackRequest(XmlCallbackRequestInfo request, CallbackRequest response) {
	response.setComments(request.getComments());
    }

    private static void processConversionRequest(XmlRequestInfo request, Request response, User createdBy)
	    throws WrongArgumentException {
	response.setCreatedBy(createdBy);
	RequesterData requester = convertRequester(request.getRequester());
	response.setRequester(requester);
	UTMData utm = convertUTM(request.getUtm());
	response.setUtmData(utm);
    }

    private static void processConversionInsuranceRequest(XmlPolicyRequestInfo request, InsuranceRequest response)
	    throws WrongArgumentException {
	PaymentData payment = convertPayment(request.getPayment());
	response.setPayment(payment);
	response.setType(request.getType());
    }

    private static void processConversionPolicyRequest(XmlPolicyRequestInfo request, PolicyRequest response)
	    throws WrongArgumentException {
	Policy policy = convertPolicy(request.getPolicy());
	response.setPolicy(policy);
    }

    private static void processConversionXmlPolicyVehicleInfo(PolicyVehicle request, XmlPolicyVehicleInfo response) {
	response.setAgeClass(request.getVehicleAgeClass());
	response.setArea(response.getArea());
	response.setTypeClass(request.getVehicleClass());

	MyOptionals.of(request.getFullName()) //
		.ifPresent(response::setName);

	MyOptionals.of(request.getYearOfManufacture()) //
		.ifPresent(response::setYear);

	MyOptionals.of(request.getVinCode()) //
		.ifPresent(response::setVin);

	MyOptionals.of(request.getCity()) //
		.map(KZCity::isRegional) //
		.ifPresent(response::setMajorCity);
    }

    private static void processConversionXmlPolicyVehicleShort(PolicyVehicle request, XmlPolicyVehicleInfo response) {
	MyOptionals.of(request.getCertificateData()) //
		.map(VehicleCertificateData::getRegistrationNumber) //
		.ifPresent(response::setRegNumber);
    }

    private static void processConversionXmlPolicyDriverInfo(PolicyDriver request, XmlPolicyDriverInfo response) {
	response.setAgeClass(request.getAgeClass());
	response.setExpirienceClass(request.getExpirienceClass());
	response.setInsuranceClass(request.getInsuranceClassType());
	XmlPersonalData personalData = convertXmlPersonalData(request.getPersonalData());
	response.setPersonal(personalData);
	response.setPrivileger(request.isHasAnyPrivilege());
    }

    private static void processConversionXmlPolicyDriverShort(Driver request, XmlPolicyDriverShort response) {
	response.setIdNumber(request.getIdNumber());
    }
}

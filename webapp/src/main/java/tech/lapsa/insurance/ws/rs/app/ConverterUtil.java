package tech.lapsa.insurance.ws.rs.app;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.Optional;

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
import com.lapsa.insurance.elements.CancelationReason;
import com.lapsa.insurance.elements.InsuranceRequestType;
import com.lapsa.kz.country.KZArea;
import com.lapsa.kz.country.KZCity;

import tech.lapsa.insurance.ws.jaxb.entity.XmlCallbackRequestInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlFetchPolicy;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPaymentInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPeriodInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPersonalData;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyDriverInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyRequestInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyVehicleInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlRequestInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlRequesterInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlResponseCheckPolicy;
import tech.lapsa.insurance.ws.jaxb.entity.XmlUTMInfo;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.javax.rs.utility.WrongArgumentException;

public class ConverterUtil {

    public static CallbackRequest convertCallbackRequest(final XmlCallbackRequestInfo request, final User createdBy)
	    throws WrongArgumentException {
	final CallbackRequest response = new CallbackRequest();
	processConversionRequest(request, response, createdBy);
	processConversionInsuranceRequest(request, response);
	processConversionCallbackRequest(request, response);
	return response;
    }

    public static PolicyRequest convertPolicyRequest(final XmlPolicyRequestInfo request, final User createdBy)
	    throws WrongArgumentException {
	final PolicyRequest response = new PolicyRequest();
	processConversionRequest(request, response, createdBy);
	processConversionInsuranceRequest(request, response);
	processConversionPolicyRequest(request, response);
	return response;
    }

    public static RequesterData convertRequester(final XmlRequesterInfo request) throws WrongArgumentException {
	final RequesterData response = new RequesterData();
	processConversionRequester(request, response);
	return response;
    }

    public static UTMData convertUTM(final XmlUTMInfo request) {
	if (request == null)
	    return null;
	final UTMData response = new UTMData();
	processConversionUTM(request, response);
	return response;
    }

    private static PaymentData convertPayment(final XmlPaymentInfo request) {
	if (request == null)
	    return null;
	final PaymentData response = new PaymentData();
	processConversionPayment(request, response);
	return response;
    }

    public static XmlPolicyInfo convertPolicyShortToFull(final XmlFetchPolicy request) {
	final XmlPolicyInfo response = new XmlPolicyInfo(
		Arrays.copyOf(request.getDrivers(), request.getDrivers().length),
		Arrays.copyOf(request.getVehicles(), request.getVehicles().length), request.getPeriod(),
		null);
	return response;
    }

    public static Policy convertPolicyShort(final XmlFetchPolicy request) throws WrongArgumentException {
	final Policy response = new Policy();
	processConversionPolicyShort(request, response);
	return response;
    }

    public static InsurancePeriodData converPeriod(final XmlPeriodInfo period) {
	if (period == null)
	    return null;
	final InsurancePeriodData response = new InsurancePeriodData();
	processConversionPeriod(period, response);
	return response;
    }

    public static Policy convertPolicy(final XmlPolicyInfo request)
	    throws WrongArgumentException {
	final Policy response = new Policy();
	processConversionPolicyShort(request, response);
	processConversionPolicy(request, response);
	return response;
    }

    public static PolicyDriver convertDriver(final XmlPolicyDriverInfo request)
	    throws WrongArgumentException {
	final PolicyDriver response = new PolicyDriver();
	processConversionDriver(request, response);
	processConversionPolicyDriver(request, response);
	return response;
    }

    public static PolicyVehicle convertVehicle(final XmlPolicyVehicleInfo request)
	    throws WrongArgumentException {
	final PolicyVehicle response = new PolicyVehicle();
	processConversionPolicyVehicle(request, response);
	return response;
    }

    public static XmlPersonalData convertXmlPersonalData(final PersonalData request) {
	final XmlPersonalData response = new XmlPersonalData();
	response.setName(request.getName());
	response.setPatronymic(request.getPatronymic());
	response.setSurename(request.getSurename());
	response.setDayOfBirth(request.getDateOfBirth());
	return response;
    }

    public static XmlPolicyDriverInfo convertXmlPolicyDriver(final PolicyDriver request) {
	final XmlPolicyDriverInfo response = new XmlPolicyDriverInfo();
	processConversionXmlPolicyDriverInfo(request, response);
	return response;
    }

    public static XmlPolicyVehicleInfo convertXmlPolicyVehicle(final PolicyVehicle request) {
	final XmlPolicyVehicleInfo response = new XmlPolicyVehicleInfo();
	processConversionXmlPolicyVehicleInfo(request, response);
	return response;
    }

    public static XmlResponseCheckPolicy convertXmlCheckPolicyResonse(final Policy request) {
	final XmlResponseCheckPolicy response = new XmlResponseCheckPolicy();
	processConversionXmlResponseCheckPolicy(request, response);
	return response;
    }

    // PRIVATE

    private static void processConversionRequester(final XmlRequesterInfo request, final RequesterData response) {
	response.setName(request.getName());
	response.setEmail(request.getEmail());
	response.setPhone(request.getPhone());
	response.setPreferLanguage(request.getLanguage());
    }

    private static void processConversionUTM(final XmlUTMInfo request, final UTMData response) {
	response.setSource(request.getSource());
	response.setMedium(request.getMedium());
	response.setCampaign(request.getCampaign());
	response.setContent(request.getContent());
	response.setTerm(request.getTerm());
    }

    private static void processConversionPayment(final XmlPaymentInfo request, final PaymentData response) {
    }

    private static void processConversionPeriod(final XmlPeriodInfo period, final InsurancePeriodData response) {
	response.setFrom(period.getFrom());
	response.setTo(period.getTo());
    }

    private static void processConversionDriver(final XmlPolicyDriverInfo request, final Driver response) {
	response.setIdNumber(request.getIdNumber());
    }

    private static void processConversionPolicyDriver(final XmlPolicyDriverInfo request, final PolicyDriver response) {
	response.setInsuranceClassType(request.getInsuranceClass());
	response.setAgeClass(request.getAgeClass());
	response.setExpirienceClass(request.getExpirienceClass());
	response.setHasAnyPrivilege(request.getPrivileger() == null ? false : request.getPrivileger().booleanValue());
    }

    private static void processConversionPolicyVehicle(final XmlPolicyVehicleInfo request, final PolicyVehicle response)
	    throws WrongArgumentException {

	final boolean temporaryEntry = request.getTemporaryEntry() == null ? false
		: request.getTemporaryEntry().booleanValue();
	response.setTemporaryEntry(temporaryEntry);

	if (!temporaryEntry) { // проверяем только, если не
			       // временный въезд
	    final boolean majorCity = request.getMajorCity() == null ? false : request.getMajorCity().booleanValue();
	    response.setArea(request.getArea());
	    response.setForcedMajorCity(majorCity);
	    if ((request.getArea() == KZArea.GALM || request.getArea() == KZArea.GAST) && !majorCity)
		throw new WrongArgumentException(
			String.format("%1$s or %2$s should always be a major city", KZArea.GALM, KZArea.GAST));
	}

	response.setVehicleClass(request.getTypeClass());

	response.setVehicleAgeClass(request.getAgeClass());
    }

    private static void processConversionPolicy(final XmlPolicyInfo request, final Policy response) {
	response.getCalculation().setAmount(request.getCost());
	response.getCalculation().setCurrency(Currency.getInstance("KZT"));
    }

    private static void processConversionPolicyShort(final XmlFetchPolicy request, final Policy response)
	    throws WrongArgumentException {
	for (final XmlPolicyDriverInfo driver : request.getDrivers())
	    response.addDriver(convertDriver(driver));

	for (final XmlPolicyVehicleInfo vehicle : request.getVehicles())
	    response.addVehicle(convertVehicle(vehicle));

	if (request.getPeriod() != null)
	    response.setPeriod(converPeriod(request.getPeriod()));
    }

    private static void processConversionCallbackRequest(final XmlCallbackRequestInfo request,
	    final CallbackRequest response) {
	response.setComments(request.getComments());
    }

    private static void processConversionRequest(final XmlRequestInfo request, final Request response,
	    final User createdBy)
	    throws WrongArgumentException {
	response.setCreatedBy(createdBy);
	final RequesterData requester = convertRequester(request.getRequester());
	response.setRequester(requester);
	final UTMData utm = convertUTM(request.getUtm());
	response.setUtmData(utm);
    }

    private static void processConversionInsuranceRequest(final XmlPolicyRequestInfo request,
	    final InsuranceRequest response)
	    throws WrongArgumentException {
	final PaymentData payment = convertPayment(request.getPayment());
	response.setPayment(payment);
	response.setType(request.getType());
    }

    private static void processConversionInsuranceRequest(final XmlCallbackRequestInfo request,
	    final InsuranceRequest response)
	    throws WrongArgumentException {
	response.setPayment(new PaymentData());
	response.setType(InsuranceRequestType.UNCOMPLETE);
    }

    private static void processConversionPolicyRequest(final XmlPolicyRequestInfo request, final PolicyRequest response)
	    throws WrongArgumentException {
	final Policy policy = convertPolicy(request.getPolicy());
	response.setPolicy(policy);
    }

    private static void processConversionXmlPolicyVehicleInfo(final PolicyVehicle request,
	    final XmlPolicyVehicleInfo response) {

	final Optional<KZArea> optArea = MyOptionals.of(request.getArea());
	final Optional<String> optName = MyOptionals.of(request.getFullName());

	MyOptionals.of(request.getCertificateData()) //
		.map(VehicleCertificateData::getRegistrationNumber) //
		.ifPresent(response::setRegNumber);

	MyOptionals.of(request.getVehicleAgeClass()) //
		.ifPresent(response::setAgeClass);

	MyOptionals.of(request.getVehicleClass()) //
		.ifPresent(response::setTypeClass);

	optArea.ifPresent(response::setArea);

	optName.ifPresent(response::setName);

	MyOptionals.of(request.getYearOfManufacture()) //
		.ifPresent(response::setYear);

	MyOptionals.of(request.getVinCode()) //
		.ifPresent(response::setVin);

	MyOptionals.of(request.getCity()) //
		.map(KZCity::isRegional) //
		.ifPresent(response::setMajorCity);

	optArea.filter(x -> x.equals(KZArea.GALM) || x.equals(KZArea.GAST)) //
		.ifPresent(x -> response.setMajorCity(true));

	optArea.ifPresent(x -> response.setTemporaryEntry(Boolean.FALSE));

	if (!optArea.isPresent() && optName.isPresent())
	    response.setTemporaryEntry(Boolean.TRUE);
    }

    private static void processConversionXmlPolicyDriverInfo(final PolicyDriver request,
	    final XmlPolicyDriverInfo response) {
	MyOptionals.of(request.getIdNumber())
		.ifPresent(response::setIdNumber);
	response.setAgeClass(request.getAgeClass());
	response.setExpirienceClass(request.getExpirienceClass());
	response.setInsuranceClass(request.getInsuranceClassType());
	final XmlPersonalData personalData = convertXmlPersonalData(request.getPersonalData());
	response.setPersonal(personalData);
	response.setPrivileger(request.isHasAnyPrivilege());
    }

    private static void processConversionXmlResponseCheckPolicy(final Policy request,
	    final XmlResponseCheckPolicy response) {

	response.setPolicyNumber(request.getNumber());
	response.setAgreementDate(request.getDateOfIssue());

	if (request.getInsurant() != null) {
	    if (request.getInsurant().getPersonal() != null)
		response.setInsurantName(request.getInsurant().getPersonal().getFullName());
	    else if (request.getInsurant().getCompany() != null)
		response.setInsurantName(request.getInsurant().getCompany().getFullName());
	    response.setInsurantIdNumber(request.getInsurant().getIdNumber());
	}

	response.setValidFrom(request.getPeriod().getFrom());
	response.setValidTill(request.getPeriod().getTo());

	if (request.getDateOfTermination() != null) {
	    final LocalDate date = request.getDateOfTermination();
	    final CancelationReason reason = request.getTerminationReason();
	    switch (reason) {
	    case MADE_INSURANCE_PAYMENT:
		response.setDateOfPayment(date);
		break;
	    default:
		response.setDateOfTermination(date);
		break;
	    }
	}
    }

}

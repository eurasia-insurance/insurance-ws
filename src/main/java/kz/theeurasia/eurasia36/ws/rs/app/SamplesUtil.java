package kz.theeurasia.eurasia36.ws.rs.app;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;

import com.lapsa.insurance.elements.InsuranceClassType;
import com.lapsa.insurance.elements.InsuranceRequestType;
import com.lapsa.insurance.elements.InsuredAgeClass;
import com.lapsa.insurance.elements.InsuredExpirienceClass;
import com.lapsa.insurance.elements.PaymentMethod;
import com.lapsa.insurance.elements.VehicleAgeClass;
import com.lapsa.insurance.elements.VehicleClass;
import com.lapsa.international.localization.LocalizationLanguage;
import com.lapsa.international.phone.CountryCode;
import com.lapsa.international.phone.PhoneNumber;
import com.lapsa.kz.country.KZArea;

import kz.theeurasia.eurasia36.ws.jaxb.entity.EbillMethodType;
import kz.theeurasia.eurasia36.ws.jaxb.entity.EbillStatus;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlCallbackRequestInfo;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlEbillInfo;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlEbillMethod;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlEbillPurpose;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlEbillPurposeItem;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlEbillResult;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlHttpForm;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlHttpFormParam;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlPaymentInfo;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlPeriodInfo;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlPersonalData;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlPolicyDriverInfo;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlPolicyInfo;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlPolicyRequestInfo;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlPolicyVehicleInfo;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlRequesterInfo;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlUTMInfo;

public class SamplesUtil {
    public static XmlPolicyDriverInfo driverSample() {
	XmlPolicyDriverInfo sample = new XmlPolicyDriverInfo("811203400953", InsuranceClassType.CLASS_10,
		InsuredAgeClass.OVER25, InsuredExpirienceClass.LESS2, false,
		new XmlPersonalData("Иван", "Иванов", "Иванович", LocalDate.of(1976, 4, 1)));
	return sample;
    }

    public static XmlPolicyVehicleInfo vehicleSample() {
	XmlPolicyVehicleInfo sample = new XmlPolicyVehicleInfo(KZArea.GALM, true, VehicleClass.CAR,
		VehicleAgeClass.UNDER7, false);
	return sample;
    }

    public static XmlPolicyInfo policySample() {
	XmlPolicyInfo sample = new XmlPolicyInfo(new XmlPolicyDriverInfo[] { driverSample() },
		new XmlPolicyVehicleInfo[] { vehicleSample() }, periodSample(), 18252.27d);
	return sample;
    }

    public static XmlPeriodInfo periodSample() {
	return new XmlPeriodInfo(LocalDate.of(2019, 04, 01), LocalDate.of(2019, 04, 01).plusYears(1).minusDays(1));
    }

    public static XmlUTMInfo utmSample() {
	return new XmlUTMInfo("google", "cpc", "context-advertising", "something", "key word one and two");
    }

    public static XmlRequesterInfo requesterSample() {
	XmlRequesterInfo sample = new XmlRequesterInfo("Джон Булл", PhoneNumber.of(CountryCode.KZ, "701", "9655474"),
		LocalizationLanguage.RUSSIAN, "john.smith@email.com");
	return sample;
    }

    public static XmlPolicyRequestInfo policyRequestSample() {
	XmlPolicyRequestInfo sample = new XmlPolicyRequestInfo(requesterSample(), utmSample(),
		policySample(), paymentSample(), InsuranceRequestType.EXPRESS);
	return sample;
    }

    public static XmlPaymentInfo paymentSample() {
	return new XmlPaymentInfo(PaymentMethod.PAYCARD_ONLINE);
    }

    public static XmlCallbackRequestInfo callbackRequestSample() {
	XmlCallbackRequestInfo sample = new XmlCallbackRequestInfo(requesterSample(), utmSample(),
		"Pls call back ASAP");
	return sample;
    }

    public static XmlEbillInfo ebillInfoSample() {
	XmlEbillInfo sample = new XmlEbillInfo("833835829896744", //
		13711.82d, //
		EbillStatus.READY, //
		LocalizationLanguage.ENGLISH, //
		Instant.parse("2016-08-01T19:24:11Z"), //
		Instant.parse("2016-08-01T19:24:11Z"), //
		ebillPurposeSample(), //
		new XmlEbillMethod[] { ebillMethodSample() }, ebillResultSample());
	return sample;
    }

    public static XmlEbillResult ebillResultSample() {
	XmlEbillResult sample = new XmlEbillResult(EbillMethodType.QAZKOM, "4444444444");
	return sample;
    }

    public static XmlEbillMethod ebillMethodSample() {
	XmlEbillMethod sample = new XmlEbillMethod(EbillMethodType.QAZKOM, httpFormSample());
	return sample;
    }

    public static XmlHttpForm httpFormSample() {
	try {
	    XmlHttpForm sample = new XmlHttpForm(new URL("https://testpay.kkb.kz/jsp/process/logon.jsp"), "POST",
		    Arrays.asList( //
			    new XmlHttpFormParam("Signed_Order_B64",
				    "PGRvY3VtZW50PjxtZXJjaGFudCBjZXJ0X2lkPSI3MWY4MDQxMCIgbmFtZT0iZXVyYXNpYTM2Lmt6Ij48b3JkZXIgb3JkZXJfaWQ9IjgzMzgzNTgyOTg5Njc0NCIgY3VycmVuY3k9IjM5OCIgYW1vdW50PSIxMzcxMS44MiI+PGRlcGFydG1lbnQgbWVyY2hhbnRfaWQ9Ijk4MTE2NjUxIiBhbW91bnQ9IjEzNzExLjgyIi8+PC9vcmRlcj48L21lcmNoYW50PjxtZXJjaGFudF9zaWduIHR5cGU9IlJTQSI+a0hQSzRsY1krZTF0QWpKZlQycVRwdG1oZVVxS2pkNkdTb2hyMk1sdXkxUzMvak9tSDVWVEpza1pPUENGUGNGM0gxZUtmU2tZc3FJdkhuVjF6blhnTVQzWjhEQkx5dWVrQk93d29tRjJJTlpscFltZ1F6cE5WbkJVSzY4eG5Uemxpa0JMMjYxRmI3SmFvbWlhT29DNDNtcWl6R0ZDaWI1UnRJQjNHS3V4OHdYTG1qbThCRGJaZ1lzSEhGNHYrc3BaVjhsSU14aUpiN3JreG1DSCtUeVNBQlNrRnFmYm9acm5EbzhXMklSRnFiU0p6cWc3WDlpQzhwTDZvRndjT2NhekhuY1NWbXRsNkc5Q081K0h4OXEzaG9UcW9WVXJwSEFPdjg3YnZhaUlZa3A0V3M3UTYwTVRGaFRMQmVJUUtpWUN1Z2pUcHJLTmRhMGY2d3FUNlYrMkN3PT08L21lcmNoYW50X3NpZ24+PC9kb2N1bWVudD4="), //
			    new XmlHttpFormParam("template", "default.xsl"), //
			    new XmlHttpFormParam("email", "vadim.o.isaev@gmail.com"), //
			    new XmlHttpFormParam("PostLink", "https://isaev.theeurasia.kz:8181/order/ws/qazkom/ok"), //
			    new XmlHttpFormParam("Language", "%%LANGUAGE_TAG%%"), //
			    new XmlHttpFormParam("appendix",
				    "PGRvY3VtZW50PjxpdGVtIG5hbWU9IlBvbGljeSBvZiBPSSBDTFZPIiBudW1iZXI9IjEiIHF1YW50aXR5PSIxIiBhbW91bnQ9IjEzNzExLjgyIi8+PC9kb2N1bWVudD4="),
			    new XmlHttpFormParam("BackLink", "%%PAYMENT_PAGE_URL%%"), //
			    new XmlHttpFormParam("FailureBackLink", "%%PAYMENT_PAGE_URL%%") //
		    ).stream().toArray(XmlHttpFormParam[]::new));
	    return sample;
	} catch (MalformedURLException e) {
	    return null;
	}
    }

    public static XmlEbillPurpose ebillPurposeSample() {
	XmlEbillPurpose sample = new XmlEbillPurpose(
		new XmlEbillPurposeItem[] { ebillPurposeItemSample() });
	return sample;
    }

    public static XmlEbillPurposeItem ebillPurposeItemSample() {
	XmlEbillPurposeItem sample = new XmlEbillPurposeItem("Policy of OI CLVO", 15000d, 1);
	return sample;
    }

}

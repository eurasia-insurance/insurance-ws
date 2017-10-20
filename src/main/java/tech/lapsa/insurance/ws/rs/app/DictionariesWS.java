package tech.lapsa.insurance.ws.rs.app;

import static tech.lapsa.javax.rs.utility.RESTUtils.*;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.security.PermitAll;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lapsa.insurance.elements.InsuranceClassType;
import com.lapsa.insurance.elements.InsuranceRequestType;
import com.lapsa.insurance.elements.InsuredAgeClass;
import com.lapsa.insurance.elements.InsuredExpirienceClass;
import com.lapsa.insurance.elements.PaymentMethod;
import com.lapsa.insurance.elements.VehicleAgeClass;
import com.lapsa.insurance.elements.VehicleClass;
import com.lapsa.international.localization.LocalizationLanguage;
import com.lapsa.kz.country.KZArea;

import tech.lapsa.insurance.ws.rs.entity.LocalizationLanguageWrapped;
import tech.lapsa.java.commons.localization.Localized;

@Path("/dict")
@Produces({ MediaType.APPLICATION_JSON })
@PermitAll
@Singleton
public class DictionariesWS extends ALanguageDetectorWS {

    @GET
    @Path("/insurance-class")
    public Response insuranceClassSelectableGET(@QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, InsuranceClassType.selectableValues()), lang.getLocale());
    }

    @GET
    @Path("/insurance-class/all")
    public Response insuranceClassAllGET(@QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, InsuranceClassType.values()), lang.getLocale());
    }

    //

    @GET
    @Path("/expirience-class")
    public Response expirienceClassSelectableGET(@QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, InsuredExpirienceClass.selectableValues()), lang.getLocale());
    }

    @GET
    @Path("/expirience-class/all")
    public Response expirienceClassAllGET(@QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, InsuredExpirienceClass.values()), lang.getLocale());
    }

    //

    @GET
    @Path("/driver-age-class")
    public Response driverAgeClassSelectableGET(@QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, InsuredAgeClass.selectableValues()), lang.getLocale());
    }

    @GET
    @Path("/driver-age-class/all")
    public Response driverAgeClassAllGET(@QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, InsuredAgeClass.values()), lang.getLocale());
    }

    //

    @GET
    @Path("/kz-area")
    public Response kzAreaSelectableGET(@QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, KZArea.selectableValues()), lang.getLocale());
    }

    @GET
    @Path("/kz-area/all")
    public Response kzAreaAllGET(@QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, KZArea.values()), lang.getLocale());
    }

    //

    @GET
    @Path("/vehicle-age-class")
    public Response vehicleAgeClassSelectableGET(@QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, VehicleAgeClass.selectableValues()), lang.getLocale());
    }

    @GET
    @Path("/vehicle-age-class/all")
    public Response vehicleAgeClassAllGET(@QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, VehicleAgeClass.values()), lang.getLocale());
    }

    //

    @GET
    @Path("/vehicle-type-class")
    public Response vehicleTypeClassSelectableGET(@QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, VehicleClass.selectableValues()), lang.getLocale());
    }

    @GET
    @Path("/vehicle-type-class/all")
    public Response vehicleTypeClassAllGET(@QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, VehicleClass.values()), lang.getLocale());
    }

    //

    @GET
    @Path("/localization-language")
    public Response localizationLanguageSelectableGET(
	    @QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, LocalizationLanguage.selectableValues()), lang.getLocale());
    }

    @GET
    @Path("/localization-language/all")
    public Response localizationLanguageAllGET(@QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, LocalizationLanguage.values()), lang.getLocale());
    }

    //

    @GET
    @Path("/payment-method")
    public Response paymentMethodSelectableGET(@QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, PaymentMethod.selectableValues()), lang.getLocale());
    }

    @GET
    @Path("/payment-method/all")
    public Response paymentMethodAllGET(@QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, PaymentMethod.values()), lang.getLocale());
    }

    //

    @GET
    @Path("/insurance-request-type")
    public Response insuranceRequestTypeSelectableGET(
	    @QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, InsuranceRequestType.UNCOMPLETE, InsuranceRequestType.EXPRESS),
		lang.getLocale());
    }

    @GET
    @Path("/insurance-request-type/all")
    public Response insuranceRequestTypeAllGET(@QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, InsuranceRequestType.values()), lang.getLocale());
    }

    //

    private <T extends Localized> Object convertToResult(final LocalizationLanguage lang,
	    @SuppressWarnings("unchecked") final T... values) {
	Function<? super T, ? extends Map<Localized.LocalizationVariant, String>> valueMapper = //
		t -> Stream.of(Localized.LocalizationVariant.values()) //
			.collect(Collectors.toMap(Function.identity(), x -> t.localized(x, lang.getLocale())));
	Map<T, Map<Localized.LocalizationVariant, String>> entries = //
		Stream.of(values) //
			.collect(Collectors.toMap(Function.identity(), valueMapper));
	return entries;
    }
}

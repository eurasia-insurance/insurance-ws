package tech.lapsa.insurance.ws.rs.app;

import static tech.lapsa.javax.rs.utility.RESTUtils.*;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.security.PermitAll;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lapsa.insurance.elements.InsuranceClassType;
import com.lapsa.insurance.elements.InsuranceRequestType;
import com.lapsa.insurance.elements.InsuredAgeClass;
import com.lapsa.insurance.elements.InsuredExpirienceClass;
import com.lapsa.insurance.elements.VehicleAgeClass;
import com.lapsa.insurance.elements.VehicleClass;
import com.lapsa.international.localization.LocalizationLanguage;
import com.lapsa.kz.country.KZArea;
import com.lapsa.kz.country.KZCity;

import tech.lapsa.insurance.ws.rs.entity.LocalizationLanguageWrapped;
import tech.lapsa.java.commons.localization.Localized;
import tech.lapsa.javax.validation.NotNullValue;

@Path("/dict")
@Produces({ MediaType.APPLICATION_JSON })
@PermitAll
@Singleton
public class DictionariesWS extends ALanguageDetectorWS {

    @GET
    @Path("/insurance-class")
    public Response insuranceClassSelectableGET(
	    @QueryParam("lang") final LocalizationLanguageWrapped queryLangWrapped) {
	final LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, InsuranceClassType.selectableValues()), lang.getLocale());
    }

    //

    @GET
    @Path("/expirience-class")
    public Response expirienceClassSelectableGET(
	    @QueryParam("lang") final LocalizationLanguageWrapped queryLangWrapped) {
	final LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, InsuredExpirienceClass.selectableValues()), lang.getLocale());
    }

    //

    @GET
    @Path("/driver-age-class")
    public Response driverAgeClassSelectableGET(
	    @QueryParam("lang") final LocalizationLanguageWrapped queryLangWrapped) {
	final LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, InsuredAgeClass.selectableValues()), lang.getLocale());
    }

    //

    @GET
    @Path("/kz-area")
    public Response kzAreaSelectableGET(@QueryParam("lang") final LocalizationLanguageWrapped queryLangWrapped) {
	final LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, KZArea.selectableValues()), lang.getLocale());
    }

    //

    @GET
    @Path("/kz-city/available/")
    public Response kzCitySelectableGET(@QueryParam("lang") final LocalizationLanguageWrapped queryLangWrapped) {
	final LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, KZCity.selectableValues()), lang.getLocale());
    }

    @GET
    @Path("/kz-city/majors/")
    public Response kzCityMajorsGET(@QueryParam("lang") final LocalizationLanguageWrapped queryLangWrapped) {
	final LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, KZCity.regionalValuesByArea(Optional.empty())), lang.getLocale());
    }

    @GET
    @Path("/kz-city/available/{area}")
    public Response kzCityByAreaGET(@QueryParam("lang") final LocalizationLanguageWrapped queryLangWrapped,
	    @PathParam("area") @NotNullValue final KZArea area) {
	final LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, KZCity.selectableValuesByArea(area)), lang.getLocale());
    }

    @GET
    @Path("/kz-city/majors/{area}/")
    public Response kzCityMajorByAreaGET(@QueryParam("lang") final LocalizationLanguageWrapped queryLangWrapped,
	    @PathParam("area") @NotNullValue final KZArea area) {
	final LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, KZCity.regionalValuesByArea(area)), lang.getLocale());
    }

    //

    @GET
    @Path("/vehicle-age-class")
    public Response vehicleAgeClassSelectableGET(
	    @QueryParam("lang") final LocalizationLanguageWrapped queryLangWrapped) {
	final LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, VehicleAgeClass.selectableValues()), lang.getLocale());
    }

    //

    @GET
    @Path("/vehicle-type-class")
    public Response vehicleTypeClassSelectableGET(
	    @QueryParam("lang") final LocalizationLanguageWrapped queryLangWrapped) {
	final LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, VehicleClass.selectableValues()), lang.getLocale());
    }

    //

    @GET
    @Path("/localization-language")
    public Response localizationLanguageSelectableGET(
	    @QueryParam("lang") final LocalizationLanguageWrapped queryLangWrapped) {
	final LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, LocalizationLanguage.selectableValues()), lang.getLocale());
    }

    //

    @GET
    @Path("/insurance-request-type")
    public Response insuranceRequestTypeSelectableGET(
	    @QueryParam("lang") final LocalizationLanguageWrapped queryLangWrapped) {
	final LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(convertToResult(lang, InsuranceRequestType.UNCOMPLETE, InsuranceRequestType.EXPRESS),
		lang.getLocale());
    }

    //

    private <T extends Localized> Object convertToResult(final LocalizationLanguage lang,
	    @SuppressWarnings("unchecked") final T... values) {
	final Function<? super T, ? extends Map<Localized.LocalizationVariant, String>> valueMapper = //
		t -> Stream.of(Localized.LocalizationVariant.values()) //
			.collect(Collectors.toMap(Function.identity(), x -> t.localized(x, lang.getLocale())));
	final Map<T, Map<Localized.LocalizationVariant, String>> entries = //
		Stream.of(values) //
			.collect(Collectors.toMap(Function.identity(), valueMapper));
	return entries;
    }
}

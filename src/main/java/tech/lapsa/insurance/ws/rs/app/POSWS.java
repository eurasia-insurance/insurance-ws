package tech.lapsa.insurance.ws.rs.app;

import static tech.lapsa.java.commons.function.MyExceptions.*;
import static tech.lapsa.javax.rs.utility.RESTUtils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.lapsa.insurance.domain.CompanyContactEmail;
import com.lapsa.insurance.domain.CompanyContactPhone;
import com.lapsa.insurance.domain.CompanyPointOfSale;
import com.lapsa.international.localization.LocalizationLanguage;
import com.lapsa.kz.country.KZCity;

import tech.lapsa.insurance.facade.CompanyPointOfSaleFacade;
import tech.lapsa.insurance.ws.ejb.EJBViaCDI;
import tech.lapsa.insurance.ws.jaxb.entity.XmlGeo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPOS;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPOSCity;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPOSEmail;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPOSPhone;
import tech.lapsa.insurance.ws.rs.entity.LocalizationLanguageWrapped;
import tech.lapsa.javax.validation.NotNullValue;

@Path("/pos")
@Produces({ MediaType.APPLICATION_JSON })
@PermitAll
@Singleton
public class POSWS extends ALanguageDetectorWS {

    @Context
    private UriInfo uriInfo;

    @Inject
    @EJBViaCDI
    private CompanyPointOfSaleFacade posFacade;

    @GET
    @Path("/all/{lang}")
    public Response getAllOwnAvailableLangPath(
	    @PathParam("lang") @NotNullValue LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);

	return responseOk(all(lang), lang.getLocale());
    }

    @GET
    @Path("/all")
    public Response getAllOwnAvailableLangDefault(@QueryParam("lang") LocalizationLanguageWrapped queryLangWrapped) {
	LocalizationLanguage lang = getLanguageOrDefault(queryLangWrapped);
	return responseOk(all(lang), lang.getLocale());
    }

    private Object all(LocalizationLanguage language) {

	final List<CompanyPointOfSale> poses = reThrowAsUnchecked(() -> posFacade.findAllOwnOffices());

	List<KZCity> order = new ArrayList<>();
	Map<KZCity, List<CompanyPointOfSale>> cityMap = new HashMap<>();
	for (CompanyPointOfSale pos : poses) {
	    if (!cityMap.containsKey(pos.getAddress().getCity())) {
		cityMap.put(pos.getAddress().getCity(), new ArrayList<CompanyPointOfSale>());
		order.add(pos.getAddress().getCity());
	    }
	    cityMap.get(pos.getAddress().getCity()).add(pos);
	}

	List<XmlPOSCity> result = new ArrayList<>();
	for (KZCity kzc : order) {
	    XmlPOSCity city = new XmlPOSCity();
	    result.add(city);
	    city.setName(kzc.few(language.getLocale()));

	    {
		List<XmlPOS> list1 = new ArrayList<>();
		for (CompanyPointOfSale cpos : cityMap.get(kzc)) {
		    XmlPOS pos = new XmlPOS();
		    list1.add(pos);
		    pos.setId(cpos.getId());
		    pos.setName(cpos.few(language.getLocale()));
		    pos.setAddress(cpos.getAddress().few(language.getLocale()));
		    pos.setDeliveryServiceEnable(cpos.isDeliveryServicesAvailable());
		    pos.setPickupServiceAvailable(cpos.isPickupAvailable());

		    if (cpos.getGeoPoint() != null) {
			pos.setGeoPoint(new XmlGeo());
			pos.getGeoPoint().setLat(cpos.getGeoPoint().getLatitude());
			pos.getGeoPoint().setLng(cpos.getGeoPoint().getLongitude());
		    }

		    {
			List<XmlPOSPhone> list2 = new ArrayList<>();
			for (CompanyContactPhone ccp : cpos.getPhones()) {
			    XmlPOSPhone phone = new XmlPOSPhone();
			    list2.add(phone);
			    phone.setType(ccp.getPhoneType().regular(language.getLocale()));
			    phone.setFullNumber(ccp.getPhone().getFormatted());
			}
			pos.setPhones(list2.toArray(new XmlPOSPhone[0]));
		    }

		    {
			List<XmlPOSEmail> list2 = new ArrayList<>();
			for (CompanyContactEmail cce : cpos.getEmailAddresses()) {
			    XmlPOSEmail xmlPOSEmail = new XmlPOSEmail();
			    list2.add(xmlPOSEmail);
			    xmlPOSEmail.setAddress(cce.getAddress());
			}
			pos.setEmails(list2.toArray(new XmlPOSEmail[0]));
		    }

		}
		city.setPoses(list1.toArray(new XmlPOS[0]));
	    }
	}

	return result;
    }
}

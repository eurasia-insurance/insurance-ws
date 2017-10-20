package tech.lapsa.insurance.ws.rs.app;

import static tech.lapsa.insurance.ws.rs.app.ConverterUtil.*;
import static tech.lapsa.javax.rs.utility.RESTUtils.*;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lapsa.insurance.domain.policy.Policy;
import com.lapsa.insurance.domain.policy.PolicyDriver;

import tech.lapsa.insurance.calculation.CalculationFailed;
import tech.lapsa.insurance.calculation.PolicyCalculation;
import tech.lapsa.insurance.facade.PolicyDriverFacade;
import tech.lapsa.insurance.ws.auth.InsuranceSecurity;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyDriverInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyDriverShort;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyShort;
import tech.lapsa.javax.validation.NotNullValue;

@Path("/policy")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@RolesAllowed({ InsuranceSecurity.ROLE_ADMIN, InsuranceSecurity.ROLE_AGENT })
@Singleton
public class PolicyWS extends ALanguageDetectorWS {

    @POST
    @Path("/fetch-driver")
    public Response fetchDriverPOST(@NotNullValue @Valid XmlPolicyDriverShort request) {
	return fetchDriver(request);
    }

    private Response fetchDriver(XmlPolicyDriverShort request) {
	try {
	    XmlPolicyDriverInfo reply = _fetchDriver(request);
	    return responseOk(reply, getLocaleOrDefault());
	} catch (WrongArgumentException e) {
	    return responseBadRequest(e.getMessage(), getLocaleOrDefault());
	} catch (ServerException e) {
	    return responseServerError(e.getMessage(), getLocaleOrDefault());
	}
    }

    @Inject
    private PolicyDriverFacade facade;

    private XmlPolicyDriverInfo _fetchDriver(XmlPolicyDriverShort request)
	    throws WrongArgumentException, ServerException {
	PolicyDriver driver = facade.fetchByIdNumber(request.getIdNumber());
	XmlPolicyDriverInfo response = ConverterUtil.convertXmlPolicyDriver(driver);
	return response;
    }

    @POST
    @Path("/fetch-policy")
    public Response fetchPolicyPOST(@NotNullValue @Valid XmlPolicyShort request) {
	return fetchPolicy(request);
    }

    private Response fetchPolicy(XmlPolicyShort request) {
	try {
	    XmlPolicyInfo reply = _fetchPolicy(request);
	    return responseOk(reply, getLocaleOrDefault());
	} catch (WrongArgumentException e) {
	    return responseBadRequest(e.getMessage(), getLocaleOrDefault());
	} catch (ServerException e) {
	    return responseServerError(e.getMessage(), getLocaleOrDefault());
	}
    }

    private XmlPolicyInfo _fetchPolicy(XmlPolicyShort request)
	    throws WrongArgumentException, ServerException {
	if (request == null)
	    throw new WrongArgumentException("Request data is empty");

	Policy policy = convertPolicyShort(request);

	try {
	    PolicyCalculation.calculatePolicyCost(policy);
	} catch (CalculationFailed e) {
	    throw new ServerException(
		    String.format("Calculation failed. %1$s. Ask to support team for details. ", e.getMessage()), e);
	}

	XmlPolicyInfo response = convertPolicyShortToFull(request);

	response.setCost(policy.getCalculation().getCalculatedPremiumCost());
	return response;
    }
}

package tech.lapsa.insurance.ws.rs.app;

import static tech.lapsa.insurance.ws.rs.app.ConverterUtil.*;
import static tech.lapsa.java.commons.function.MyExceptions.*;
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
import com.lapsa.insurance.domain.policy.PolicyVehicle;

import tech.lapsa.insurance.calculation.CalculationFailed;
import tech.lapsa.insurance.calculation.PolicyCalculation;
import tech.lapsa.insurance.facade.PolicyDriverFacade;
import tech.lapsa.insurance.facade.PolicyVehicleFacade;
import tech.lapsa.insurance.ws.auth.InsuranceSecurity;
import tech.lapsa.insurance.ws.jaxb.entity.XmlFetchPolicy;
import tech.lapsa.insurance.ws.jaxb.entity.XmlFetchPolicyDriver;
import tech.lapsa.insurance.ws.jaxb.entity.XmlFetchPolicyVehicle;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyDriverInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyVehicleInfo;
import tech.lapsa.java.commons.logging.MyLogger;
import tech.lapsa.javax.rs.utility.InternalServerErrorException;
import tech.lapsa.javax.rs.utility.WrongArgumentException;
import tech.lapsa.javax.validation.NotNullValue;

@Path("/policy")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@RolesAllowed({ InsuranceSecurity.ROLE_ADMIN, InsuranceSecurity.ROLE_AGENT })
@Singleton
public class PolicyWS extends ALanguageDetectorWS {

    @POST
    @Path("/fetch-driver")
    public Response fetchDriverPOST(@NotNullValue @Valid XmlFetchPolicyDriver request) {
	return fetchDriver(request);
    }

    private Response fetchDriver(XmlFetchPolicyDriver request) {
	try {
	    XmlPolicyDriverInfo reply = _fetchDriver(request);
	    return responseOk(reply, getLocaleOrDefault());
	} catch (WrongArgumentException e) {
	    return responseWrongArgument(e, getLocaleOrDefault());
	} catch (InternalServerErrorException e) {
	    return responseInternalServerError(e, getLocaleOrDefault());
	}
    }

    @POST
    @Path("/fetch-vehicle")
    public Response fetchVehiclePOST(@NotNullValue @Valid XmlFetchPolicyVehicle request) {
	return fetchVehicle(request);
    }

    private Response fetchVehicle(XmlFetchPolicyVehicle request) {
	try {
	    XmlPolicyVehicleInfo reply = _fetchVehicle(request);
	    return responseOk(reply, getLocaleOrDefault());
	} catch (WrongArgumentException e) {
	    return responseWrongArgument(e, getLocaleOrDefault());
	} catch (InternalServerErrorException e) {
	    return responseInternalServerError(e, getLocaleOrDefault());
	}
    }

    @POST
    @Path("/fetch-policy")
    public Response fetchPolicyPOST(@NotNullValue @Valid XmlFetchPolicy request) {
	return fetchPolicy(request);
    }

    private Response fetchPolicy(XmlFetchPolicy request) {
	try {
	    XmlPolicyInfo reply = _fetchPolicy(request);
	    return responseOk(reply, getLocaleOrDefault());
	} catch (WrongArgumentException e) {
	    return responseWrongArgument(e, getLocaleOrDefault());
	} catch (InternalServerErrorException e) {
	    return responseInternalServerError(e, getLocaleOrDefault());
	}
    }

    // PRIVATE

    private final MyLogger logger = MyLogger.newBuilder() //
	    .withNameOf(PolicyWS.class) //
	    .build();

    @Inject
    private PolicyDriverFacade driverFacade;

    private XmlPolicyDriverInfo _fetchDriver(XmlFetchPolicyDriver request)
	    throws WrongArgumentException, InternalServerErrorException {
	try {
	    PolicyDriver driver = reThrowAsUnchecked(() ->
	    //
	    driverFacade.getByTaxpayerNumberOrDefault(request.getIdNumber())
	    //
	    );
	    XmlPolicyDriverInfo response = ConverterUtil.convertXmlPolicyDriver(driver);
	    return response;
	} catch (IllegalArgumentException | IllegalStateException e) {
	    logger.DEBUG.log(e);
	    throw new WrongArgumentException(e);
	} catch (RuntimeException e) {
	    logger.SEVERE.log(e);
	    throw new InternalServerErrorException(e);
	}
    }

    //

    @Inject
    private PolicyVehicleFacade policyVehicles;

    private XmlPolicyVehicleInfo _fetchVehicle(XmlFetchPolicyVehicle request)
	    throws WrongArgumentException, InternalServerErrorException {
	try {
	    PolicyVehicle vehicle = reThrowAsUnchecked(() ->
	    //
	    policyVehicles.getByRegNumberOrDefault(request.getRegNumber())
	    //
	    );
	    XmlPolicyVehicleInfo response = ConverterUtil.convertXmlPolicyVehicle(vehicle);
	    return response;
	} catch (IllegalArgumentException | IllegalStateException e) {
	    logger.DEBUG.log(e);
	    throw new WrongArgumentException(e);
	} catch (RuntimeException e) {
	    logger.SEVERE.log(e);
	    throw new InternalServerErrorException(e);
	}
    }

    private XmlPolicyInfo _fetchPolicy(XmlFetchPolicy request)
	    throws WrongArgumentException, InternalServerErrorException {
	try {
	    if (request == null)
		throw new WrongArgumentException("Request data is empty");

	    Policy policy = convertPolicyShort(request);

	    try {
		PolicyCalculation.calculatePolicyCost(policy);
	    } catch (CalculationFailed e) {
		throw new InternalServerErrorException(
			String.format("Calculation failed. %1$s. Ask to support team for details. ", e.getMessage()),
			e);
	    }

	    XmlPolicyInfo response = convertPolicyShortToFull(request);

	    response.setCost(policy.getCalculation().getCalculatedPremiumCost());
	    return response;
	} catch (IllegalArgumentException | IllegalStateException e) {
	    logger.DEBUG.log(e);
	    throw new WrongArgumentException(e);
	} catch (RuntimeException e) {
	    logger.SEVERE.log(e);
	    throw new InternalServerErrorException(e);
	}
    }

}

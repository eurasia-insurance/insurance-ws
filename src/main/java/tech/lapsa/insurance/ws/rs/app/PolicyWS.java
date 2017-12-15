package tech.lapsa.insurance.ws.rs.app;

import static tech.lapsa.insurance.ws.rs.app.ConverterUtil.*;
import static tech.lapsa.javax.rs.utility.RESTUtils.*;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
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
import tech.lapsa.insurance.facade.PolicyDriverFacade.PolicyDriverFacadeRemote;
import tech.lapsa.insurance.facade.PolicyVehicleFacade.PolicyVehicleFacadeRemote;
import tech.lapsa.insurance.ws.auth.InsuranceSecurity;
import tech.lapsa.insurance.ws.jaxb.entity.XmlFetchPolicy;
import tech.lapsa.insurance.ws.jaxb.entity.XmlFetchPolicyDriver;
import tech.lapsa.insurance.ws.jaxb.entity.XmlFetchPolicyVehicle;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyDriverInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyVehicleInfo;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyObjects;
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
    public Response fetchDriverPOST(@NotNullValue @Valid final XmlFetchPolicyDriver request) {
	return fetchDriver(request);
    }

    private Response fetchDriver(final XmlFetchPolicyDriver request) {
	try {
	    final XmlPolicyDriverInfo reply = _fetchDriver(request);
	    return responseOk(reply, getLocaleOrDefault());
	} catch (final WrongArgumentException e) {
	    return responseWrongArgument(e, getLocaleOrDefault());
	} catch (final InternalServerErrorException e) {
	    return responseInternalServerError(e, getLocaleOrDefault());
	}
    }

    @POST
    @Path("/fetch-vehicle")
    public Response fetchVehiclePOST(@NotNullValue @Valid final XmlFetchPolicyVehicle request) {
	return fetchVehicle(request);
    }

    private Response fetchVehicle(final XmlFetchPolicyVehicle request) {
	try {
	    final XmlPolicyVehicleInfo reply = _fetchVehicle(request);
	    return responseOk(reply, getLocaleOrDefault());
	} catch (final WrongArgumentException e) {
	    return responseWrongArgument(e, getLocaleOrDefault());
	} catch (final InternalServerErrorException e) {
	    return responseInternalServerError(e, getLocaleOrDefault());
	}
    }

    @POST
    @Path("/fetch-policy")
    public Response fetchPolicyPOST(@NotNullValue @Valid final XmlFetchPolicy request) {
	return fetchPolicy(request);
    }

    private Response fetchPolicy(final XmlFetchPolicy request) {
	try {
	    final XmlPolicyInfo reply = _fetchPolicy(request);
	    return responseOk(reply, getLocaleOrDefault());
	} catch (final WrongArgumentException e) {
	    return responseWrongArgument(e, getLocaleOrDefault());
	} catch (final InternalServerErrorException e) {
	    return responseInternalServerError(e, getLocaleOrDefault());
	}
    }

    // PRIVATE

    private final MyLogger logger = MyLogger.newBuilder() //
	    .withNameOf(PolicyWS.class) //
	    .build();

    @EJB
    private PolicyDriverFacadeRemote driverFacade;

    private XmlPolicyDriverInfo _fetchDriver(final XmlFetchPolicyDriver request)
	    throws WrongArgumentException, InternalServerErrorException {
	try {
	    final PolicyDriver driver = driverFacade.getByTaxpayerNumberOrDefault(request.getIdNumber());
	    final XmlPolicyDriverInfo response = ConverterUtil.convertXmlPolicyDriver(driver);
	    return response;
	} catch (final IllegalArgument e) {
	    logger.DEBUG.log(e);
	    throw new WrongArgumentException(e);
	} catch (final RuntimeException e) {
	    logger.SEVERE.log(e);
	    throw new InternalServerErrorException(e);
	}
    }

    //

    @EJB
    private PolicyVehicleFacadeRemote policyVehicles;

    private XmlPolicyVehicleInfo _fetchVehicle(final XmlFetchPolicyVehicle request)
	    throws WrongArgumentException, InternalServerErrorException {
	try {
	    final PolicyVehicle vehicle = policyVehicles.fetchFirstByRegNumberOrDefault(request.getRegNumber());
	    final XmlPolicyVehicleInfo response = ConverterUtil.convertXmlPolicyVehicle(vehicle);
	    return response;
	} catch (final IllegalArgument e) {
	    logger.DEBUG.log(e);
	    throw new WrongArgumentException(e);
	} catch (final RuntimeException e) {
	    logger.SEVERE.log(e);
	    throw new InternalServerErrorException(e);
	}
    }

    private XmlPolicyInfo _fetchPolicy(final XmlFetchPolicy request)
	    throws WrongArgumentException, InternalServerErrorException {
	try {
	    MyObjects.requireNonNull(request, "request");

	    final Policy policy = convertPolicyShort(request);

	    try {
		PolicyCalculation.calculatePolicyCost(policy);
	    } catch (final CalculationFailed e) {
		throw new InternalServerErrorException(
			String.format("Calculation failed. %1$s. Ask to support team for details. ", e.getMessage()),
			e);
	    }

	    final XmlPolicyInfo response = convertPolicyShortToFull(request);

	    response.setCost(policy.getCalculation().getCalculatedPremiumCost());
	    return response;
	} catch (IllegalArgumentException | IllegalStateException e) {
	    logger.DEBUG.log(e);
	    throw new WrongArgumentException(e);
	} catch (final RuntimeException e) {
	    logger.SEVERE.log(e);
	    throw new InternalServerErrorException(e);
	}
    }

}

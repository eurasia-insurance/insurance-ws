package tech.lapsa.insurance.ws.rs.app;

import static tech.lapsa.javax.rs.utility.RESTUtils.*;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.lapsa.international.phone.PhoneFormatException;
import com.lapsa.international.phone.PhoneNumber;
import com.lapsa.international.phone.validators.ValidPhoneNumber;

import tech.lapsa.insurance.facade.PingClient.PingClientRemote;
import tech.lapsa.java.commons.exceptions.IllegalState;
import tech.lapsa.java.commons.naming.MyNaming;
import tech.lapsa.javax.rs.utility.RESTUtils;
import tech.lapsa.javax.validation.NotNullValue;
import tech.lapsa.javax.validation.ValidEmail;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;
import tech.lapsa.kz.taxpayer.validators.ValidTaxpayerNumber;
import tech.lapsa.kz.vehicle.VehicleRegNumber;
import tech.lapsa.kz.vehicle.validators.ValidVehicleRegNumber;

@Path("/check")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@PermitAll
public class CheckWS extends ALanguageDetectorWS {

    @Deprecated
    @GET
    @Path("/phone/{phoneNumber}")
    public Response testPhoneGET(
	    @NotNullValue @ValidPhoneNumber @PathParam("phoneNumber") final PhoneNumber phoneNumber) {
	return _testPhoneNumber(phoneNumber);
    }

    @GET
    @Path("/phoneNumber/{phoneNumber}")
    public Response testPhoneNumberGET(
	    @NotNullValue @ValidPhoneNumber @PathParam("phoneNumber") final PhoneNumber phoneNumber) {
	return _testPhoneNumber(phoneNumber);
    }

    private Response _testPhoneNumber(final PhoneNumber phoneNumber) {
	return responseOk(phoneNumber, getLocaleOrDefault());
    }

    @Deprecated
    @GET
    @Path("/idNumber/{taxpayerNumber}")
    public Response testIdNumberGET(
	    @NotNullValue @ValidTaxpayerNumber @PathParam("taxpayerNumber") final TaxpayerNumber taxpayerNumber) {
	return _testTaxpayerNumber(taxpayerNumber);
    }

    @GET
    @Path("/taxpayerNumber/{taxpayerNumber}")
    public Response checkTaxpayerNumberGET(
	    @NotNullValue @ValidTaxpayerNumber @PathParam("taxpayerNumber") final TaxpayerNumber taxpayerNumber) {
	return _testTaxpayerNumber(taxpayerNumber);
    }

    protected Response _testTaxpayerNumber(final TaxpayerNumber taxpayerNumber) {
	return responseOk(taxpayerNumber, getLocaleOrDefault());
    }

    @GET
    @Path("/vehicleRegNumber/{vehicleRegNumber}")
    public Response checkTaxpayerNumberGET(
	    @NotNullValue @ValidVehicleRegNumber @PathParam("vehicleRegNumber") final VehicleRegNumber vehicleRegNumber) {
	return _testVehicleRegNumber(vehicleRegNumber);
    }

    protected Response _testVehicleRegNumber(final VehicleRegNumber vehicleRegNumber) {
	return responseOk(vehicleRegNumber, getLocaleOrDefault());
    }

    @GET
    @Path("/email/{email}")
    public Response testEmailGET(
	    @NotNullValue @ValidEmail @PathParam("email") final String email)
	    throws PhoneFormatException {
	return _testEmail(email);
    }

    protected Response _testEmail(final String email) {
	final Map<String, String> response = new HashMap<>();
	response.put("email", email);
	return responseOk(response, getLocaleOrDefault());
    }

    @GET
    @Path("/ping")
    public Response testPingGET() {
	return _testPing();
    }

    protected Response _testPing() {
	try {
	    MyNaming.lookupEJB(IllegalStateException::new,
		    PingClientRemote.APPLICATION_NAME,
		    PingClientRemote.MODULE_NAME,
		    PingClientRemote.BEAN_NAME,
		    PingClientRemote.class) //
		    .fullPing();
	    return responseOk(0);
	} catch (IllegalState | IllegalStateException e) {
	    return RESTUtils.response(Status.SERVICE_UNAVAILABLE, e.getMessage());
	}
    }

}

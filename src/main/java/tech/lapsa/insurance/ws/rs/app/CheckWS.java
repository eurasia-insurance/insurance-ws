package tech.lapsa.eurasia36.ws.rs.app;

import static com.lapsa.utils.RESTUtils.*;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lapsa.international.internet.validators.ValidEmail;
import com.lapsa.international.phone.PhoneFormatException;
import com.lapsa.international.phone.PhoneNumber;
import com.lapsa.international.phone.validators.ValidPhoneNumber;
import com.lapsa.kz.idnumber.validators.ValidIdNumber;
import com.lapsa.validation.NotNullValue;

@Path("/check")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@PermitAll
@Singleton
public class CheckWS extends ALanguageDetectorWS {

    @GET
    @Path("/phone/{phone}")
    public Response testPhoneGET(
	    @NotNullValue @ValidPhoneNumber @PathParam("phone") PhoneNumber phone) {
	return _testPhone(phone);
    }

    private Response _testPhone(PhoneNumber phone) {
	return responseOk(phone, getLocaleOrDefault());
    }

    @GET
    @Path("/idNumber/{idNumber}")
    public Response testIdNumberGET(@NotNullValue @ValidIdNumber @PathParam("idNumber") String idNumber) {
	return _testIdNumber(idNumber);
    }

    protected Response _testIdNumber(String idNumber) {
	Map<String, Object> response = new HashMap<>();
	response.put("idNumber", idNumber);
	return responseOk(response, getLocaleOrDefault());
    }

    @GET
    @Path("/email/{email}")
    public Response testEmailGET(
	    @NotNullValue @ValidEmail @PathParam("email") String email)
	    throws PhoneFormatException {
	return _testEmail(email);
    }

    protected Response _testEmail(String email) {
	Map<String, String> response = new HashMap<>();
	response.put("email", email);
	return responseOk(response, getLocaleOrDefault());
    }

}

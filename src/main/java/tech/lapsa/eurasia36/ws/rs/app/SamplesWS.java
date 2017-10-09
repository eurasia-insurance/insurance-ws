package kz.theeurasia.eurasia36.ws.rs.app;

import static com.lapsa.utils.RESTUtils.*;
import static kz.theeurasia.eurasia36.ws.rs.app.SamplesUtil.*;

import javax.annotation.security.PermitAll;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlCallbackRequestInfo;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlPolicyDriverInfo;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlPolicyInfo;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlPolicyRequestInfo;
import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlPolicyVehicleInfo;

@Path("/sample")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@PermitAll
@Singleton
public class SamplesWS extends ALanguageDetectorWS {

    @GET
    @Path("/driver")
    public Response driverSampleGET() {
	XmlPolicyDriverInfo sample = driverSample();
	return responseOk(sample, getLocaleOrDefault());
    }

    @GET
    @Path("/vehicle")
    public Response vehicleSampleGET() {
	XmlPolicyVehicleInfo sample = vehicleSample();
	return responseOk(sample, getLocaleOrDefault());
    }

    @GET
    @Path("/policy")
    public Response policySampleGET() {
	XmlPolicyInfo sample = policySample();
	return responseOk(sample, getLocaleOrDefault());
    }

    @GET
    @Path("/policy-request")
    public Response policyRequestSampleGET() {
	XmlPolicyRequestInfo sample = policyRequestSample();
	return responseOk(sample, getLocaleOrDefault());
    }

    @GET
    @Path("/callback-request")
    public Response callbackRequestSampleGET() {
	XmlCallbackRequestInfo sample = callbackRequestSample();
	return responseOk(sample, getLocaleOrDefault());
    }
}

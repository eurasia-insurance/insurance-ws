package tech.lapsa.insurance.ws.rs.app;

import static tech.lapsa.insurance.ws.rs.app.SamplesUtil.*;
import static tech.lapsa.javax.rs.utility.RESTUtils.*;

import javax.annotation.security.PermitAll;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tech.lapsa.insurance.ws.jaxb.entity.XmlCallbackRequestInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyDriverInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyRequestInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyVehicleInfo;

@Path("/sample")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@PermitAll
@Singleton
public class SamplesWS extends ALanguageDetectorWS {

    @GET
    @Path("/driver")
    public Response driverSampleGET() {
	final XmlPolicyDriverInfo sample = driverSample();
	return responseOk(sample, getLocaleOrDefault());
    }

    @GET
    @Path("/vehicle")
    public Response vehicleSampleGET() {
	final XmlPolicyVehicleInfo sample = vehicleSample();
	return responseOk(sample, getLocaleOrDefault());
    }

    @GET
    @Path("/policy")
    public Response policySampleGET() {
	final XmlPolicyInfo sample = policySample();
	return responseOk(sample, getLocaleOrDefault());
    }

    @GET
    @Path("/policy-request")
    public Response policyRequestSampleGET() {
	final XmlPolicyRequestInfo sample = policyRequestSample();
	return responseOk(sample, getLocaleOrDefault());
    }

    @GET
    @Path("/callback-request")
    public Response callbackRequestSampleGET() {
	final XmlCallbackRequestInfo sample = callbackRequestSample();
	return responseOk(sample, getLocaleOrDefault());
    }
}

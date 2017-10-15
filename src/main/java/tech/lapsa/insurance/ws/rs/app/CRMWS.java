package tech.lapsa.insurance.ws.rs.app;

import static com.lapsa.utils.RESTUtils.*;
import static tech.lapsa.insurance.ws.rs.app.ConverterUtil.*;

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

import com.lapsa.insurance.domain.CallbackRequest;
import com.lapsa.insurance.domain.policy.PolicyRequest;
import com.lapsa.insurance.elements.PaymentMethod;
import com.lapsa.insurance.security.InsuranceRole;

import tech.lapsa.insurance.facade.CallbackRequestFacade;
import tech.lapsa.insurance.facade.InsuranceRequestFacade;
import tech.lapsa.insurance.facade.PaymentsFacade;
import tech.lapsa.insurance.ws.auth.AuthenticatedUser;
import tech.lapsa.insurance.ws.jaxb.entity.XmlCallbackRequestInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyRequestInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlSendRequestResultInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlSendRequestResultShort;
import tech.lapsa.javax.validation.NotNullValue;

@Path("/crm")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@RolesAllowed({ InsuranceRole.ADMIN, InsuranceRole.AGENT })
@Singleton
public class CRMWS extends ALanguageDetectorWS {

    private static final String DEFAULT_SUCCESS_MESSAGE = "Success";

    @Inject
    private AuthenticatedUser authenticatedUser;

    @POST
    @Path("/send-policy-request")
    public Response sendPolicyRequestPOST(@NotNullValue @Valid XmlPolicyRequestInfo request) {
	return sendPolicyRequest(request);
    }

    private Response sendPolicyRequest(XmlPolicyRequestInfo request) {
	try {
	    XmlSendRequestResultShort reply = _sendPolicyRequest(request);
	    return responseOk(reply, getLocaleOrDefault());
	} catch (WrongArgumentException e) {
	    return responseBadRequest(e.getMessage(), getLocaleOrDefault());
	} catch (ServerException e) {
	    return responseServerError(e.getMessage(), getLocaleOrDefault());
	}
    }

    @Inject
    private InsuranceRequestFacade insuranceRequestFacade;

    private XmlSendRequestResultShort _sendPolicyRequest(XmlPolicyRequestInfo request)
	    throws WrongArgumentException, ServerException {
	PolicyRequest policy = convertPolicyRequest(request, authenticatedUser.getUser());
	insuranceRequestFacade.accept(policy);
	return new XmlSendRequestResultShort(DEFAULT_SUCCESS_MESSAGE);
    }

    @POST
    @Path("/send-policy-request-reply")
    public Response sendPolicyRequestAndReplyPOST(@NotNullValue @Valid XmlPolicyRequestInfo request) {
	return sendPolicyRequestAndReply(request);
    }

    private Response sendPolicyRequestAndReply(XmlPolicyRequestInfo request) {
	try {
	    XmlSendRequestResultInfo reply = _sendPolicyRequestAndReply(request);
	    return responseOk(reply, getLocaleOrDefault());
	} catch (WrongArgumentException e) {
	    return responseBadRequest(e.getMessage(), getLocaleOrDefault());
	} catch (ServerException e) {
	    return responseServerError(e.getMessage(), getLocaleOrDefault());
	}
    }

    @Inject
    private PaymentsFacade payments;

    private XmlSendRequestResultInfo _sendPolicyRequestAndReply(XmlPolicyRequestInfo request)
	    throws WrongArgumentException, ServerException {
	PolicyRequest policy = convertPolicyRequest(request, authenticatedUser.getUser());
	PolicyRequest saved = insuranceRequestFacade.acceptAndReply(policy);
	XmlSendRequestResultInfo reply = new XmlSendRequestResultInfo(DEFAULT_SUCCESS_MESSAGE, saved.getId());
	if (saved.getPayment() != null && saved.getPayment().getMethod() == PaymentMethod.PAYCARD_ONLINE) {
	    String invoiceId = saved.getPayment().getExternalId();
	    reply.setInvoiceId(invoiceId);
	    reply.setPaymentLink(payments.getPaymentURI(invoiceId));
	}
	return reply;
    }

    @POST
    @Path("/send-callback-request")
    public Response sendCallbackRequestPOST(@NotNullValue @Valid XmlCallbackRequestInfo request) {
	return sendCallbackRequest(request);
    }

    private Response sendCallbackRequest(XmlCallbackRequestInfo request) {
	try {
	    XmlSendRequestResultShort reply = _sendCallbackRequest(request);
	    return responseOk(reply, getLocaleOrDefault());
	} catch (WrongArgumentException e) {
	    return responseBadRequest(e.getMessage(), getLocaleOrDefault());
	} catch (ServerException e) {
	    return responseServerError(e.getMessage(), getLocaleOrDefault());
	}
    }

    @Inject
    private CallbackRequestFacade callbackRequestFacade;

    private XmlSendRequestResultShort _sendCallbackRequest(XmlCallbackRequestInfo request)
	    throws WrongArgumentException, ServerException {
	CallbackRequest callback = convertCallbackRequest(request, authenticatedUser.getUser());
	callbackRequestFacade.accept(callback);
	return new XmlSendRequestResultShort(DEFAULT_SUCCESS_MESSAGE);
    }

    @POST
    @Path("/send-callback-request-reply")
    public Response sendCallbackRequestSyncPOST(@NotNullValue @Valid XmlCallbackRequestInfo request) {
	return sendCallbackRequestSync(request);
    }

    private Response sendCallbackRequestSync(XmlCallbackRequestInfo request) {
	try {
	    XmlSendRequestResultInfo reply = _sendCallbackRequestAndReply(request);
	    return responseOk(reply, getLocaleOrDefault());
	} catch (WrongArgumentException e) {
	    return responseBadRequest(e.getMessage(), getLocaleOrDefault());
	} catch (ServerException e) {
	    return responseServerError(e.getMessage(), getLocaleOrDefault());
	}
    }

    private XmlSendRequestResultInfo _sendCallbackRequestAndReply(XmlCallbackRequestInfo request)
	    throws WrongArgumentException, ServerException {
	CallbackRequest callback = convertCallbackRequest(request, authenticatedUser.getUser());
	CallbackRequest reply = callbackRequestFacade.acceptAndReply(callback);
	return new XmlSendRequestResultInfo(DEFAULT_SUCCESS_MESSAGE, reply.getId());
    }
}

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

import com.lapsa.insurance.domain.CallbackRequest;
import com.lapsa.insurance.domain.policy.PolicyRequest;

import tech.lapsa.insurance.facade.CallbackRequestFacade;
import tech.lapsa.insurance.facade.EpaymentConnectionFacade;
import tech.lapsa.insurance.facade.InsuranceRequestFacade;
import tech.lapsa.insurance.ws.auth.AuthenticatedUser;
import tech.lapsa.insurance.ws.auth.InsuranceSecurity;
import tech.lapsa.insurance.ws.ejb.EJBViaCDI;
import tech.lapsa.insurance.ws.jaxb.entity.XmlCallbackRequestInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyRequestInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlSendRequestResponse;
import tech.lapsa.insurance.ws.jaxb.entity.XmlSendRequestResponseFull;
import tech.lapsa.insurance.ws.jaxb.entity.XmlSendRequestResponseInvoice;
import tech.lapsa.java.commons.logging.MyLogger;
import tech.lapsa.javax.rs.utility.InternalServerErrorException;
import tech.lapsa.javax.rs.utility.WrongArgumentException;
import tech.lapsa.javax.validation.NotNullValue;

@Path("/crm")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@RolesAllowed({ InsuranceSecurity.ROLE_ADMIN, InsuranceSecurity.ROLE_AGENT })
@Singleton
public class CRMWS extends ALanguageDetectorWS {

    private static final String DEFAULT_SUCCESS_MESSAGE = "Success";

    @POST
    @Path("/send-policy-request")
    public Response sendPolicyRequestPOST(@NotNullValue @Valid XmlPolicyRequestInfo request) {
	return sendPolicyRequest(request);
    }

    private Response sendPolicyRequest(XmlPolicyRequestInfo request) {
	try {
	    XmlSendRequestResponse reply = _sendPolicyRequest(request);
	    return responseOk(reply, getLocaleOrDefault());
	} catch (WrongArgumentException e) {
	    return responseWrongArgument(e, getLocaleOrDefault());
	} catch (InternalServerErrorException e) {
	    return responseInternalServerError(e, getLocaleOrDefault());
	}
    }

    @POST
    @Path("/send-policy-request-reply")
    public Response sendPolicyRequestAndReplyPOST(@NotNullValue @Valid XmlPolicyRequestInfo request) {
	return sendPolicyRequestAndReply(request);
    }

    private Response sendPolicyRequestAndReply(XmlPolicyRequestInfo request) {
	try {
	    XmlSendRequestResponseFull reply = _sendPolicyRequestAndReply(request);
	    return responseOk(reply, getLocaleOrDefault());
	} catch (WrongArgumentException e) {
	    return responseWrongArgument(e, getLocaleOrDefault());
	} catch (InternalServerErrorException e) {
	    return responseInternalServerError(e, getLocaleOrDefault());
	}
    }

    @POST
    @Path("/send-callback-request")
    public Response sendCallbackRequestPOST(@NotNullValue @Valid XmlCallbackRequestInfo request) {
	return sendCallbackRequest(request);
    }

    private Response sendCallbackRequest(XmlCallbackRequestInfo request) {
	try {
	    XmlSendRequestResponse reply = _sendCallbackRequest(request);
	    return responseOk(reply, getLocaleOrDefault());
	} catch (WrongArgumentException e) {
	    return responseWrongArgument(e, getLocaleOrDefault());
	} catch (InternalServerErrorException e) {
	    return responseInternalServerError(e, getLocaleOrDefault());
	}
    }

    @POST
    @Path("/send-callback-request-reply")
    public Response sendCallbackRequestSyncPOST(@NotNullValue @Valid XmlCallbackRequestInfo request) {
	return sendCallbackRequestSync(request);
    }

    private Response sendCallbackRequestSync(XmlCallbackRequestInfo request) {
	try {
	    XmlSendRequestResponseFull reply = _sendCallbackRequestAndReply(request);
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
    private AuthenticatedUser authenticatedUser;

    @Inject
    @EJBViaCDI
    private InsuranceRequestFacade insuranceRequests;

    private XmlSendRequestResponse _sendPolicyRequest(XmlPolicyRequestInfo request)
	    throws WrongArgumentException, InternalServerErrorException {
	try {
	    PolicyRequest policy = convertPolicyRequest(request, authenticatedUser.getUser());
	    insuranceRequests.accept(policy);
	    return new XmlSendRequestResponse(DEFAULT_SUCCESS_MESSAGE);
	} catch (IllegalArgumentException | IllegalStateException e) {
	    logger.DEBUG.log(e);
	    throw new WrongArgumentException(e);
	} catch (RuntimeException e) {
	    logger.SEVERE.log(e);
	    throw new InternalServerErrorException(e);
	}
    }

    @Inject
    @EJBViaCDI
    private EpaymentConnectionFacade toEpayments;

    private XmlSendRequestResponseFull _sendPolicyRequestAndReply(XmlPolicyRequestInfo request)
	    throws WrongArgumentException, InternalServerErrorException {
	try {
	    final PolicyRequest policy = convertPolicyRequest(request, authenticatedUser.getUser());
	    final PolicyRequest saved = insuranceRequests.acceptAndReply(policy);
	    final String invoiceNumber = saved.getPayment().getInvoiceNumber();
	    final XmlSendRequestResponseFull reply = new XmlSendRequestResponseInvoice(DEFAULT_SUCCESS_MESSAGE,
		    saved.getId(), invoiceNumber, toEpayments.getPaymentURI(invoiceNumber));
	    return reply;
	} catch (IllegalArgumentException | IllegalStateException e) {
	    logger.DEBUG.log(e);
	    throw new WrongArgumentException(e);
	} catch (RuntimeException e) {
	    logger.SEVERE.log(e);
	    throw new InternalServerErrorException(e);
	}
    }

    @Inject
    @EJBViaCDI
    private CallbackRequestFacade callbackRequestFacade;

    private XmlSendRequestResponse _sendCallbackRequest(XmlCallbackRequestInfo request)
	    throws WrongArgumentException, InternalServerErrorException {
	try {
	    CallbackRequest callback = convertCallbackRequest(request, authenticatedUser.getUser());
	    callbackRequestFacade.accept(callback);
	    return new XmlSendRequestResponse(DEFAULT_SUCCESS_MESSAGE);
	} catch (IllegalArgumentException | IllegalStateException e) {
	    logger.DEBUG.log(e);
	    throw new WrongArgumentException(e);
	} catch (RuntimeException e) {
	    logger.SEVERE.log(e);
	    throw new InternalServerErrorException(e);
	}
    }

    private XmlSendRequestResponseFull _sendCallbackRequestAndReply(XmlCallbackRequestInfo request)
	    throws WrongArgumentException, InternalServerErrorException {
	try {
	    CallbackRequest callback = convertCallbackRequest(request, authenticatedUser.getUser());
	    CallbackRequest reply = callbackRequestFacade.acceptAndReply(callback);
	    return new XmlSendRequestResponseFull(DEFAULT_SUCCESS_MESSAGE, reply.getId());
	} catch (IllegalArgumentException | IllegalStateException e) {
	    logger.DEBUG.log(e);
	    throw new WrongArgumentException(e);
	} catch (RuntimeException e) {
	    logger.SEVERE.log(e);
	    throw new InternalServerErrorException(e);
	}
    }

}

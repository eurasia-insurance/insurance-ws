package tech.lapsa.insurance.ws.rs.app;

import static tech.lapsa.insurance.ws.rs.app.ConverterUtil.*;
import static tech.lapsa.javax.rs.utility.RESTUtils.*;

import java.net.URI;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
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

import tech.lapsa.insurance.facade.CallbackRequestFacade.CallbackRequestFacadeRemote;
import tech.lapsa.insurance.facade.EpaymentConnectionFacade.EpaymentConnectionFacadeRemote;
import tech.lapsa.insurance.facade.InsuranceRequestFacade.InsuranceRequestFacadeRemote;
import tech.lapsa.insurance.ws.auth.AuthenticatedUser;
import tech.lapsa.insurance.ws.auth.InsuranceSecurity;
import tech.lapsa.insurance.ws.jaxb.entity.XmlCallbackRequestInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyRequestInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlSendRequestResponse;
import tech.lapsa.insurance.ws.jaxb.entity.XmlSendRequestResponseFull;
import tech.lapsa.insurance.ws.jaxb.entity.XmlSendRequestResponseInvoice;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
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
    public Response sendPolicyRequestPOST(@NotNullValue @Valid final XmlPolicyRequestInfo request) {
	return sendPolicyRequest(request);
    }

    private Response sendPolicyRequest(final XmlPolicyRequestInfo request) {
	try {
	    final XmlSendRequestResponse reply = _sendPolicyRequest(request);
	    return responseOk(reply, getLocaleOrDefault());
	} catch (final WrongArgumentException e) {
	    return responseWrongArgument(e, getLocaleOrDefault());
	} catch (final InternalServerErrorException e) {
	    return responseInternalServerError(e, getLocaleOrDefault());
	}
    }

    @POST
    @Path("/send-policy-request-reply")
    public Response sendPolicyRequestAndReplyPOST(@NotNullValue @Valid final XmlPolicyRequestInfo request) {
	return sendPolicyRequestAndReply(request);
    }

    private Response sendPolicyRequestAndReply(final XmlPolicyRequestInfo request) {
	try {
	    final XmlSendRequestResponseFull reply = _sendPolicyRequestAndReply(request);
	    return responseOk(reply, getLocaleOrDefault());
	} catch (final WrongArgumentException e) {
	    return responseWrongArgument(e, getLocaleOrDefault());
	} catch (final InternalServerErrorException e) {
	    return responseInternalServerError(e, getLocaleOrDefault());
	}
    }

    @POST
    @Path("/send-callback-request")
    public Response sendCallbackRequestPOST(@NotNullValue @Valid final XmlCallbackRequestInfo request) {
	return sendCallbackRequest(request);
    }

    private Response sendCallbackRequest(final XmlCallbackRequestInfo request) {
	try {
	    final XmlSendRequestResponse reply = _sendCallbackRequest(request);
	    return responseOk(reply, getLocaleOrDefault());
	} catch (final WrongArgumentException e) {
	    return responseWrongArgument(e, getLocaleOrDefault());
	} catch (final InternalServerErrorException e) {
	    return responseInternalServerError(e, getLocaleOrDefault());
	}
    }

    @POST
    @Path("/send-callback-request-reply")
    public Response sendCallbackRequestSyncPOST(@NotNullValue @Valid final XmlCallbackRequestInfo request) {
	return sendCallbackRequestSync(request);
    }

    private Response sendCallbackRequestSync(final XmlCallbackRequestInfo request) {
	try {
	    final XmlSendRequestResponseFull reply = _sendCallbackRequestAndReply(request);
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

    @Inject
    private AuthenticatedUser authenticatedUser;

    @EJB
    private InsuranceRequestFacadeRemote insuranceRequests;

    private XmlSendRequestResponse _sendPolicyRequest(final XmlPolicyRequestInfo request)
	    throws WrongArgumentException, InternalServerErrorException {
	try {
	    final PolicyRequest policy = convertPolicyRequest(request, authenticatedUser.getUser());
	    insuranceRequests.acceptAndReply(policy);
	    return new XmlSendRequestResponse(DEFAULT_SUCCESS_MESSAGE);
	} catch (final IllegalArgument e) {
	    logger.DEBUG.log(e);
	    throw new WrongArgumentException(e);
	} catch (final RuntimeException e) {
	    logger.SEVERE.log(e);
	    throw new InternalServerErrorException(e);
	}
    }

    @EJB
    private EpaymentConnectionFacadeRemote toEpayments;

    private XmlSendRequestResponseFull _sendPolicyRequestAndReply(final XmlPolicyRequestInfo request)
	    throws WrongArgumentException, InternalServerErrorException {
	try {
	    final PolicyRequest policy = convertPolicyRequest(request, authenticatedUser.getUser());
	    final PolicyRequest saved = insuranceRequests.acceptAndReply(policy);
	    final String invoiceNumber = saved.getPayment().getInvoiceNumber();
	    final URI uri = toEpayments.getPaymentURI(invoiceNumber);
	    final XmlSendRequestResponseFull reply = new XmlSendRequestResponseInvoice(DEFAULT_SUCCESS_MESSAGE, saved.getId(), invoiceNumber, uri);
	    return reply;
	} catch (final IllegalArgument e) {
	    logger.DEBUG.log(e);
	    throw new WrongArgumentException(e);
	} catch (final RuntimeException e) {
	    logger.SEVERE.log(e);
	    throw new InternalServerErrorException(e);
	}
    }

    @EJB
    private CallbackRequestFacadeRemote callbackRequestFacade;

    private XmlSendRequestResponse _sendCallbackRequest(final XmlCallbackRequestInfo request)
	    throws WrongArgumentException, InternalServerErrorException {
	try {
	    final CallbackRequest callback = convertCallbackRequest(request, authenticatedUser.getUser());
	    callbackRequestFacade.acceptAndReply(callback);
	    return new XmlSendRequestResponse(DEFAULT_SUCCESS_MESSAGE);
	} catch (final IllegalArgument e) {
	    logger.DEBUG.log(e);
	    throw new WrongArgumentException(e);
	} catch (final RuntimeException e) {
	    logger.SEVERE.log(e);
	    throw new InternalServerErrorException(e);
	}
    }

    private XmlSendRequestResponseFull _sendCallbackRequestAndReply(final XmlCallbackRequestInfo request)
	    throws WrongArgumentException, InternalServerErrorException {
	try {
	    final CallbackRequest callback = convertCallbackRequest(request, authenticatedUser.getUser());
	    final CallbackRequest reply = callbackRequestFacade.acceptAndReply(callback);
	    return new XmlSendRequestResponseFull(DEFAULT_SUCCESS_MESSAGE, reply.getId());
	} catch (final IllegalArgument e) {
	    logger.DEBUG.log(e);
	    throw new WrongArgumentException(e);
	} catch (final RuntimeException e) {
	    logger.SEVERE.log(e);
	    throw new InternalServerErrorException(e);
	}
    }

}

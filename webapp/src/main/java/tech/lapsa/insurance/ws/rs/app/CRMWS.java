package tech.lapsa.insurance.ws.rs.app;

import static tech.lapsa.insurance.ws.rs.app.ConverterUtil.*;
import static tech.lapsa.javax.rs.utility.RESTUtils.*;

import java.net.URI;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lapsa.insurance.domain.CallbackRequest;
import com.lapsa.insurance.domain.Request;
import com.lapsa.insurance.domain.policy.PolicyRequest;

import tech.lapsa.epayment.facade.EpaymentFacade.EpaymentFacadeRemote;
import tech.lapsa.epayment.facade.InvoiceNotFound;
import tech.lapsa.insurance.facade.InsuranceRequestFacade.InsuranceRequestFacadeRemote;
import tech.lapsa.insurance.ws.auth.AuthenticatedUser;
import tech.lapsa.insurance.ws.auth.InsuranceSecurity;
import tech.lapsa.insurance.ws.jaxb.entity.XmlCallbackRequestInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyRequestInfo;
import tech.lapsa.insurance.ws.jaxb.entity.XmlSendRequestResponse;
import tech.lapsa.insurance.ws.jaxb.entity.XmlSendRequestResponseInvoice;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.java.commons.logging.MyLogger;
import tech.lapsa.javax.rs.utility.InternalServerErrorException;
import tech.lapsa.javax.rs.utility.WrongArgumentException;
import tech.lapsa.javax.validation.NotNullValue;

@Path("/crm")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@RolesAllowed({ InsuranceSecurity.ROLE_ADMIN, InsuranceSecurity.ROLE_AGENT })
public class CRMWS extends ALanguageDetectorWS {

    private static final String DEFAULT_SUCCESS_MESSAGE = "Success";

    @POST
    @Path("/send-policy-request")
    public Response sendPolicyRequestPOST(@NotNullValue @Valid final XmlPolicyRequestInfo request) {
	return sendPolicyRequest(request, insuranceRequests::newRequest);
    }

    @POST
    @Path("/send-policy-request-reply")
    @Deprecated
    public Response sendPolicyRequestSyncPOST(@NotNullValue @Valid final XmlPolicyRequestInfo request) {
	return sendPolicyRequest(request, insuranceRequests::newRequest);
    }

    @POST
    @Path("/send-policy-request-accepted")
    public Response sendPolicyRequestAcceptedPOST(@NotNullValue @Valid final XmlPolicyRequestInfo request) {
	return sendPolicyRequest(request, insuranceRequests::newAcceptedRequest);
    }

    private Response sendPolicyRequest(final XmlPolicyRequestInfo request, InsuranceRequestSendAction<PolicyRequest> sendAction) {
	try {
	    final XmlSendRequestResponse reply = _sendPolicyRequest(request, sendAction);
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

    @POST
    @Path("/send-callback-request-reply")
    @Deprecated
    public Response sendCallbackRequestSyncPOST(@NotNullValue @Valid final XmlCallbackRequestInfo request) {
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

    // PRIVATE

    private final MyLogger logger = MyLogger.newBuilder() //
	    .withNameOf(PolicyWS.class) //
	    .build();

    @Inject
    private AuthenticatedUser authenticatedUser;

    @EJB
    private InsuranceRequestFacadeRemote insuranceRequests;

    @EJB
    private EpaymentFacadeRemote epayments;

    private interface InsuranceRequestSendAction<T extends Request> {
	T apply(T t) throws IllegalArgument;
    }

    private XmlSendRequestResponse _sendPolicyRequest(final XmlPolicyRequestInfo request, InsuranceRequestSendAction<PolicyRequest> action)
	    throws WrongArgumentException, InternalServerErrorException {
	try {
	    final PolicyRequest policy = convertPolicyRequest(request, authenticatedUser.getUser());
	    final PolicyRequest saved = action.apply(policy);
	    final String invoiceNumber = saved.getPayment().getInvoiceNumber();
	    final XmlSendRequestResponse response;

	    if (MyStrings.nonEmpty(invoiceNumber)) {
		final URI uri;
		try {
		    uri = epayments.getDefaultPaymentURI(invoiceNumber);
		} catch (InvoiceNotFound e) {
		    logger.SEVERE.log(e);
		    throw new InternalServerErrorException(e);
		}
		response = new XmlSendRequestResponseInvoice(DEFAULT_SUCCESS_MESSAGE,
			saved.getId(), invoiceNumber, uri);
	    } else {
		response = new XmlSendRequestResponse(DEFAULT_SUCCESS_MESSAGE, saved.getId());
	    }
	    return response;
	} catch (final IllegalArgument e) {
	    logger.DEBUG.log(e);
	    throw new WrongArgumentException(e);
	} catch (final RuntimeException e) {
	    logger.SEVERE.log(e);
	    throw new InternalServerErrorException(e);
	}
    }

    private XmlSendRequestResponse _sendCallbackRequest(final XmlCallbackRequestInfo request)
	    throws WrongArgumentException, InternalServerErrorException {
	try {
	    final CallbackRequest callback = convertCallbackRequest(request, authenticatedUser.getUser());
	    final CallbackRequest reply = insuranceRequests.newRequest(callback);
	    return new XmlSendRequestResponse(DEFAULT_SUCCESS_MESSAGE, reply.getId());
	} catch (final IllegalArgument e) {
	    logger.DEBUG.log(e);
	    throw new WrongArgumentException(e);
	} catch (final RuntimeException e) {
	    logger.SEVERE.log(e);
	    throw new InternalServerErrorException(e);
	}
    }

}

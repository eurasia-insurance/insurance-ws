package tech.lapsa.insurance.ws.auth;

import static tech.lapsa.java.commons.function.MyExceptions.*;

import java.security.Principal;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.lapsa.insurance.domain.crm.User;

import tech.lapsa.insurance.facade.UserFacade;
import tech.lapsa.insurance.ws.ejbProducer.EJBViaCDI;
import tech.lapsa.javax.rs.security.QAuthenticatedUser;

@RequestScoped
public class AuthenticatedUser {

    @Inject
    @EJBViaCDI
    private UserFacade userFacade;

    public User getUser() {
	return user;
    }

    private User user;

    public void handleAuthenticationEvent(@Observes @QAuthenticatedUser String principalName) {
	if (user == null)
	    user = reThrowAsUnchecked(() -> userFacade.findOrCreate(principalName));
    }

    public void handleAuthenticationEvent(@Observes @QAuthenticatedUser Principal principal) {
	if (user == null)
	    user = reThrowAsUnchecked(() -> userFacade.findOrCreate(principal));
    }

}

package tech.lapsa.insurance.ws.auth;

import static tech.lapsa.java.commons.function.MyExceptions.*;

import java.security.Principal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;

import com.lapsa.insurance.domain.crm.User;

import tech.lapsa.insurance.facade.UserFacade.UserFacadeRemote;
import tech.lapsa.javax.rs.security.QAuthenticatedUser;

@RequestScoped
public class AuthenticatedUser {

    @EJB
    private UserFacadeRemote userFacade;

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

package tech.lapsa.insurance.ws.ejb;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.facade.PolicyDriverFacade;
import tech.lapsa.insurance.facade.PolicyDriverFacade.PolicyDriverFacadeRemote;

@Dependent
public class PolicyDriverFacadeProducer {

    @EJB
    private PolicyDriverFacadeRemote ejb;

    @Produces
    @EJBViaCDI
    public PolicyDriverFacade getEjb() {
	return ejb;
    }
}

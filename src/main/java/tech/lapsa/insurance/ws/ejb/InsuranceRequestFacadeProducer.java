package tech.lapsa.insurance.ws.ejb;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.facade.InsuranceRequestFacade;
import tech.lapsa.insurance.facade.InsuranceRequestFacade.InsuranceRequestFacadeRemote;

@Dependent
public class InsuranceRequestFacadeProducer {

    @EJB
    private InsuranceRequestFacadeRemote ejb;

    @Produces
    @EJBViaCDI
    public InsuranceRequestFacade getEjb() {
	return ejb;
    }
}

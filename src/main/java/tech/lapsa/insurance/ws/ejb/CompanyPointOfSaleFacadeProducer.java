package tech.lapsa.insurance.ws.ejb;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.facade.CompanyPointOfSaleFacade;
import tech.lapsa.insurance.facade.CompanyPointOfSaleFacade.CompanyPointOfSaleFacadeRemote;

@Dependent
public class CompanyPointOfSaleFacadeProducer {

    @EJB
    private CompanyPointOfSaleFacadeRemote ejb;

    @Produces
    @EJBViaCDI
    public CompanyPointOfSaleFacade getEjb() {
	return ejb;
    }
}

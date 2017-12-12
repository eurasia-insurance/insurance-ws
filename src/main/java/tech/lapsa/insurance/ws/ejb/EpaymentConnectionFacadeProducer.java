package tech.lapsa.insurance.ws.ejb;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.facade.EpaymentConnectionFacade;
import tech.lapsa.insurance.facade.EpaymentConnectionFacade.EpaymentConnectionFacadeRemote;

@Dependent
public class EpaymentConnectionFacadeCDIProducer {

    @EJB
    private EpaymentConnectionFacadeRemote ejb;

    @Produces
    @EJBViaCDI
    public EpaymentConnectionFacade getEjb() {
	return ejb;
    }
}

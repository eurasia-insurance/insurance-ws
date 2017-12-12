package tech.lapsa.insurance.ws.ejb;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.facade.PolicyVehicleFacade;
import tech.lapsa.insurance.facade.PolicyVehicleFacade.PolicyVehicleFacadeRemote;

@Dependent
public class PolicyVehicleFacadeCDIProducer {

    @EJB
    private PolicyVehicleFacadeRemote ejb;

    @Produces
    @EJBViaCDI
    public PolicyVehicleFacade getEjb() {
	return ejb;
    }
}

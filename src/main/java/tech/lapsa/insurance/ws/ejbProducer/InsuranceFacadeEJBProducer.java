package tech.lapsa.insurance.ws.ejbProducer;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.facade.CallbackRequestFacade;
import tech.lapsa.insurance.facade.CallbackRequestFacade.CallbackRequestFacadeRemote;
import tech.lapsa.insurance.facade.CompanyPointOfSaleFacade;
import tech.lapsa.insurance.facade.CompanyPointOfSaleFacade.CompanyPointOfSaleFacadeRemote;
import tech.lapsa.insurance.facade.EpaymentConnectionFacade;
import tech.lapsa.insurance.facade.EpaymentConnectionFacade.EpaymentConnectionFacadeRemote;
import tech.lapsa.insurance.facade.InsuranceRequestFacade;
import tech.lapsa.insurance.facade.InsuranceRequestFacade.InsuranceRequestFacadeRemote;
import tech.lapsa.insurance.facade.PolicyDriverFacade.PolicyDriverFacadeRemote;
import tech.lapsa.insurance.facade.PolicyVehicleFacade.PolicyVehicleFacadeRemote;
import tech.lapsa.insurance.facade.UserFacade.UserFacadeRemote;

@Dependent
public class InsuranceFacadeEJBProducer {

    @EJB
    private PolicyDriverFacadeRemote policyDriverFacade;

    @EJB
    private InsuranceRequestFacadeRemote insuranceRequestFacade;

    @EJB
    private EpaymentConnectionFacadeRemote epaymentConnectionFacade;

    @EJB
    private CallbackRequestFacadeRemote callbackRequestFacade;

    @EJB
    private PolicyVehicleFacadeRemote policyVehicleFacade;

    @EJB
    private CompanyPointOfSaleFacadeRemote companyPointOfSaleFacade;

    @EJB
    private UserFacadeRemote userFacade;

    @Produces
    @EJBViaCDI
    public PolicyDriverFacadeRemote getPolicyDriverFacade() {
	return policyDriverFacade;
    }

    @Produces
    @EJBViaCDI
    public InsuranceRequestFacade getInsuranceRequestFacade() {
	return insuranceRequestFacade;
    }

    @Produces
    @EJBViaCDI
    public EpaymentConnectionFacade getEpaymentConnectionFacade() {
	return epaymentConnectionFacade;
    }

    @Produces
    @EJBViaCDI
    public CallbackRequestFacade getCallbackRequestFacade() {
	return callbackRequestFacade;
    }

    @Produces
    @EJBViaCDI
    public PolicyVehicleFacadeRemote getPolicyVehicleFacade() {
	return policyVehicleFacade;
    }

    @Produces
    @EJBViaCDI
    public CompanyPointOfSaleFacade getCompanyPointOfSaleFacade() {
	return companyPointOfSaleFacade;
    }

    @Produces
    @EJBViaCDI
    public UserFacadeRemote getUserFacade() {
	return userFacade;
    }
}

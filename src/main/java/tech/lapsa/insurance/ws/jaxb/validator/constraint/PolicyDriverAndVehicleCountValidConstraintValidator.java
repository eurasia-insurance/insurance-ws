package tech.lapsa.insurance.ws.jaxb.validator.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyShort;
import tech.lapsa.insurance.ws.jaxb.validator.PolicyDriverAndVehicleCountValid;

public class PolicyDriverAndVehicleCountValidConstraintValidator
	implements ConstraintValidator<PolicyDriverAndVehicleCountValid, XmlPolicyShort> {

    @Override
    public void initialize(PolicyDriverAndVehicleCountValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(XmlPolicyShort value, ConstraintValidatorContext context) {
	if (value == null)
	    return true;

	if (value.getDrivers() == null)
	    return true;

	if (value.getVehicles() == null)
	    return true;

	if (value.getDrivers().length == 0 || value.getVehicles().length == 0)
	    return true;

	return (value.getDrivers().length == 1 && value.getVehicles().length >= 1)
		|| (value.getVehicles().length == 1 && value.getDrivers().length >= 1);
    }

}

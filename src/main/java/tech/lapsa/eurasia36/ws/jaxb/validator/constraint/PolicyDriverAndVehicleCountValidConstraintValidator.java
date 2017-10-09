package kz.theeurasia.eurasia36.ws.jaxb.validator.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import kz.theeurasia.eurasia36.ws.jaxb.entity.XmlPolicyShort;
import kz.theeurasia.eurasia36.ws.jaxb.validator.PolicyDriverAndVehicleCountValid;

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

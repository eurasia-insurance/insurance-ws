package tech.lapsa.eurasia36.ws.jaxb.validator.constraint;

import static tech.lapsa.eurasia36.ws.rs.app.ConverterUtil.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lapsa.insurance.calculation.CalculationFailed;
import com.lapsa.insurance.calculation.PolicyCalculation;
import com.lapsa.insurance.domain.policy.Policy;

import tech.lapsa.eurasia36.ws.jaxb.entity.XmlPolicyShort;
import tech.lapsa.eurasia36.ws.jaxb.validator.PolicyParametersValid;
import tech.lapsa.eurasia36.ws.rs.app.WrongArgumentException;

public class PolicyParametersValidConstraintValidator
	implements ConstraintValidator<PolicyParametersValid, XmlPolicyShort> {

    @Override
    public void initialize(PolicyParametersValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(XmlPolicyShort value, ConstraintValidatorContext context) {
	if (value == null)
	    return true;

	Policy policy;
	try {
	    policy = convertPolicyShort(value);
	} catch (WrongArgumentException e) {
	    exception(context, e);
	    return false;
	}
	try {
	    PolicyCalculation.calculatePolicyCost(policy);
	} catch (CalculationFailed e) {
	    exception(context, e);
	    return false;
	}
	return true;
    }

    protected void exception(ConstraintValidatorContext context, Exception e) {
//	context.disableDefaultConstraintViolation();
//	context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate() + e.getLocalizedMessage())
//		.addConstraintViolation();
    }
}

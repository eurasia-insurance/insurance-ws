package tech.lapsa.insurance.ws.jaxb.validator.constraint;

import static tech.lapsa.insurance.ws.rs.app.ConverterUtil.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import com.lapsa.insurance.domain.policy.Policy;

import tech.lapsa.insurance.calculation.CalculationFailed;
import tech.lapsa.insurance.calculation.PolicyCalculation.PolicyCalculationRemote;
import tech.lapsa.insurance.ws.jaxb.entity.XmlFetchPolicy;
import tech.lapsa.insurance.ws.jaxb.validator.PolicyParametersValid;
import tech.lapsa.java.commons.naming.MyNaming;
import tech.lapsa.javax.rs.utility.WrongArgumentException;

public class PolicyParametersValidConstraintValidator
	implements ConstraintValidator<PolicyParametersValid, XmlFetchPolicy> {

    @Override
    public void initialize(final PolicyParametersValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(final XmlFetchPolicy value, final ConstraintValidatorContext context) {
	if (value == null)
	    return true;

	final PolicyCalculationRemote policyCalculations = MyNaming.lookupEJB(ValidationException::new,
		PolicyCalculationRemote.APPLICATION_NAME,
		PolicyCalculationRemote.MODULE_NAME,
		PolicyCalculationRemote.BEAN_NAME,
		PolicyCalculationRemote.class);

	try {
	    final Policy policy;
	    try {
		policy = convertPolicyShort(value);
	    } catch (final WrongArgumentException e) {
		exception(context, e);
		return false;
	    }
	    policyCalculations.calculateAmount(policy);
	} catch (final CalculationFailed e) {
	    exception(context, e);
	    return false;
	}
	return true;
    }

    protected void exception(final ConstraintValidatorContext context, final Exception e) {
	// context.disableDefaultConstraintViolation();
	// context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()
	// + e.getLocalizedMessage())
	// .addConstraintViolation();
    }
}

package tech.lapsa.insurance.ws.jaxb.validator.constraint;

import static tech.lapsa.java.commons.function.MyExceptions.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import com.lapsa.insurance.domain.policy.PolicyDriver;

import tech.lapsa.insurance.facade.PolicyDriverFacade;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyDriverInfo;
import tech.lapsa.insurance.ws.jaxb.validator.PolicyDriverSettingsValid;
import tech.lapsa.insurance.ws.jaxb.validator.ValidationMessages;
import tech.lapsa.javax.cdi.commons.MyBeans;

public class PolicyDriverSettingsValidConstraintValidator
	implements ConstraintValidator<PolicyDriverSettingsValid, XmlPolicyDriverInfo> {

    @Override
    public void initialize(PolicyDriverSettingsValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(XmlPolicyDriverInfo value, ConstraintValidatorContext context) {
	if (value == null)
	    return true;

	PolicyDriver fetched = reThrowAsUnchecked(() ->
	//
	MyBeans.lookup(PolicyDriverFacade.class) //
		.orElseThrow(() -> new ValidationException("Cannot find an instance of " + PolicyDriverFacade.class)) //
		.getByTaxpayerNumber(value.getIdNumber())
	//
	);

	if (fetched == null)
	    return false;

	if (value.getInsuranceClass() != null && value.getInsuranceClass() != fetched.getInsuranceClassType())
	    return invalid(context, ValidationMessages.POLICY_DRIVER_SETTINGS_VALID_INVALID_CLASS);

	if (value.getAgeClass() != null && fetched.getAgeClass() != null
		&& value.getAgeClass() != fetched.getAgeClass())
	    return invalid(context, ValidationMessages.POLICY_DRIVER_SETTINGS_VALID_INVALID_AGE_CLASS);

	return true;
    }

    private boolean invalid(ConstraintValidatorContext context, String string) {
	context.disableDefaultConstraintViolation();
	context.buildConstraintViolationWithTemplate(string).addConstraintViolation();
	return false;
    }

}

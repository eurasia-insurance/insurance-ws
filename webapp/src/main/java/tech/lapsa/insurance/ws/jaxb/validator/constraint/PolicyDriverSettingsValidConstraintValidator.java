package tech.lapsa.insurance.ws.jaxb.validator.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import com.lapsa.insurance.domain.policy.PolicyDriver;

import tech.lapsa.insurance.facade.PolicyDriverFacade.PolicyDriverFacadeRemote;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyDriverInfo;
import tech.lapsa.insurance.ws.jaxb.validator.PolicyDriverSettingsValid;
import tech.lapsa.insurance.ws.jaxb.validator.ValidationMessages;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.naming.MyNaming;

public class PolicyDriverSettingsValidConstraintValidator
	implements ConstraintValidator<PolicyDriverSettingsValid, XmlPolicyDriverInfo> {

    @Override
    public void initialize(final PolicyDriverSettingsValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(final XmlPolicyDriverInfo value, final ConstraintValidatorContext context)
	    throws ValidationException {
	if (value == null)
	    return true;

	// если не указан ИИН то ничего проверить нельзя. Верим тому что
	// указано.
	if (MyObjects.isNull(value.getIdNumber()))
	    return true;

	final PolicyDriverFacadeRemote policyDrivers = MyNaming.lookupEJB(ValidationException::new,
		PolicyDriverFacadeRemote.APPLICATION_NAME,
		PolicyDriverFacadeRemote.MODULE_NAME,
		PolicyDriverFacadeRemote.BEAN_NAME,
		PolicyDriverFacadeRemote.class);

	final PolicyDriver fetched;
	try {
	    fetched = policyDrivers.getByTaxpayerNumberOrDefault(value.getIdNumber());
	} catch (final IllegalArgument e) {
	    return false;
	}

	if (value.getInsuranceClass() != null && value.getInsuranceClass() != fetched.getInsuranceClassType())
	    return invalid(context, ValidationMessages.POLICY_DRIVER_SETTINGS_VALID_INVALID_CLASS);

	if (value.getAgeClass() != null && fetched.getAgeClass() != null
		&& value.getAgeClass() != fetched.getAgeClass())
	    return invalid(context, ValidationMessages.POLICY_DRIVER_SETTINGS_VALID_INVALID_AGE_CLASS);

	return true;
    }

    private boolean invalid(final ConstraintValidatorContext context, final String string) {
	context.disableDefaultConstraintViolation();
	context.buildConstraintViolationWithTemplate(string).addConstraintViolation();
	return false;
    }

}

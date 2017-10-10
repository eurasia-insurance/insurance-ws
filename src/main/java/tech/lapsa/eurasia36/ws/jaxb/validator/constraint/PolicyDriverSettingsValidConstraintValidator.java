package tech.lapsa.eurasia36.ws.jaxb.validator.constraint;

import static com.lapsa.utils.BeanUtils.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lapsa.insurance.domain.policy.PolicyDriver;

import tech.lapsa.eurasia36.ws.jaxb.entity.XmlPolicyDriverInfo;
import tech.lapsa.eurasia36.ws.jaxb.validator.PolicyDriverSettingsValid;
import tech.lapsa.eurasia36.ws.jaxb.validator.ValidationMessages;
import tech.lapsa.insurance.facade.PolicyDriverFacade;

public class PolicyDriverSettingsValidConstraintValidator
	implements ConstraintValidator<PolicyDriverSettingsValid, XmlPolicyDriverInfo> {

    @Override
    public void initialize(PolicyDriverSettingsValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(XmlPolicyDriverInfo value, ConstraintValidatorContext context) {
	if (value == null)
	    return true;

	PolicyDriverFacade facade = getBean(PolicyDriverFacade.class);
	PolicyDriver fetched = facade.fetchByIdNumber(value.getIdNumber());

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

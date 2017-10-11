package tech.lapsa.insurance.ws.jaxb.validator.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lapsa.kz.country.KZArea;

import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyVehicleInfo;
import tech.lapsa.insurance.ws.jaxb.validator.PolicyVehicleSettingsValid;
import tech.lapsa.insurance.ws.jaxb.validator.ValidationMessages;

public class PolicyVehicleSettingsValidConstraintValidator
	implements ConstraintValidator<PolicyVehicleSettingsValid, XmlPolicyVehicleInfo> {

    @Override
    public void initialize(PolicyVehicleSettingsValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(XmlPolicyVehicleInfo value, ConstraintValidatorContext context) {
	if (value == null)
	    return true;

	if (value.getTemporaryEntry() == null)
	    return true;

	if (value.getArea() == null)
	    return true;

	if (value.getMajorCity() == null)
	    return true;

	if (value.getTemporaryEntry()) {
	    if (!KZArea.UNDEFINED.equals(value.getArea()))
		return invalid(context, ValidationMessages.POLICY_VEHICLE_SETTINGS_VALID_TEMPORARY_ENTRY_AREA_MUST_DEFINED);
	    if (value.getMajorCity())
		return invalid(context,
			ValidationMessages.POLICY_VEHICLE_SETTINGS_VALID_TEMPORARY_ENTRY_MAJOR_CITY_MUST_FALSE);
	} else {
	    if (KZArea.UNDEFINED.equals(value.getArea()))
		return invalid(context, ValidationMessages.POLICY_VEHICLE_SETTINGS_VALID_AREA_MUST_NOT_UNDEFINED);
	    if ((KZArea.GALM.equals(value.getArea()) || KZArea.GAST.equals(value.getArea())) && !value.getMajorCity())
		return invalid(context, ValidationMessages.POLICY_VEHICLE_SETTINGS_VALID_AREA_MUST_BE_MAJOR_CITY);
	}

	return true;
    }

    private boolean invalid(ConstraintValidatorContext context, String string) {
	context.disableDefaultConstraintViolation();
	context.buildConstraintViolationWithTemplate(string).addConstraintViolation();
	return false;
    }

}

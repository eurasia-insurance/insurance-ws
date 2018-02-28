package tech.lapsa.insurance.ws.jaxb.validator.constraint;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lapsa.kz.country.KZArea;

import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyVehicleInfo;
import tech.lapsa.insurance.ws.jaxb.validator.PolicyVehicleSettingsValid;
import tech.lapsa.insurance.ws.jaxb.validator.ValidationMessages;
import tech.lapsa.java.commons.function.MyOptionals;

public class PolicyVehicleSettingsValidConstraintValidator
	implements ConstraintValidator<PolicyVehicleSettingsValid, XmlPolicyVehicleInfo> {

    @Override
    public void initialize(final PolicyVehicleSettingsValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(final XmlPolicyVehicleInfo value, final ConstraintValidatorContext context) {
	if (value == null)
	    return true;

	final Optional<Boolean> optTemporaryEntry = MyOptionals.of(value.getTemporaryEntry());
	final Optional<KZArea> optArea = MyOptionals.of(value.getArea());
	final Optional<Boolean> optMajorCity = MyOptionals.of(value.getMajorCity());
		
	if (!optTemporaryEntry.isPresent())
	    return invalid(context, ValidationMessages.POLICY_VEHICLE_SETTINGS_VALID_TEMPORARY_ENTRY_MUST_NOT_NULL);
	
	final boolean temporaryEntry = optTemporaryEntry.get().booleanValue();

	if (temporaryEntry) {
	    if (optArea.isPresent())
		return invalid(context,
			ValidationMessages.POLICY_VEHICLE_SETTINGS_VALID_TEMPORARY_ENTRY_AREA_MUST_NULL);
	    if (optMajorCity.isPresent())
		return invalid(context,
			ValidationMessages.POLICY_VEHICLE_SETTINGS_VALID_TEMPORARY_ENTRY_MAJOR_CITY_MUST_NULL);
	} else {
	    if (!optArea.isPresent() || optArea.get().equals(KZArea.UNDEFINED))
		return invalid(context, ValidationMessages.POLICY_VEHICLE_SETTINGS_VALID_AREA_MUST_NOT_NULL);

	    if (!optMajorCity.isPresent())
		return invalid(context, ValidationMessages.POLICY_VEHICLE_SETTINGS_VALID_MAJOR_CITY_MUST_NOT_NULL);

	    final KZArea area = optArea.get();
	    final boolean majorCity = optMajorCity.get().booleanValue();
	    
	    if (area.in(KZArea.GALM, KZArea.GAST) && !majorCity)
		return invalid(context, ValidationMessages.POLICY_VEHICLE_SETTINGS_VALID_MAJOR_CITY_MUST_TRUE);
	}

	return true;
    }

    private boolean invalid(final ConstraintValidatorContext context, final String string) {
	context.disableDefaultConstraintViolation();
	context.buildConstraintViolationWithTemplate(string).addConstraintViolation();
	return false;
    }

}

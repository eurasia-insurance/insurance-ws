package tech.lapsa.insurance.ws.jaxb.validator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import tech.lapsa.insurance.ws.jaxb.validator.constraint.PolicyVehicleSettingsValidConstraintValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PolicyVehicleSettingsValidConstraintValidator.class)
public @interface PolicyVehicleSettingsValid {

    String message() default ValidationMessages.POLICY_VEHICLE_SETTINGS_VALID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

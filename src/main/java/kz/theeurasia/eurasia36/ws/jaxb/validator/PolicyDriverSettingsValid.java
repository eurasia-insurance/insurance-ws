package kz.theeurasia.eurasia36.ws.jaxb.validator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import kz.theeurasia.eurasia36.ws.jaxb.validator.constraint.PolicyDriverSettingsValidConstraintValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PolicyDriverSettingsValidConstraintValidator.class)
public @interface PolicyDriverSettingsValid {

    String message() default ValidationMessages.POLICY_DRIVER_SETTINGS_VALID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

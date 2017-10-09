package tech.lapsa.eurasia36.ws.jaxb.validator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import tech.lapsa.eurasia36.ws.jaxb.validator.constraint.PolicyCostValidConstraintValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PolicyCostValidConstraintValidator.class)
public @interface PolicyCostValid {

    String message() default ValidationMessages.POLICY_COST_VALID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

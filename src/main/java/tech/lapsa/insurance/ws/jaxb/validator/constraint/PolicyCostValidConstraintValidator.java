package tech.lapsa.insurance.ws.jaxb.validator.constraint;

import static tech.lapsa.insurance.ws.rs.app.ConverterUtil.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lapsa.insurance.domain.policy.Policy;

import tech.lapsa.insurance.calculation.CalculationFailed;
import tech.lapsa.insurance.calculation.PolicyCalculation;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyInfo;
import tech.lapsa.insurance.ws.jaxb.validator.PolicyCostValid;
import tech.lapsa.insurance.ws.rs.app.WrongArgumentException;

public class PolicyCostValidConstraintValidator implements ConstraintValidator<PolicyCostValid, XmlPolicyInfo> {

    @Override
    public void initialize(PolicyCostValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(XmlPolicyInfo value, ConstraintValidatorContext context) {
	if (value == null)
	    return true;

	if (value.getCost() == null)
	    return true;

	Policy policy;
	try {
	    policy = convertPolicyShort(value);
	} catch (WrongArgumentException e) {
	    return true;
	}
	try {
	    PolicyCalculation.calculatePolicyCost(policy);
	} catch (CalculationFailed e) {
	    return true; // означает что была ошибка расчета. Проверка должна
			 // быть пройдена, т.к. до сути проверки не дошло
	}
	boolean result = policy.getCalculation().getCalculatedPremiumCost() == value.getCost();
	return result;
    }

}
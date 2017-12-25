package tech.lapsa.insurance.ws.jaxb.validator.constraint;

import static tech.lapsa.insurance.ws.rs.app.ConverterUtil.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import com.lapsa.insurance.domain.CalculationData;
import com.lapsa.insurance.domain.policy.Policy;

import tech.lapsa.insurance.calculation.CalculationFailed;
import tech.lapsa.insurance.calculation.PolicyCalculation.PolicyCalculationRemote;
import tech.lapsa.insurance.ws.jaxb.entity.XmlPolicyInfo;
import tech.lapsa.insurance.ws.jaxb.validator.PolicyCostValid;
import tech.lapsa.java.commons.naming.MyNaming;
import tech.lapsa.javax.rs.utility.WrongArgumentException;

public class PolicyCostValidConstraintValidator implements ConstraintValidator<PolicyCostValid, XmlPolicyInfo> {

    @Override
    public void initialize(final PolicyCostValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(final XmlPolicyInfo value, final ConstraintValidatorContext context) {
	if (value == null)
	    return true;

	if (value.getCost() == null)
	    return true;

	final PolicyCalculationRemote policyCalculations = MyNaming.lookupEJB(ValidationException::new,
		PolicyCalculationRemote.APPLICATION_NAME,
		PolicyCalculationRemote.MODULE_NAME,
		PolicyCalculationRemote.BEAN_NAME,
		PolicyCalculationRemote.class);

	final CalculationData calculation;
	try {
	    final Policy policy;
	    try {
		policy = convertPolicyShort(value);
	    } catch (final WrongArgumentException e) {
		return true;
	    }
	    calculation = policyCalculations.calculateAmount(policy);
	} catch (final CalculationFailed e) {
	    return true; // означает что была ошибка расчета. Проверка должна
			 // быть пройдена, т.к. до сути проверки не дошло
	}
	final Double test = value.getCost();
	final Double right = calculation.getAmount();
	final boolean result = test.equals(right);
	return result;
    }
}

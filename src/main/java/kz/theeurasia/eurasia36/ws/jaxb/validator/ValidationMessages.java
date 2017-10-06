package kz.theeurasia.eurasia36.ws.jaxb.validator;

public class ValidationMessages {
    public static final String POLICY_COST_VALID = "{kz.theeurasia.eurasia36.ws.jaxb.validator.PolicyCostValidConstraintValidator.message}";
    public static final String POLICY_DRIVER_AND_VEHICLE_COUNT_VALID = "{kz.theeurasia.eurasia36.ws.jaxb.validator.PolicyDriverAndVehicleCountValid.message}";
    public static final String POLICY_PARAMETERS_VALID = "{kz.theeurasia.eurasia36.ws.jaxb.validator.PolicyParametersValid.message}";

    public static final String POLICY_VEHICLE_SETTINGS_VALID = "{kz.theeurasia.eurasia36.ws.jaxb.validator.PolicyVehicleSettingsValid.message}";
    public static final String POLICY_VEHICLE_SETTINGS_VALID_TEMPORARY_ENTRY_AREA_MUST_DEFINED = "{kz.theeurasia.eurasia36.ws.jaxb.validator.PolicyVehicleSettingsValid.message.temporaryEntryAreaMustUndefined}";
    public static final String POLICY_VEHICLE_SETTINGS_VALID_TEMPORARY_ENTRY_MAJOR_CITY_MUST_FALSE = "{kz.theeurasia.eurasia36.ws.jaxb.validator.PolicyVehicleSettingsValid.message.temporaryEntryMajorCityMustFalse}";
    public static final String POLICY_VEHICLE_SETTINGS_VALID_AREA_MUST_NOT_UNDEFINED = "{kz.theeurasia.eurasia36.ws.jaxb.validator.PolicyVehicleSettingsValid.message.areaMustNotUndefined}";
    public static final String POLICY_VEHICLE_SETTINGS_VALID_AREA_MUST_BE_MAJOR_CITY = "{kz.theeurasia.eurasia36.ws.jaxb.validator.PolicyVehicleSettingsValid.message.mustBeMajorCity}";

    public static final String POLICY_DRIVER_SETTINGS_VALID = "{kz.theeurasia.eurasia36.ws.jaxb.validator.PolicyDriverSettingsValid.message}";
    public static final String POLICY_DRIVER_SETTINGS_VALID_INVALID_CLASS = "{kz.theeurasia.eurasia36.ws.jaxb.validator.PolicyDriverSettingsValid.invalidInsuranceClass}";
    public static final String POLICY_DRIVER_SETTINGS_VALID_INVALID_AGE_CLASS = "{kz.theeurasia.eurasia36.ws.jaxb.validator.PolicyDriverSettingsValid.message.invalidAgeClass}";

    public static final String EBILL_VALID = "{kz.theeurasia.eurasia36.ws.jaxb.validator.EbillValid.message}";

    public static final String[] ALL = new String[] {
	    POLICY_COST_VALID,
	    POLICY_DRIVER_AND_VEHICLE_COUNT_VALID,
	    POLICY_PARAMETERS_VALID,

	    POLICY_VEHICLE_SETTINGS_VALID,
	    POLICY_VEHICLE_SETTINGS_VALID_TEMPORARY_ENTRY_AREA_MUST_DEFINED,
	    POLICY_VEHICLE_SETTINGS_VALID_TEMPORARY_ENTRY_MAJOR_CITY_MUST_FALSE,
	    POLICY_VEHICLE_SETTINGS_VALID_AREA_MUST_NOT_UNDEFINED,
	    POLICY_VEHICLE_SETTINGS_VALID_AREA_MUST_BE_MAJOR_CITY,

	    POLICY_DRIVER_SETTINGS_VALID,
	    POLICY_DRIVER_SETTINGS_VALID_INVALID_CLASS,
	    POLICY_DRIVER_SETTINGS_VALID_INVALID_AGE_CLASS,

	    EBILL_VALID
    };

}

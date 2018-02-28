package tech.lapsa.insurance.ws.jaxb.validator;

public class ValidationMessages {
    public static final String POLICY_COST_VALID = "{tech.lapsa.insurance.ws.jaxb.validator.PolicyCostValidConstraintValidator.message}";
    public static final String POLICY_DRIVER_AND_VEHICLE_COUNT_VALID = "{tech.lapsa.insurance.ws.jaxb.validator.PolicyDriverAndVehicleCountValid.message}";
    public static final String POLICY_PARAMETERS_VALID = "{tech.lapsa.insurance.ws.jaxb.validator.PolicyParametersValid.message}";

    public static final String POLICY_VEHICLE_SETTINGS_VALID = "{tech.lapsa.insurance.ws.jaxb.validator.PolicyVehicleSettingsValid.message}";

    public static final String POLICY_VEHICLE_SETTINGS_VALID_TEMPORARY_ENTRY_MUST_NOT_NULL = "{tech.lapsa.insurance.ws.jaxb.validator.PolicyVehicleSettingsValid.message.temporaryEntryMustNotNull}";;
    public static final String POLICY_VEHICLE_SETTINGS_VALID_TEMPORARY_ENTRY_AREA_MUST_NULL = "{tech.lapsa.insurance.ws.jaxb.validator.PolicyVehicleSettingsValid.message.temporaryEntryAreaMustUndefined}";
    public static final String POLICY_VEHICLE_SETTINGS_VALID_TEMPORARY_ENTRY_MAJOR_CITY_MUST_NULL = "{tech.lapsa.insurance.ws.jaxb.validator.PolicyVehicleSettingsValid.message.temporaryEntryMajorCityMustFalse}";
    public static final String POLICY_VEHICLE_SETTINGS_VALID_AREA_MUST_NOT_NULL = "{tech.lapsa.insurance.ws.jaxb.validator.PolicyVehicleSettingsValid.message.areaMustNotUndefined}";
    public static final String POLICY_VEHICLE_SETTINGS_VALID_MAJOR_CITY_MUST_NOT_NULL = "{tech.lapsa.insurance.ws.jaxb.validator.PolicyVehicleSettingsValid.message.majorCityMustNotNull}";
    public static final String POLICY_VEHICLE_SETTINGS_VALID_MAJOR_CITY_MUST_TRUE = "{tech.lapsa.insurance.ws.jaxb.validator.PolicyVehicleSettingsValid.message.mustBeMajorCity}";

    public static final String POLICY_DRIVER_SETTINGS_VALID = "{tech.lapsa.insurance.ws.jaxb.validator.PolicyDriverSettingsValid.message}";
    public static final String POLICY_DRIVER_SETTINGS_VALID_INVALID_CLASS = "{tech.lapsa.insurance.ws.jaxb.validator.PolicyDriverSettingsValid.invalidInsuranceClass}";
    public static final String POLICY_DRIVER_SETTINGS_VALID_INVALID_AGE_CLASS = "{tech.lapsa.insurance.ws.jaxb.validator.PolicyDriverSettingsValid.message.invalidAgeClass}";

    public static final String[] ALL = new String[] {
	    POLICY_COST_VALID,
	    POLICY_DRIVER_AND_VEHICLE_COUNT_VALID,
	    POLICY_PARAMETERS_VALID,

	    POLICY_VEHICLE_SETTINGS_VALID,
	    POLICY_VEHICLE_SETTINGS_VALID_TEMPORARY_ENTRY_MUST_NOT_NULL,
	    POLICY_VEHICLE_SETTINGS_VALID_TEMPORARY_ENTRY_AREA_MUST_NULL,
	    POLICY_VEHICLE_SETTINGS_VALID_TEMPORARY_ENTRY_MAJOR_CITY_MUST_NULL,
	    POLICY_VEHICLE_SETTINGS_VALID_AREA_MUST_NOT_NULL,
	    POLICY_VEHICLE_SETTINGS_VALID_MAJOR_CITY_MUST_NOT_NULL,
	    POLICY_VEHICLE_SETTINGS_VALID_MAJOR_CITY_MUST_TRUE,

	    POLICY_DRIVER_SETTINGS_VALID,
	    POLICY_DRIVER_SETTINGS_VALID_INVALID_CLASS,
	    POLICY_DRIVER_SETTINGS_VALID_INVALID_AGE_CLASS
    };

}

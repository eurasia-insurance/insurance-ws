package tech.lapsa.insurance.ws.auth;

import java.util.stream.Stream;

import com.lapsa.utils.security.SecurityRole;

public final class InsuranceRoles {

public final class InsuranceSecurity {

    private InsuranceSecurity() {
    }

    public static final String ADMIN = "admin";
    public static final String SPECIALIST = "specialist";
    public static final String REPORTER = "reporter";
    public static final String AGENT = "agent";

    public static String[] values() {
	return new String[] { ADMIN, SPECIALIST, REPORTER, AGENT };
    }

    public static enum Role implements SecurityRole {

	ADMIN(InsuranceRoles.ADMIN),
	SPECIALIST(InsuranceRoles.SPECIALIST),
	REPORTER(InsuranceRoles.REPORTER),
	AGENT(InsuranceRoles.AGENT);

	private final String roleName;

	private Role(String roleName) {
	    this.roleName = roleName;
	}

	public static Role forName(String roleName) {
	    return Stream.of(values()) //
		    .filter(x -> x.roleName().equals(roleName)) //
		    .findAny().orElse(null);
	}

	@Override
	public String roleName() {
	    return roleName;
	}
    }
}

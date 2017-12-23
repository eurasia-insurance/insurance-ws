package tech.lapsa.insurance.ws.auth;

import java.util.Set;
import java.util.stream.Stream;

import com.lapsa.utils.security.SecurityRole;

import tech.lapsa.java.commons.function.MySets;

public final class InsuranceSecurity {

    private InsuranceSecurity() {
    }

    public static final String ROLE_ADMIN = "role-admin";
    public static final String ROLE_SPECIALIST = "role-specialist";
    public static final String ROLE_REPORTER = "role-reporter";
    public static final String ROLE_AGENT = "role-agent";

    public static final Set<String> ALL = MySets.of(ROLE_ADMIN, ROLE_SPECIALIST, ROLE_REPORTER, ROLE_AGENT);

    public static enum Role implements SecurityRole {

	ADMIN(InsuranceSecurity.ROLE_ADMIN),
	SPECIALIST(InsuranceSecurity.ROLE_SPECIALIST),
	REPORTER(InsuranceSecurity.ROLE_REPORTER),
	AGENT(InsuranceSecurity.ROLE_AGENT);

	private final String roleName;

	private Role(final String roleName) {
	    this.roleName = roleName;
	}

	public static Role forName(final String roleName) {
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

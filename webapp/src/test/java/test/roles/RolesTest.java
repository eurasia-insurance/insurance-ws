package test.roles;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import tech.lapsa.insurance.ws.auth.InsuranceSecurity;

public class RolesTest {

    @Test
    public void simpleTest() {
	InsuranceSecurity.ALL.stream()
		.map(InsuranceSecurity.Role::forName)
		.forEach(Assert::assertNotNull);

	assertTrue(InsuranceSecurity.Role.values().length == InsuranceSecurity.ALL.size());
    }
}

package tech.lapsa.insurance.ws.ejb;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

@Qualifier
@Retention(RUNTIME)
@Target({ FIELD, TYPE, METHOD })
public @interface EJBViaCDI {

    public static EJBViaCDI INSTANCE = new EJBViaCDI() {
	@Override
	public Class<? extends Annotation> annotationType() {
	    return EJBViaCDI.class;
	}
    };
}

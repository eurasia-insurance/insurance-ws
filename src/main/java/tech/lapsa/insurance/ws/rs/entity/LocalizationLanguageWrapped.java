package tech.lapsa.insurance.ws.rs.entity;

import com.lapsa.international.localization.LocalizationLanguage;

import tech.lapsa.java.commons.function.MyObjects;

public final class LocalizationLanguageWrapped {

    private final LocalizationLanguage wrapped;

    private LocalizationLanguageWrapped(LocalizationLanguage wrapped) {
	MyObjects.requireNonNull(wrapped, "wrapped value must not be a null value");
	this.wrapped = wrapped;
    }

    public LocalizationLanguage getLanguage() {
	return wrapped;
    }

    public static LocalizationLanguageWrapped create(LocalizationLanguage ll) {
	if (ll == null)
	    return null;
	return new LocalizationLanguageWrapped(ll);
    }

}

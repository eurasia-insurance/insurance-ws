package tech.lapsa.eurasia36.ws.rs.provider.converter;

import javax.ws.rs.ext.ParamConverter;

import com.lapsa.international.localization.LocalizationLanguage;

import tech.lapsa.eurasia36.ws.rs.entity.LocalizationLanguageWrapped;

public class LocalizationLanguageWrappedByLanguageTagParamConverter implements ParamConverter<LocalizationLanguageWrapped> {

    @Override
    public LocalizationLanguageWrapped fromString(String v) {
	if (v == null || v.trim().isEmpty())
	    return null;
	LocalizationLanguage ll = LocalizationLanguage.byTag(v);
	if (ll == null)
	    return null;
	return LocalizationLanguageWrapped.create(ll);
    }

    @Override
    public String toString(LocalizationLanguageWrapped v) {
	if (v == null)
	    return null;
	return v.getLanguage().getTag();
    }

}

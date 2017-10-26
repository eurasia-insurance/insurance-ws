package tech.lapsa.insurance.ws.rs.provider.converter;

import java.util.Map.Entry;

import javax.ws.rs.ext.ParamConverter;

import com.lapsa.international.localization.LocalizationLanguage;

import tech.lapsa.insurance.ws.rs.entity.LocalizationLanguageWrapped;
import tech.lapsa.java.commons.function.MyMaps;
import tech.lapsa.javax.rs.params.MyParamConverterProvider;

public class LocalizationLanguageWrappedByLanguageTagParamConverter
	implements ParamConverter<LocalizationLanguageWrapped>, MyParamConverterProvider<LocalizationLanguageWrapped> {

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

    private static Entry<Class<LocalizationLanguageWrapped>, ParamConverter<LocalizationLanguageWrapped>> ENTRY = MyMaps
	    .entry(LocalizationLanguageWrapped.class, new LocalizationLanguageWrappedByLanguageTagParamConverter());

    @Override
    public Entry<Class<LocalizationLanguageWrapped>, ParamConverter<LocalizationLanguageWrapped>> entry() {
	return ENTRY;
    }

}

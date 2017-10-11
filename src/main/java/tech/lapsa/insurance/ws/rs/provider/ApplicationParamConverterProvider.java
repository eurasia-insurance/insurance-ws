package tech.lapsa.insurance.ws.rs.provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import com.lapsa.international.phone.PhoneNumber;
import com.lapsa.international.phone.converter.rs.PhoneNumberParamConverter;

import tech.lapsa.insurance.ws.rs.entity.LocalizationLanguageWrapped;
import tech.lapsa.insurance.ws.rs.provider.converter.LocalizationLanguageWrappedByLanguageTagParamConverter;

@Provider
public class ApplicationParamConverterProvider implements ParamConverterProvider {

    @SuppressWarnings("unchecked")
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
	if (LocalizationLanguageWrapped.class.equals(rawType))
	    return (ParamConverter<T>) new LocalizationLanguageWrappedByLanguageTagParamConverter();
	if (PhoneNumber.class.equals(rawType))
	    return (ParamConverter<T>) new PhoneNumberParamConverter();
	return null;
    }

}

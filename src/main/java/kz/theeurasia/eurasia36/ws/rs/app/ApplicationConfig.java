package kz.theeurasia.eurasia36.ws.rs.app;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.TracingConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.lapsa.jerseyExtensions.authorization.AutheniticationFilterProvider;
import com.lapsa.jerseyExtensions.localization.ValidationMessagesLocalizationProvider;

import kz.theeurasia.eurasia36.ws.rs.provider.ApplicationParamConverterProvider;
import kz.theeurasia.eurasia36.ws.rs.provider.JacksonObjectMapperProvider;

@ApplicationPath("/" + WSPathNames.WS)
public class ApplicationConfig extends Application {

    private final Set<Class<?>> classes;

    private final Map<String, Object> properties;

    public ApplicationConfig() {

	classes = Stream.<Class<?>> builder() //
		.add(JacksonFeature.class) //
		.add(JacksonObjectMapperProvider.class) //
		.add(ApplicationParamConverterProvider.class) //
		.add(AutheniticationFilterProvider.class) //
		.add(RolesAllowedDynamicFeature.class) //
		.add(ValidationMessagesLocalizationProvider.class) //

		// permitted
		.add(SamplesWS.class) //
		.add(DictionariesWS.class) //
		.add(POSWS.class) //
		.add(CheckWS.class) //

		// secured
		.add(CRMWS.class) //
		.add(PolicyWS.class) //
		.add(EbillWS.class) //

		// qazkom
		.add(QazkomWS.class) // ;

		//
		.build() //
		.collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));

	properties = Stream.<Map.Entry<String, Object>> builder() //
		.add(immutableEntry(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true)) //
		.add(immutableEntry(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true)) //
		.add(immutableEntry(ServerProperties.APPLICATION_NAME, "Eurasia36 WS API")) //
		.add(immutableEntry(ServerProperties.TRACING, TracingConfig.ON_DEMAND.toString())) //
		.add(immutableEntry(ServerProperties.MOXY_JSON_FEATURE_DISABLE, true)) //
		.build() //
		.collect(Collectors.collectingAndThen( //
			Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue),
			Collections::unmodifiableMap) //
	);

    }

    @Override
    public Set<Class<?>> getClasses() {
	return classes;
    }

    @Override
    public Map<String, Object> getProperties() {
	return properties;
    }

    // PRIVATE

    private static <K, V> Map.Entry<K, V> immutableEntry(final K key, final V value) {
	return new Map.Entry<K, V>() {
	    @Override
	    public K getKey() {
		return key;
	    }

	    @Override
	    public V getValue() {
		return value;
	    }

	    @Override
	    public V setValue(V value) {
		throw new UnsupportedOperationException();
	    }
	};
    }

}

package ${package}.webapp.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Class defining Jackson as a JSON Provider, with additional configuration.
 */
public class JacksonJaxbJsonProviderConfig extends ResourceConfig {


    public JacksonJaxbJsonProviderConfig() {
        final ObjectMapper om = new ObjectMapper();
        om.disable(DeserializationFeature.ACCEPT_FLOAT_AS_INT);
        om.enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);

        // Allows enums be serialized/deserialized using toString method (instead of name)
        om.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        om.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);


        register(new JacksonJaxbJsonProvider(om, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS));
    }


}
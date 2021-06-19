package pt.andronikus.pnia.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import pt.andronikus.pnia.api.BusinessSectorCounter;

import java.io.IOException;

public class BusinessSectorCounterSerializer extends JsonSerializer<BusinessSectorCounter> {
    @Override
    public void serialize(BusinessSectorCounter businessSectorCounter, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        businessSectorCounter.getBusinessSectorCounter().forEach( (k,v) -> {
            try {
                jsonGenerator.writeNumberField(k,v);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        jsonGenerator.writeEndObject();
    }
}

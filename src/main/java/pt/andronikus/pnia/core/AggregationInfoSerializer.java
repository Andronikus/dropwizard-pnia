package pt.andronikus.pnia.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import pt.andronikus.pnia.api.AggregationInfo;

import java.io.IOException;

public class AggregationInfoSerializer extends JsonSerializer<AggregationInfo> {
    @Override
    public void serialize(AggregationInfo aggregationInfo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        aggregationInfo.getAggInfo().forEach((k,v) -> {
            try {
                jsonGenerator.writeObjectField(k, v);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        jsonGenerator.writeEndObject();
    }
}

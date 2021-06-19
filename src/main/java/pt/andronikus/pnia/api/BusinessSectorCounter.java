package pt.andronikus.pnia.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pt.andronikus.pnia.core.BusinessSectorCounterSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@JsonSerialize(using = BusinessSectorCounterSerializer.class)
public class BusinessSectorCounter {
    @JsonProperty
    private final Map<String, Integer> businessSectorCounter;

    public Map<String, Integer> getBusinessSectorCounter() {
        return businessSectorCounter;
    }

    public BusinessSectorCounter() {
        this.businessSectorCounter = new HashMap<>();
    }

    public void addBusinessSector(String name){
        Integer counter = businessSectorCounter.get(name);

        if(Objects.isNull(counter)){
            businessSectorCounter.put(name,1);
        }else {
            businessSectorCounter.put(name,counter + 1);
        }
    }

    @Override
    public String toString() {
        return "BusinessSectorCounter{" +
                "businessSectorCounter=" + businessSectorCounter +
                '}';
    }
}

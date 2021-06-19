package pt.andronikus.pnia.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pt.andronikus.pnia.core.AggregationInfoSerializer;

import java.util.HashMap;
import java.util.Map;

@JsonSerialize(using = AggregationInfoSerializer.class)
public class AggregationInfo {
    private final Map<String, BusinessSectorCounter> aggInfo;

    public AggregationInfo() {
        this.aggInfo = new HashMap<>();
    }

    public Map<String, BusinessSectorCounter> getAggInfo() {
        return aggInfo;
    }

    public BusinessSectorCounter getBusinessSectorCounterForPrefix(String prefix){
        return this.aggInfo.get(prefix);
    }

    public void addNewPrefix(String prefix){
        this.aggInfo.put(prefix, new BusinessSectorCounter());
    }
}

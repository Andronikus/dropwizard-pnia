package pt.andronikus.pnia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.pnia.api.AggregationInfo;
import pt.andronikus.pnia.api.BusinessSectorCounter;

import java.util.Objects;

public class PhoneAggregatorService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final AggregationInfo aggregationInfo = new AggregationInfo();

    public AggregationInfo getAggregationInfo() {
        return aggregationInfo;
    }

    /**
     * Aggregate the phone info by prefix and business sector.
     *
     * @param prefix phone's number prefix
     * @param businessSector Business sector associated to the phone number
     *
     */
    public void aggregateInfo(String prefix, String businessSector){

        if(Objects.isNull(businessSector) || businessSector.length() == 0){
            LOGGER.warn("received a null or empty business sector");
            return;
        }

        BusinessSectorCounter businessSectorCounter = aggregationInfo.getBusinessSectorCounterForPrefix(prefix);

        if(Objects.isNull(businessSectorCounter)){
            aggregationInfo.addNewPrefix(prefix);
        }

        aggregationInfo.getBusinessSectorCounterForPrefix(prefix).addBusinessSector(businessSector);
    }
}

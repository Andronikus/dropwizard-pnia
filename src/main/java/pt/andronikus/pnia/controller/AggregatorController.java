package pt.andronikus.pnia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.pnia.api.AggregationInfo;
import pt.andronikus.pnia.api.BusinessInfo;
import pt.andronikus.pnia.api.PhoneList;
import pt.andronikus.pnia.core.BusinessInfoClient;
import pt.andronikus.pnia.exception.InvalidPhoneNumber;
import pt.andronikus.pnia.service.PhoneAggregatorService;
import pt.andronikus.pnia.service.PhoneBusinessInfoService;
import pt.andronikus.pnia.core.PhonePrefix;
import pt.andronikus.pnia.service.PhonePrefixService;

import java.util.Objects;

public class AggregatorController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    private final PhonePrefixService phonePrefixService;
    private final PhoneAggregatorService phoneAggregatorService;

    public AggregatorController() {
        this.phonePrefixService = new PhonePrefixService(PhonePrefix.INSTANCE.getPrefixList());
        this.phoneAggregatorService = new PhoneAggregatorService();
    }

    public AggregationInfo aggregatePhoneInfo(PhoneList phoneList){

        BusinessInfo businessInfo;

        for(String phoneNumber: phoneList.getPhoneNumbers()){
                String phonePrefix = phonePrefixService.getPhonePrefix(phoneNumber);

                if(phonePrefix.length() > 0){
                    try {
                        PhoneBusinessInfoService phoneBusinessInfoService = new PhoneBusinessInfoService(BusinessInfoClient.INSTANCE.getClient());
                        businessInfo = phoneBusinessInfoService.getBusinessInfoForPhoneNumber(phoneNumber);

                        if (Objects.nonNull(businessInfo)){
                            phoneAggregatorService.aggregateInfo(phonePrefix,businessInfo.getBusinessSector());
                        }
                    }catch (InvalidPhoneNumber e){
                        LOGGER.info(String.format("phone %s is invalid", phoneNumber));
                    }
                }
        }

        return phoneAggregatorService.getAggregationInfo();
    }
}

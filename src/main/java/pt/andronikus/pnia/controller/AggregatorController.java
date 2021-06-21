package pt.andronikus.pnia.controller;

import pt.andronikus.pnia.api.AggregationInfo;
import pt.andronikus.pnia.api.BusinessInfo;
import pt.andronikus.pnia.api.PhoneList;
import pt.andronikus.pnia.core.BusinessInfoClient;
import pt.andronikus.pnia.service.PhoneAggregatorService;
import pt.andronikus.pnia.service.PhoneBusinessInfoService;
import pt.andronikus.pnia.service.PhoneNumberValidatorService;
import pt.andronikus.pnia.core.PhonePrefix;
import pt.andronikus.pnia.service.PhonePrefixService;

import java.util.Objects;

public class AggregatorController {

    private final PhoneNumberValidatorService phoneNumberValidator;
    private final PhonePrefixService phonePrefixService;
    private final PhoneAggregatorService phoneAggregatorService;

    public AggregatorController() {
        this.phoneNumberValidator = new PhoneNumberValidatorService();
        this.phonePrefixService = new PhonePrefixService(PhonePrefix.INSTANCE.getPrefixList());
        this.phoneAggregatorService = new PhoneAggregatorService();
    }

    public AggregationInfo aggregatePhoneInfo(PhoneList phoneList){

        AggregationInfo aggregationInfo = new AggregationInfo();
        BusinessInfo businessInfo;

        for(String phoneNumber: phoneList.getPhoneNumbers()){

            if(phoneNumberValidator.isValidPhoneNumber(phoneNumber))
            {
                String phonePrefix = phonePrefixService.getPhonePrefix(phoneNumber);

                if(phonePrefix.length() > 0){
                    PhoneBusinessInfoService phoneBusinessInfoService = new PhoneBusinessInfoService(BusinessInfoClient.INSTANCE.client);
                    businessInfo = phoneBusinessInfoService.getBusinessInfoForPhoneNumber(phoneNumber);

                    if (Objects.nonNull(businessInfo)){
                        phoneAggregatorService.aggregateInfo(phonePrefix,businessInfo.getBusinessSector(),aggregationInfo);
                    }
                }
            }
        }

        return aggregationInfo;
    }
}

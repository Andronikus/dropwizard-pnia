package pt.andronikus.pnia.controller;


import pt.andronikus.pnia.api.AggregationInfo;
import pt.andronikus.pnia.api.BusinessInfo;
import pt.andronikus.pnia.api.BusinessSectorCounter;
import pt.andronikus.pnia.api.PhoneList;
import pt.andronikus.pnia.service.PhoneBusinessInfoService;
import pt.andronikus.pnia.service.PhoneNumberValidatorService;
import pt.andronikus.pnia.core.PhonePrefix;
import pt.andronikus.pnia.service.PhonePrefixService;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AggregatorController {

    private final PhoneBusinessInfoService phoneBusinessInfoService;
    private final PhoneNumberValidatorService phoneNumberValidator;
    private final PhonePrefixService phonePrefixService;

    public AggregatorController() {
        Client client = ClientBuilder.newBuilder().connectTimeout(2, TimeUnit.SECONDS).build();
        this.phoneBusinessInfoService = new PhoneBusinessInfoService(client);
        this.phoneNumberValidator = new PhoneNumberValidatorService();
        this.phonePrefixService = new PhonePrefixService(PhonePrefix.INSTANCE.getPrefixList());
    }

    public AggregationInfo aggregatePhoneInfo(PhoneList phoneList){

        AggregationInfo aggregationInfo = new AggregationInfo();
        BusinessInfo businessInfo;

        for(String phoneNumber: phoneList.getPhoneNumbers()){

            if(phoneNumberValidator.isValidPhoneNumber(phoneNumber))
            {
                String phonePrefix = phonePrefixService.getPhonePrefix(phoneNumber);

                if(phonePrefix.length() > 0){
                    businessInfo = phoneBusinessInfoService.getBusinessInfoForPhoneNumber(phoneNumber);

                    if (Objects.nonNull(businessInfo)){
                        aggregateInfo(phonePrefix,businessInfo.getBusinessSector(),aggregationInfo);
                    }
                }
            }
        }

        return aggregationInfo;
    }

    public void aggregateInfo(String prefix, String businessSector, AggregationInfo aggregationInfo){

        BusinessSectorCounter businessSectorCounter = aggregationInfo.getBusinessSectorCounterForPrefix(prefix);

        if(Objects.isNull(businessSectorCounter)){
            aggregationInfo.addNewPrefix(prefix);
        }

        aggregationInfo.getBusinessSectorCounterForPrefix(prefix).addBusinessSector(businessSector);
    }





}

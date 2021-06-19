package pt.andronikus.pnia.controller;


import pt.andronikus.pnia.api.AggregationInfo;
import pt.andronikus.pnia.api.BusinessInfo;
import pt.andronikus.pnia.api.BusinessSectorCounter;
import pt.andronikus.pnia.api.PhoneList;
import pt.andronikus.pnia.core.PhoneBusinessInfoService;
import pt.andronikus.pnia.core.PhoneNumberValidator;
import pt.andronikus.pnia.core.PhonePrefix;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AggregatorController {

    private final PhoneBusinessInfoService phoneBusinessInfoService;
    private final PhoneNumberValidator phoneNumberValidator;

    public AggregatorController() {
        Client client = ClientBuilder.newBuilder().connectTimeout(2, TimeUnit.SECONDS).build();
        this.phoneBusinessInfoService = new PhoneBusinessInfoService(client);
        this.phoneNumberValidator = new PhoneNumberValidator();
    }

    public AggregationInfo aggregatePhoneInfo(PhoneList phoneList){

        AggregationInfo aggregationInfo = new AggregationInfo();
        BusinessInfo businessInfo;

        for(String phoneNumber: phoneList.getPhoneNumbers()){

            if(phoneNumberValidator.isValidPhoneNumber(phoneNumber))
            {
                String phonePrefix = getPhonePrefix(phoneNumber, PhonePrefix.INSTANCE.getPrefixList());

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

    public String getPhonePrefix(String phoneNumber, Set<String> prefixes){
        String normalizedNumber = phoneNumberValidator.normalizeNumber(phoneNumber);

        for(int i=0; i < normalizedNumber.length(); i++){
            String prefix = normalizedNumber.substring(0,i+1);
            System.out.println(prefix);

            if (prefixes.contains(prefix)){
                return prefix;
            }
        }

        return "";
    }



}

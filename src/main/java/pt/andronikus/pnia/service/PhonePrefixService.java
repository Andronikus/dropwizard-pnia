package pt.andronikus.pnia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class PhonePrefixService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    private Set<String> prefixes = new HashSet<>();
    private PhoneNumberValidatorService phoneNumberValidatorService;

    public PhonePrefixService(Set<String> prefixes) {
        this.prefixes = prefixes;
        this.phoneNumberValidatorService = new PhoneNumberValidatorService();
    }

    /**
     * Given a phone number it try to find the prefix against a list of prefixes
     *
     * @param phoneNumber String representation of a phone number
     *
     * @return prefix of the phone number or empty string if cannot find any prefix in the list
     *
     */
    public String getPhonePrefix(String phoneNumber){
        String normalizedNumber = phoneNumberValidatorService.normalizeNumber(phoneNumber);

        for(int i=0; i < normalizedNumber.length(); i++){
            String prefix = normalizedNumber.substring(0,i+1);

            if (prefixes.contains(prefix)){
                LOGGER.info(String.format("prefix founded for phone number %s. value: %s", phoneNumber,prefix));
                return prefix;
            }
        }

        LOGGER.info(String.format("prefix not founded for phone number %s", phoneNumber));
        return "";
    }
}

package pt.andronikus.pnia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class PhonePrefixService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    private final Set<String> prefixes;
    private final PhoneNumberValidatorService phoneNumberValidatorService;

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
        Instant startTime = Instant.now();

        String normalizedNumber = phoneNumberValidatorService.normalizeNumber(phoneNumber);

        for(int i=0; i < normalizedNumber.length(); i++){
            String prefix = normalizedNumber.substring(0,i+1);

            if (prefixes.contains(prefix)){
                long elapsedTime = Duration.between(startTime, Instant.now()).toMillis();
                LOGGER.info(String.format("prefix found for phone number %s in in %d (ms). prefix: %s ", phoneNumber, elapsedTime , prefix));
                return prefix;
            }
        }

        LOGGER.info(String.format("prefix not founded for phone number %s", phoneNumber));
        return "";
    }
}

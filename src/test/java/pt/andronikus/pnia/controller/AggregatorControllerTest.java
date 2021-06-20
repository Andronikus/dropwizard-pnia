package pt.andronikus.pnia.controller;

import org.junit.jupiter.api.Test;
import pt.andronikus.pnia.service.PhoneNumberValidatorService;
import pt.andronikus.pnia.service.PhonePrefixService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AggregatorControllerTest {

    @Test
    void normalizeNumber() {
        PhoneNumberValidatorService phoneNumberValidator = new PhoneNumberValidatorService();

        assertEquals("17490276403", phoneNumberValidator.normalizeNumber("+1 7490276403"));
        assertEquals("17490276403", phoneNumberValidator.normalizeNumber("001 7490276403"));
        assertEquals("17490276403", phoneNumberValidator.normalizeNumber("+1 749 02 764 03"));
    }

    @Test
    void getPhonePrefix() {

        Set<String> listPrefixes = new HashSet<>();
        listPrefixes.add("12");
        listPrefixes.add("18");
        listPrefixes.add("3");

        PhonePrefixService phonePrefixService = new PhonePrefixService(listPrefixes);
        assertEquals("18",phonePrefixService.getPhonePrefix("+189192879"));
        assertEquals("12",phonePrefixService.getPhonePrefix("+129823978"));
        assertEquals("3",phonePrefixService.getPhonePrefix("+398123938"));
        assertEquals("",phonePrefixService.getPhonePrefix("+598123938"));
    }
}
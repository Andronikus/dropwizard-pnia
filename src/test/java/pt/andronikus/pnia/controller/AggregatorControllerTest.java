package pt.andronikus.pnia.controller;

import org.junit.jupiter.api.Test;
import pt.andronikus.pnia.core.PhoneNumberValidator;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AggregatorControllerTest {

    @Test
    void normalizeNumber() {
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();

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

        AggregatorController controller = new AggregatorController();
        assertEquals("18",controller.getPhonePrefix("+189192879", listPrefixes));
        assertEquals("12",controller.getPhonePrefix("+129823978", listPrefixes));
        assertEquals("3",controller.getPhonePrefix("+398123938", listPrefixes));
        assertEquals("",controller.getPhonePrefix("+598123938", listPrefixes));
    }
}
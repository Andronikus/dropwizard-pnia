package pt.andronikus.pnia.core;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneNumberValidatorTest {
    @Test
    void whenPhoneNumberIsEmpty_shouldNotBeValid() {
        PhoneNumberValidator numberValidator = new PhoneNumberValidator();
        assertFalse(numberValidator.isValidPhoneNumber(""));
    }

    @Test
    void whenPhoneNumberIsNull_shouldNotBeValid() {
        PhoneNumberValidator numberValidator = new PhoneNumberValidator();
        assertFalse(numberValidator.isValidPhoneNumber(null));
    }

    @Test
    void whenPhoneNumberHasAPlusAndSpaceAfter_shouldNotBeValid() {
        PhoneNumberValidator numberValidator = new PhoneNumberValidator();
        assertFalse(numberValidator.isValidPhoneNumber("+ 17490276403"));
        assertFalse(numberValidator.isValidPhoneNumber(" + 17490276403" ));
    }

    @Test
    void whenPhoneNumberHasNonDigits_shouldNotBeValid() {
        PhoneNumberValidator numberValidator = new PhoneNumberValidator();
        assertFalse(numberValidator.isValidPhoneNumber("001382355A"));
        assertTrue(numberValidator.isValidPhoneNumber("+138235524"));
        assertFalse(numberValidator.isValidPhoneNumber("0+138235524"));
    }
}
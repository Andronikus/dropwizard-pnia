package pt.andronikus.pnia.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberValidatorServiceTest {

    @Test
    void normalizeNumber() {
        PhoneNumberValidatorService phoneNumberValidator = new PhoneNumberValidatorService();

        assertEquals("17490276403", phoneNumberValidator.normalizeNumber("+1 7490276403"));
        assertEquals("17490276403", phoneNumberValidator.normalizeNumber("001 7490276403"));
        assertEquals("17490276403", phoneNumberValidator.normalizeNumber("+1 749 02 764 03"));
    }

    @Test
    void whenPhoneNumberIsEmpty_shouldNotBeValid() {
        PhoneNumberValidatorService numberValidator = new PhoneNumberValidatorService();
        assertFalse(numberValidator.isValidPhoneNumber(""));
    }

    @Test
    void whenPhoneNumberIsNull_shouldNotBeValid() {
        PhoneNumberValidatorService numberValidator = new PhoneNumberValidatorService();
        assertFalse(numberValidator.isValidPhoneNumber(null));
    }

    @Test
    void whenPhoneNumberHasAPlusAndSpaceAfter_shouldNotBeValid() {
        PhoneNumberValidatorService numberValidator = new PhoneNumberValidatorService();
        assertFalse(numberValidator.isValidPhoneNumber("+ 17490276403"));
        assertFalse(numberValidator.isValidPhoneNumber(" + 17490276403" ));
    }

    @Test
    void whenPhoneNumberHasNonDigits_shouldNotBeValid() {
        PhoneNumberValidatorService numberValidator = new PhoneNumberValidatorService();
        assertFalse(numberValidator.isValidPhoneNumber("001382355A"));
        assertTrue(numberValidator.isValidPhoneNumber("+138235524"));
        assertFalse(numberValidator.isValidPhoneNumber("0+138235524"));
    }

    @Test
    void whenPhoneNumberSizeNotEqual3_OrSizeNotGreaterThan6_AndSizeNotLessThan13_shouldNotBeValid() {
        PhoneNumberValidatorService numberValidator = new PhoneNumberValidatorService();
        // 0013 -> +13 (size 2)
        assertFalse(numberValidator.isValidPhoneNumber("0013"));
        // 00134 -> +134 (size 3)
        assertTrue(numberValidator.isValidPhoneNumber("00134"));
        assertFalse(numberValidator.isValidPhoneNumber("001234"));
        assertFalse(numberValidator.isValidPhoneNumber("0012345"));
        assertFalse(numberValidator.isValidPhoneNumber("00123456"));
        assertTrue(numberValidator.isValidPhoneNumber("001234567"));
        assertTrue(numberValidator.isValidPhoneNumber("0012345678"));
        assertTrue(numberValidator.isValidPhoneNumber("00123456789"));
        assertTrue(numberValidator.isValidPhoneNumber("001234567891"));
        assertTrue(numberValidator.isValidPhoneNumber("0012345678912"));
        assertTrue(numberValidator.isValidPhoneNumber("00123456789123"));
        assertFalse(numberValidator.isValidPhoneNumber("001234567891234"));
    }
}
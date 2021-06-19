package pt.andronikus.pnia.core;

import java.util.regex.Pattern;

public class PhoneNumberValidator {

    private final Pattern ONLY_DIGITS = Pattern.compile("\\d+");

    /**
     * Given a string representation of a phone number it return a new representation
     * applying the following rules:
     *  + trimmed
     *  + Remove inner spaces characters
     *  + Remove the + or 00 prefix indicator
     *
     * @param phoneNumber String representation of a phone number
     *
     * @return new phone number representation normalized
     *
     */
    public String normalizeNumber(String phoneNumber){
        String normalizeNumber = phoneNumber.trim().replaceAll("\\s+", "").replace("+", "");

        if(normalizeNumber.startsWith("00")){
            return normalizeNumber.substring(2);
        }

        return normalizeNumber;
    }

    /**
     * Check the validity of a phone number
     *
     * Given a phone number it will check the following rules
     *
     * + Emptiness or Nullable
     * + When + (plus) is present a space should not be present immediately after the +
     * + Phone number should have only digits (only + and space are permitted)
     *
     * @param phoneNumber String representation of a phone number
     * @return Boolean value indicating if the underlying phone number is valid or not
     *
     */

    public boolean isValidPhoneNumber(String phoneNumber){
        return !isEmptyOrNull(phoneNumber) &&
               !spaceAfterPlusSignal(phoneNumber) &&
                onlyDigits(phoneNumber) &&
                havePlusSignalAndItsTheFirstPhoneNumberCharacter(phoneNumber);
    }

    private boolean isEmptyOrNull(String phoneNumber){

        if(phoneNumber == null)  return true;

        return phoneNumber.trim().length() == 0;
    }

    private boolean onlyDigits(String phoneNumber){
        return ONLY_DIGITS.matcher(normalizeNumber(phoneNumber)).matches();
    }

    private boolean spaceAfterPlusSignal(String phoneNumber){
        return phoneNumber.trim().startsWith("+ ");
    }

    private boolean havePlusSignalAndItsTheFirstPhoneNumberCharacter(String phoneNumber){
        return !(phoneNumber.trim().indexOf("+") > 0);
    }




    // valid number
    // has only digits - ok
    // optional + and digits
    // +<space> - not valid
    // exact 3 digits or more than 6 and less than 13 [7,12]
    // 00 can be replaced as +


}

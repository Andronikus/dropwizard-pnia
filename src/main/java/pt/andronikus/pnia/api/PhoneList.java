package pt.andronikus.pnia.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PhoneList {
    private List<String> phoneNumbers;

    public PhoneList() {
    }

    @JsonCreator
    public PhoneList(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @JsonProperty
    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    @JsonProperty
    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public String toString() {
        return "PhoneList{" +
                "phoneNumbers=" + phoneNumbers +
                '}';
    }
}

package pt.andronikus.pnia.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusinessInfo {
    private String phoneNumber;
    private String businessSector;


    @JsonProperty("number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("number")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("sector")
    public String getBusinessSector() {
        return businessSector;
    }

    @JsonProperty("sector")
    public void setBusinessSector(String businessSector) {
        this.businessSector = businessSector;
    }

    @Override
    public String toString() {
        return "BusinessInfo{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", businessSector='" + businessSector + '\'' +
                '}';
    }
}

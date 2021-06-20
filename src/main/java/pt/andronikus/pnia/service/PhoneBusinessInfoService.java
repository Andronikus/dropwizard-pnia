package pt.andronikus.pnia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.pnia.api.BusinessInfo;
import pt.andronikus.pnia.core.BusinessInfoClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PhoneBusinessInfoService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    private final Client webClient;

    public PhoneBusinessInfoService(Client webClient) {
        this.webClient = webClient;
    }

    public BusinessInfo getBusinessInfoForPhoneNumber(String phoneNumber){
        final WebTarget webTarget= webClient.target("https://challenge-business-sector-api.meza.talkdeskstg.com/sector/{phoneNumber}")
                                            .resolveTemplateFromEncoded("phoneNumber", phoneNumber);

        LOGGER.info(String.format("get business info for phone number %s", phoneNumber));

        Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE)
                                     .accept(MediaType.APPLICATION_JSON_TYPE)
                                     .get();

        return processResponse(response);
    }

    private BusinessInfo processResponse(Response response){
        try{
            LOGGER.info(String.format("response status code: %d", response.getStatusInfo().getStatusCode()));
            if(response.getStatusInfo().getStatusCode() == Response.Status.OK.getStatusCode()){
                return response.readEntity(BusinessInfo.class);
            }
            return null;
        }finally {
            response.close();
        }
    }
}

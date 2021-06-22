package pt.andronikus.pnia.core;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.pnia.configuration.PhoneNumberInfoAggregatorConfiguration;

import javax.ws.rs.client.Client;

public enum BusinessInfoClient {
    INSTANCE;
    private final Logger LOGGER = LoggerFactory.getLogger(BusinessInfoClient.class.getName());
    private Client client;

    public void createClient(PhoneNumberInfoAggregatorConfiguration cfg, Environment environment, String appName){
        this.client = new JerseyClientBuilder(environment)
                                .using(cfg.getJerseyClientConfiguration())
                                .build(appName == null ? "defaultApp": appName);
        LOGGER.info("Client created with success!");
    }

    public void closeClient(){
        client.close();
        LOGGER.info("Client closed with success!");
    }

    public Client getClient(){
        return client;
    }
}

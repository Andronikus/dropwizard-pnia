package pt.andronikus.pnia.core;

import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Environment;
import pt.andronikus.pnia.configuration.PhoneNumberInfoAggregatorConfiguration;

public class BusinessInfoClientManaged implements Managed {

    private final PhoneNumberInfoAggregatorConfiguration cfg;
    private final Environment environment;
    private final String appName;

    public BusinessInfoClientManaged(Environment environment, PhoneNumberInfoAggregatorConfiguration cfg, String appName) {
        this.cfg = cfg;
        this.environment = environment;
        this.appName = appName;
    }

    @Override
    public void start() {
        BusinessInfoClient.INSTANCE.createClient(cfg,environment,appName);
    }

    @Override
    public void stop() {
        BusinessInfoClient.INSTANCE.closeClient();
    }
}

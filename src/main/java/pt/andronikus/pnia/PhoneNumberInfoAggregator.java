package pt.andronikus.pnia;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.pnia.configuration.PhoneNumberInfoAggregatorConfiguration;
import pt.andronikus.pnia.core.PhonePrefix;
import pt.andronikus.pnia.resources.PhoneNumberAggregatorResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class PhoneNumberInfoAggregator extends Application<PhoneNumberInfoAggregatorConfiguration> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    public static void main(String[] args) throws Exception {
        new PhoneNumberInfoAggregator().run(args);
    }

    @Override
    public void run(PhoneNumberInfoAggregatorConfiguration phoneNumberInfoAggregatorConfiguration, Environment environment) throws Exception {
        loadPrefixInfoFromFile("prefixes.txt");
        environment.jersey().register(new PhoneNumberAggregatorResource());
    }

    private InputStream getFileFromResourceAsStream(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if(Objects.isNull(inputStream)) throw new IllegalArgumentException(String.format("file %s not found!", fileName));

        return inputStream;
    }

    private void loadPrefixInfoFromFile(String fileName){
        InputStream is = getFileFromResourceAsStream(fileName);
        int prefixCounter = 0;

        try(InputStreamReader inputStreamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader)){

            String line;
            while((line = reader.readLine()) != null){
                PhonePrefix.INSTANCE.addPrefix(line);
                prefixCounter++;
            }
            LOGGER.info("%d prefix(s) loaded from file %s", prefixCounter, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

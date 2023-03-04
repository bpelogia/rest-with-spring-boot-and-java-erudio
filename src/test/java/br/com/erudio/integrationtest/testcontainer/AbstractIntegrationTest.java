package br.com.erudio.integrationtest.testcontainer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

    static class CustomMongoDbContainer extends GenericContainer<CustomMongoDbContainer> {
        public CustomMongoDbContainer() {
            super(DockerImageName.parse("mongo:latest"));
            withEnv("MONGO_INITDB_ROOT_USERNAME", "rootuser");
            withEnv("MONGO_INITDB_ROOT_PASSWORD", "rootpass");
            withEnv("MONGO_INITDB_DATABASE", "erudio");
            withExposedPorts(27017);
        }
    }
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{
        static CustomMongoDbContainer mongoDBContainer = new CustomMongoDbContainer().withReuse(true);

        private static void startContainers() {
            Startables.deepStart(Stream.of(mongoDBContainer)).join();
        }

        private static Map<String, String> createConnectionConfiguration() {
            return Map.of(
                    "spring.data.mongodb.username", mongoDBContainer.getEnv().get(0),
                    "spring.data.mongodb.password", mongoDBContainer.getEnv().get(1),
                    "spring.data.mongodb.database", mongoDBContainer.getEnv().get(2)
            );
        }

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testcontainers = new MapPropertySource(
                    "testcontainers",
                    (Map) createConnectionConfiguration());
            environment.getPropertySources().addFirst(testcontainers);
        }
    }
}

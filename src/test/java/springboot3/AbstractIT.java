package springboot3;

import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@ContextConfiguration(
    initializers = {
        AbstractIT.PostgresInitializer.class
    }
)
@Slf4j
public abstract class AbstractIT {

  private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
      DockerImageName.parse("postgis/postgis:15-3.3")
          .asCompatibleSubstituteFor("postgres"))
      .withUsername("postgres")
      .withPassword("postgres")
      .withDatabaseName("fields-service")
      .withReuse(true);

  static {
    Startables.deepStart(Stream.of(postgreSQLContainer)).join();
  }

  public static class PostgresInitializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

      TestPropertyValues.of(
              "spring.datasource.driver-class-name=" + postgreSQLContainer.getDriverClassName(),
              "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
              "spring.datasource.username=" + postgreSQLContainer.getUsername(),
              "spring.datasource.password=" + postgreSQLContainer.getPassword())
          .applyTo(configurableApplicationContext.getEnvironment());
      log.info("Postgres is running @ {}", postgreSQLContainer.getJdbcUrl());
    }
  }
}

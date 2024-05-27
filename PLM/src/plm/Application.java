package plm;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Main entry point for the PLM application.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class Application {
    /**
     * Main method to run the Spring Boot application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }

    /**
     * Creates and configures the DataSource bean used to establish database connections.
     *
     * @return the DataSource bean (not relevant here)
     */
    @Autowired
    @Bean("dataSource")
    public DataSource getDataSource() {
        //
        // Implementation and returned value are not relevant for this exercise
        //
        return null;
    }

    /**
     * Creates and configures the SessionFactory bean used by Hibernate
     * to create sessions for database interactions.
     *
     * @param dataSource The DataSource to be used by the SessionFactory.
     * @return the SessionFactory bean.
     */
    @Autowired
    @Bean("sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        //
        // Implementation and returned value are not relevant for this exercise
        //
        return null;
    }
}

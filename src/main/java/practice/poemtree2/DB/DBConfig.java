package practice.poemtree2.DB;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {

    /*
     * If one want to apply @Component / @ComponentScan / @Autowired for DI, This
     * Class 'DBConfig' is eliminated. But dataSource bean is neccesary..!
     * 
     * So then, make class of DataSource and inject database information by
     * the @PostConstruct..!
     */
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username("sa");
        dataSourceBuilder.url("jdbc:h2:~/poemtree2");
        dataSourceBuilder.driverClassName("org.h2.Driver");
        return dataSourceBuilder.build();
    }

    @Bean
    public FavorDB favorDB() {
        return new FavorDBImpl(dataSource());
    }

    @Bean
    public TitleDB titleDB() {
        return new TitleDBImpl(dataSource());
    }

    @Bean
    public BodyDB bodyDB() {
        return new BodyDBImpl(dataSource());
    }

    @Bean
    public StarDB starDB() {
        return new StarDBImpl(dataSource());
    }

}

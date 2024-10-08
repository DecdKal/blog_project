package web_project.blog.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import web_project.blog.repository.RoleRepository;

import javax.sql.DataSource;

@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    @Bean
//    public DataSourceInitializer dataSourceInitializer(DataSource dataSource, RoleRepository roleRepository, ResourceLoader loader) {
//        DataSourceInitializer initializer = new DataSourceInitializer();
//        initializer.setDataSource(dataSource);
//
//        if(roleRepository.count() == 0) {
//            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//            populator.addScript(loader.getResource("classpath:data.sql"));
//            initializer.setDatabasePopulator(populator);
//        }
//        return initializer;
//    }
}

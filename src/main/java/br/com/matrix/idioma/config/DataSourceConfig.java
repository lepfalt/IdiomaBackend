package br.com.matrix.idioma.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@Profile({"development"})
public class DataSourceConfig implements ApplicationRunner {

	@Autowired
	private DataSource dataSource;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		resourceDatabasePopulator.addScript(new ClassPathResource("/data-development.sql"));
		resourceDatabasePopulator.execute(dataSource);
	}

}

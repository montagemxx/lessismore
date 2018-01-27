package com.mxx.it.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class SpringDBConfig {
	
//	@Autowired
//	DataSource dataSource;
	
	@Bean
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(){
		NamedParameterJdbcTemplate  template = new NamedParameterJdbcTemplate(getDataSource());
		return template;
	}
	
	@Bean
	public DataSource getDataSource() {
		DataSource dataSource = null;
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		dataSource = builder.setName("testdb").setType(EmbeddedDatabaseType.HSQL)
				.addScript("db/create_user_table.sql").addScript("db/adduser.sql").build();
		return dataSource;
	}

	@PostConstruct
	public void startDBManager(){
		
	}
}

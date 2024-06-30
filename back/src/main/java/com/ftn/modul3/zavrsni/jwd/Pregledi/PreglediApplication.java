package com.ftn.modul3.zavrsni.jwd.Pregledi;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PreglediApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreglediApplication.class, args);
	}

	 @Bean
	    public CommandLineRunner loadData(DataSource dataSource) {
	        return args -> {
	            Resource resource = new ClassPathResource("data.sql");
	            ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);
	        };
	    }

}

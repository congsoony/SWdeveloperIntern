package kr.or.connect.reservesystem.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"kr.or.connect.reservesystem.dao","kr.or.connect.reservesystem.service"})
@Import({DBConfig.class})
public class ApplicationConfig {

}

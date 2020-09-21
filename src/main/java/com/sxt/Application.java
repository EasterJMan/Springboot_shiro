package com.sxt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages= {"com.sxt.mapper"})
public class Application {

	public static void main(String[] args) {
		SpringApplication application=new SpringApplication(Application.class);
		application.setBannerMode(Mode.OFF);
		application.run(args);
	}

}

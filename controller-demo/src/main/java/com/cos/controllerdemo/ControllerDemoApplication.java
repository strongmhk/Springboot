package com.cos.controllerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages={"com.cos.controllerdemo"})
@SpringBootApplication
public class ControllerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControllerDemoApplication.class, args);
	}

}

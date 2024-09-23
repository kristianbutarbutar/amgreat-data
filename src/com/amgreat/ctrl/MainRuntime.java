package com.amgreat.ctrl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan (basePackages = "com.amgreat.lib, com.amgreat.ctrl, com.amgreat.vo")
@SpringBootApplication
public class MainRuntime {
	public static void main(String[] args) {
		SpringApplication.run(MainRuntime.class, args);
	}
}

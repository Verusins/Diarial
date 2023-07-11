package com.verusins.diarial;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.verusins.diarial.system.mapper")
public class SigmundApplication {

	public static void main(String[] args) {
		SpringApplication.run(SigmundApplication.class, args);
	}

}

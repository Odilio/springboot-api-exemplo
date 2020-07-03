package com.unisoma;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class UnisomaApplication {

		  public static void main(String[] args) {
		    	new SpringApplicationBuilder(UnisomaApplication.class)
		        .profiles("dev")
		        .run(args);
		    }
	}


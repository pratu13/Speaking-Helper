//@Author Christian Dummer
package com.speechhelper.main;

import java.util.Collections;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import com.speechhelper.controller.SpeakingHelperController;



@SpringBootApplication
@ComponentScan("com.speechhelper")
@EntityScan("com.speechhelper")
@EnableJpaRepositories("com.speechhelper")
@EnableAsync
public class Main {
	
	public static void main(String[] args) {
		//SpringApplication.run(Main.class, args);
		SpringApplication app = new SpringApplication(Main.class);
//		//SpringApplication.run(Main.class, args);
		//app.setDefaultProperties(Collections
		//          .singletonMap("server.port", "8089"));
		app.run(args);
		SpeakingHelperController c = new SpeakingHelperController();
		//c.runPythonScript_liveprediction();
		//c.runPythonScript_recordaudio();
	}

}

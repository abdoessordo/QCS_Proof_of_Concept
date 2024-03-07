package com.ge.proof_of_concept;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProofOfConceptApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProofOfConceptApplication.class, args);
	}

	/**
	 * Grant CORS access to the frontend, to multiple origins
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// Allow access to the frontend from multiple origins
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000", "http://172.26.32.1:3000")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
			}
		};
	}
}

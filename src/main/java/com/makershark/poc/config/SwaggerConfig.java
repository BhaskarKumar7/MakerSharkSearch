package com.makershark.poc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	/***** creating swagger api documentation *****/
	@Bean
	public OpenAPI customOpenApi()	{
		Contact contact = new Contact();
		contact.setEmail("bhaskarkumar4994@gmail.com");
		contact.setName("Bhaskar kumar Aligi");
		contact.setUrl("To be generated.......");
		Info info = new Info();
		info.setTitle("Maker Sharks Suppliers");
		info.setDescription("Makersharks is building a search page where buyers can search for manufacturers "
				+ "based on their customised requirements. We're building a proof of concept for the search API.");
		info.setVersion("v1.0");
		info.setContact(contact);
		return new OpenAPI().info(info);
	}
}

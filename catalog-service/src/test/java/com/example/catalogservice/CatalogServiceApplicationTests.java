package com.example.catalogservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CatalogServiceApplicationTests {

	@Value("${logging.level.com.example.catalogservice}")
	private String logLevel;

	@Test
	void contextLoads() {
		System.out.println(logLevel);
	}

}

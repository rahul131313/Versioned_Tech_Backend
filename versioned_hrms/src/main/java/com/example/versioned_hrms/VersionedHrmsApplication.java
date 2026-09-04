package com.example.versioned_hrms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class VersionedHrmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VersionedHrmsApplication.class, args);
	}

}

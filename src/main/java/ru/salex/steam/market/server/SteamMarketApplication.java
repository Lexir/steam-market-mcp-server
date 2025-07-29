package ru.salex.steam.market.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SteamMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SteamMarketApplication.class, args);
	}

}

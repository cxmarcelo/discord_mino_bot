package com.br.Mino_bot;

import javax.security.auth.login.LoginException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.Mino_bot.commands.Commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;


@SpringBootApplication
public class MinoBotApplication {

	
	//public static JDA jda;
	private static String DISC_TOKEN;
	
	public static void main(String[] args) throws Exception {
		if(args.length == 0) {
			throw new Exception("The Discord access token is necessary.");
		}
		DISC_TOKEN = args[0];
		
		SpringApplication.run(MinoBotApplication.class, args);
		//jda = JDABuilder.createDefault("OTE3NTEzNTQwNzY2Njk1NDg3.Ya5zEg.EVW0ee8JKqIWcAFp4AKFOxqFXT4").build();
		//jda.addEventListener(new Commands());
	}
	
	public static String getDiscToken() {
		return DISC_TOKEN;
	}

}

package com.br.Mino_bot;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.Mino_bot.commands.Commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

@Component
public class MinoBot {
	
	@Autowired
	private Commands commands;
	
	public static JDA jda;
	public static final String DEFAULT_PREFIX = "!mino";
	
	public MinoBot() {
	}
	
	@PostConstruct
	public void initialize() throws LoginException {
		jda = JDABuilder.createDefault(MinoBotApplication.getDiscToken()).build();
		jda.addEventListener(commands);
	}

}

package com.br.Mino_bot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.Mino_bot.MinoBot;
import com.br.Mino_bot.domain.ServerConfig;
import com.br.Mino_bot.repositories.ServerConfigRepository;
import com.br.Mino_bot.services.exceptions.ObjectNotFoundException;

@Service
public class ServerConfigService {

	@Autowired
	private ServerConfigRepository repo;

	public ServerConfig insertServerConfig(String serverId) {
		Optional<ServerConfig> obj = repo.findById(serverId);
		ServerConfig config;
		if(obj.isPresent()) {
			System.out.println("Object presente");
			config = obj.get();
			config.setPrefix(MinoBot.DEFAULT_PREFIX);
		} else {
			System.out.println("Object NÃO presente");
			config = new ServerConfig(serverId, MinoBot.DEFAULT_PREFIX);
		}
		return repo.save(config);
	}
	
	
	public ServerConfig findServerConfigById(String serverId) {
		Optional<ServerConfig> config = repo.findById(serverId);
		return config.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + serverId + ", Tipo: " + ServerConfig.class.getName()));
		
	}

}
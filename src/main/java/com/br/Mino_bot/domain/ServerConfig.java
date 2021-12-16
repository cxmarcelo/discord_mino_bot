package com.br.Mino_bot.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ServerConfig {
	
	@Id
	private String id;
	private String prefix;
	private boolean active;
	
	public ServerConfig() {
	}
	
	public ServerConfig(String id, String prefix) {
		super();
		this.id = id;
		this.prefix = prefix;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}

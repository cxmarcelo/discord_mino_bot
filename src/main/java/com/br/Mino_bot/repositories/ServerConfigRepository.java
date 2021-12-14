package com.br.Mino_bot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.Mino_bot.domain.ServerConfig;

@Repository
public interface ServerConfigRepository extends JpaRepository<ServerConfig, String>{
}

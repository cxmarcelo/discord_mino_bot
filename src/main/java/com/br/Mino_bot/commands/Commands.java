package com.br.Mino_bot.commands;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.Mino_bot.MinoBot;
import com.br.Mino_bot.domain.ServerConfig;
import com.br.Mino_bot.domain.enuns.TextCommandsEnum;
import com.br.Mino_bot.services.ServerConfigService;
import com.br.Mino_bot.services.exceptions.ObjectNotFoundException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Component
public class Commands extends ListenerAdapter {
	
	Integer farmaciaCount = 1;
	
	@Autowired
	private ServerConfigService serverConfigService;
	
	HashMap<String, String> serverPrefix = new HashMap<String, String>();
	
	@Override
	public void onMessageReactionAdd(MessageReactionAddEvent event) {
		System.out.println("onMessageReaction");
		System.out.println(event);
	}
	
	@Override
	public void onGuildJoin(GuildJoinEvent event) {
		serverConfigService.insertServerConfig(event.getGuild().getId());
		
		TextChannel channelMsg = null;
		for (GuildChannel channel : event.getGuild().getChannels()) {
			if(channel.getType().getId() == ChannelType.TEXT.getId()) {
				channelMsg = event.getGuild().getTextChannelById(channel.getId());
				break;
			}
		}
		
		if(channelMsg == null) {
			return;
		} else {
			EmbedBuilder info = new EmbedBuilder();
			info.setTitle("Mino:");
			info.setDescription("Muuuh chamando todos os cornos.");
			info.addField("Creator", "Marcelol", false);
			info.setColor(0xffff00);
			channelMsg.sendMessageEmbeds(info.build()).queue();
			info.clear();
		}
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if(event.getAuthor().isBot()) return;
		System.out.println("onMessage");
		
		String serverId = event.getGuild().getId();
		String prefix = getServerPrefix(serverId);

		xingarFarmacia(event);
		
		System.out.println(prefix);
		if(event.getMessage().getContentRaw().startsWith(prefix)) {
			String msg = event.getMessage().getContentRaw();
			System.out.println("Mensagem:");
			System.out.println(msg);
			System.out.println("---------------------");
			
			String[] msgSplit = msg.split(prefix);
			if(msgSplit.length >= 1) {
				String[] msgContent = msgSplit[1].split(" ", 2);
				String command = null;
				String param = null;
			
				if(msgContent.length >= 1) {
					command = msgContent[0];
					if(msgContent.length > 1) {
						param = msgContent[1];
					}
				}
				
				if(command.equalsIgnoreCase(TextCommandsEnum.HELP.getCommand())) {
					commandHelp(event, command, param);
				}
				
				System.out.println("Comando: " + command);
				System.out.println("Params: " + param);
				
			}
			
		}
		
		if(event.getMessage().getContentRaw().startsWith("~")) {
			System.out.println("if onMessage");
			EmbedBuilder info = new EmbedBuilder();
			info.setTitle("Mino:");
			info.setDescription("Chamando todos os bois");
			info.addField("Creator", "Marcelol", false);
			info.setColor(0xffff00);
			
			event.getChannel().sendTyping().queue();
			//Message msg = event.getChannel().sendMessageEmbeds(info.build()).complete();
			Message msg = event.getChannel().sendMessage("Farmacia você é corno").complete();
			System.out.println("Messages");
			System.out.println(msg.getId());
			System.out.println(msg.getContentRaw());
			info.clear();
		}
	}

	private void commandHelp(MessageReceivedEvent event, String command, String param) {
		System.out.println("Command helps");
		EmbedBuilder info = new EmbedBuilder();
		info.setTitle("Comandos Disponíveis:");
		String availableCommands = "";
		for (TextCommandsEnum textCommand : TextCommandsEnum.values()) {
			availableCommands += MinoBot.DEFAULT_PREFIX + textCommand.getCommand() + ": " + textCommand.getDescription() + "\n\n";
		}
		info.setDescription(availableCommands);
		info.addField("Creator", "Marcelol", false);
		info.setColor(0xffff00);
		
		event.getChannel().sendTyping().queue();
		Message msg = event.getChannel().sendMessageEmbeds(info.build()).complete();
		//msg.addReaction(":one:");
		
		
		//msg.addReaction();
		info.clear();
		
	}

	private void xingarFarmacia(MessageReceivedEvent event) {
		if(event.getAuthor().getId().equals("223216050588614666")) {
			if(farmaciaCount == 3) {
				event.getChannel().sendMessage("Farmacia você é corno").queue();
				farmaciaCount = 1;
			} else {
				farmaciaCount++;
			}
		}
	}

	private String getServerPrefix(String serverId) {
		String prefix = serverPrefix.get(serverId);
		if(prefix != null) {
			return prefix;
		} else {
			try {
				ServerConfig config = serverConfigService.findServerConfigById(serverId);
				serverPrefix.put(serverId, prefix);
				return config.getPrefix();
			} catch (ObjectNotFoundException obj) {
				ServerConfig config = serverConfigService.insertServerConfig(serverId);
				serverPrefix.put(serverId, prefix);
				return config.getPrefix();
			}
		}
	}
}

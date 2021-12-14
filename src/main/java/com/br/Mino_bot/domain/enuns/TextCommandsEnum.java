package com.br.Mino_bot.domain.enuns;

public enum TextCommandsEnum {
	
	PLAY("play", "Uma descrição de play ai."),
	HELP("help", "Comando de ajuda ai."),
	CHANGE_PREFIX("prefix", "Comando para alter prefixo ai."),
	;
	
	private String command;
	private String description;
	
	private TextCommandsEnum(String command, String description) {
		this.command = command;
		this.description = description;
	}
	
	public TextCommandsEnum toEnum(String command) {
		for (TextCommandsEnum textCommand: TextCommandsEnum.values()) {
			if(command.equalsIgnoreCase(textCommand.getCommand())) {
				return textCommand;
			}
		}
		return null;
	}

	public String getCommand() {
		return command;
	}

	public String getDescription() {
		return description;
	}

}

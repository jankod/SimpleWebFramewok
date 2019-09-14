package hr.ja.fr;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TagUtil {

	private static List<CommandJs> commands = new ArrayList<>();

	public static void add(MyTag source, MyTag tag) {
		CommandJs command = new CommandJs();
		command.elementId = source.getId();
		command.eventType = CommandJs.EventType.TAG_ADD;
		command.html = tag.toString();
		commands.add(command);
	}

	public static void append(String html, MyTag myTag) {

	}
	
	public static void text(String text, MyTag myTag) {
		
	}

	public static void addToBody(MyTag tag) {
		CommandJs command = new CommandJs();
		command.elementId = "body";
		command.eventType = CommandJs.EventType.TAG_ADD;
		command.html = tag.toString();
		commands.add(command);
	}

	public static List<CommandJs> getCommands() {
		return commands;
	}

	private static ObjectMapper mappers = new ObjectMapper();

	public static String getCommandsJson() {
		try {
			return mappers.writeValueAsString(commands);
		} catch (JsonProcessingException e) {
			log.error("", e);
			return "=??=";
		}
	}

	

}

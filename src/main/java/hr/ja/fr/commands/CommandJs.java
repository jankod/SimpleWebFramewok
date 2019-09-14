package hr.ja.fr.commands;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public abstract class CommandJs {

	@Setter
	private String funcName;

	private List<Object> args;

	public void setArguments(Object...argsParam ) {
		args = new ArrayList<>(argsParam.length);
		for (Object object : argsParam) {
			args.add(object);
		}
//		log.debug("{}", args);
		
	}

	public String format(String exec, Object... args) {
		FormattingTuple arrayFormat = MessageFormatter.arrayFormat(exec, args);
		return arrayFormat.getMessage();
	}

}

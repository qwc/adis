package to.mmo.adis.parser;

import java.util.regex.Pattern;

public class RequestParser implements IParser {
	private Pattern pattern;
	private Pattern itemPattern;
	
	public RequestParser() {
		pattern = Pattern.compile("^R(N|F)(\\d{6})(\\d+)$");
		itemPattern = Pattern.compile("((\\d{8})(\\d{2})(\\d))");
	}

	@Override
	public Object parse(String line) {
		return null;
	}
}

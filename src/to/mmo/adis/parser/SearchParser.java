package to.mmo.adis.parser;

import java.util.regex.Pattern;

public class SearchParser {
	
	private Pattern pattern;
	private Pattern itemPattern;
	
	public SearchParser() {
		pattern = Pattern.compile("^S(.)(\\d{6})(\\d+)$");
		itemPattern = Pattern.compile("(\\d+)([\\d? ]{6})");
	}

}

package to.mmo.adis.parser;

import java.util.regex.Pattern;

import to.mmo.adis.SearchValue;

public class SearchParser implements IParser{
	
	private Pattern pattern;
	private Pattern itemPattern;
	
	public SearchParser() {
		pattern = Pattern.compile("^S(.)(\\d{6})(\\d+)$");
		itemPattern = Pattern.compile("(\\d+)([0-9? ]{6})");
	}

	@Override
	public SearchValue parse(String line) {
		return null;
	}

}

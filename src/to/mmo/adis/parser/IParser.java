package to.mmo.adis.parser;

public interface IParser {
	
	public Object parse(String line) throws ADISParseException;

}

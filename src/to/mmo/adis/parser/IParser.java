package to.mmo.adis.parser;

import to.mmo.adis.ADISException;

public interface IParser {
	
	public Object parse(String line) throws ADISException;

}

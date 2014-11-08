package to.mmo.adis.handler;

import to.mmo.adis.RequestValue;

public interface RequestHandler {
	
	String entity();
	
	void handle(RequestValue value);

}

package to.mmo.adis.handler;

import to.mmo.adis.EntityValue;

public interface EntityHandler {

	String entity();

	void handle(EntityValue ev);

}

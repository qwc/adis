package eu.orthanc.jisoagrinet.aded;

import eu.orthanc.jisoagrinet.common.EntityValue;
import eu.orthanc.jisoagrinet.common.ItemValue;

public interface ADED {

	Entity getEntity(String entity);

	Item getItem(String item);

	String getVersion();

	boolean validate(EntityValue value, Entity entity);

	boolean validate(ItemValue value, Item item);

}

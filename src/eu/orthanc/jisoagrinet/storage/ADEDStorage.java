package eu.orthanc.jisoagrinet.storage;

import eu.orthanc.jisoagrinet.aded.Codeset;
import eu.orthanc.jisoagrinet.aded.Entity;
import eu.orthanc.jisoagrinet.aded.Item;

public interface ADEDStorage {

	void putADEDVersion(String version);

	void putEntity(Entity entity);

	void putItem(Item item);

	void putCodeset(Codeset codeset);

}

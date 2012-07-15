package eu.orthanc.jisoagrinet.storage;

import eu.orthanc.jisoagrinet.aded.Codeset;
import eu.orthanc.jisoagrinet.aded.Entity;
import eu.orthanc.jisoagrinet.aded.Item;


public interface ADEDPersistency {

	Entity getEntity(String entity);

	Item getItem(String item);

	Codeset getCodeset(String codeset);

}

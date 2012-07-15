/* 
 * @project jisoagrinet
 * @file ADEDStorage.java
 * @date 15.07.2012
 * 
 * @author Marcel M. Otte
 * Copyright (c) 2012 - 2012, Marcel M. Otte
 * @license LGPL
 */
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

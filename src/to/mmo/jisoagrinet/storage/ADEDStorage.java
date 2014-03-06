/* 
 * Project: jisoagrinet
 * File: ADEDStorage.java
 * Date: 22.07.2012
 * 
 * Copyright (c) 2012, Marcel M. Otte
 * License: LGPL
 */
package to.mmo.jisoagrinet.storage;

import to.mmo.jisoagrinet.aded.Codeset;
import to.mmo.jisoagrinet.aded.Entity;
import to.mmo.jisoagrinet.aded.Item;

public interface ADEDStorage {

	void putADEDVersion(String version);

	void putEntity(Entity entity);

	void putItem(Item item);

	void putCodeset(Codeset codeset);

}

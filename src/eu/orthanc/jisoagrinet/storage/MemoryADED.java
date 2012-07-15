/* 
 * @project jisoagrinet
 * @file MemoryADED.java
 * @date 15.07.2012
 * 
 * @author Marcel M. Otte
 * Copyright (c) 2012 - 2012, Marcel M. Otte
 * @license LGPL
 */
package eu.orthanc.jisoagrinet.storage;

import java.util.HashMap;
import java.util.Map;

import eu.orthanc.jisoagrinet.aded.Codeset;
import eu.orthanc.jisoagrinet.aded.Entity;
import eu.orthanc.jisoagrinet.aded.Item;

/**
 * @author qwc
 * 
 */
public class MemoryADED implements ADEDPersistency, ADEDStorage {
	private String version;
	private Map<String, Entity> entities;
	private Map<String, Item> items;
	private Map<String, Codeset> codesets;

	public MemoryADED() {
		entities = new HashMap<String, Entity>();
		items = new HashMap<String, Item>();
		codesets = new HashMap<String, Codeset>();
	}

	@Override
	public Entity getEntity(String entity) {
		return entities.get(entity);
	}

	@Override
	public Item getItem(String item) {
		return items.get(item);
	}

	@Override
	public Codeset getCodeset(String codeset) {
		return codesets.get(codeset);
	}

	@Override
	public void putADEDVersion(String version) {
		this.version = version;
	}

	@Override
	public void putEntity(Entity entity) {
		entities.put(entity.getNumber(), entity);
	}

	@Override
	public void putItem(Item item) {
		items.put(item.getNumber(), item);
	}

	@Override
	public void putCodeset(Codeset codeset) {
		codesets.put(codeset.getNumber(), codeset);
	}

}

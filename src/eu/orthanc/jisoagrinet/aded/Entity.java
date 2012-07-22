/* 
 * Project: jisoagrinet
 * File: Entity.java
 * Date: 22.07.2012
 * 
 * Copyright (c) 2012, Marcel M. Otte
 * License: LGPL
 */
package eu.orthanc.jisoagrinet.aded;

import java.util.ArrayList;
import java.util.List;

public class Entity implements IEntity {

	private String number;
	private String name;
	private String ddVersion;
	private List<ItemEntry> items;

	public Entity(String number, String name, String ddVersion) {
		this.number = number;
		this.name = name;
		this.ddVersion = ddVersion;
		items = new ArrayList<ItemEntry>();
	}

	@Override
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDataDictionaryVersion() {
		return ddVersion;
	}

	public void setDDVersion(String version) {
		this.ddVersion = version;
	}

	@Override
	public List<ItemEntry> getItems() {
		return items;
	}

	public void addItem(IItem item, Type type) {
		items.add(new ItemEntry(item, type));
	}

	public void addItem(int sequenceNo, IItem item, Type type) {
		items.add(sequenceNo, new ItemEntry(item, type));
	}

	@Override
	public Type getTypeOf(IItem item) {
		for (ItemEntry e : items) {
			if (e.getItem().equals(item)) {
				return e.getType();
			}
		}
		return null;
	}

	@Override
	public boolean hasItem(IItem item) {
		for (ItemEntry e : items) {
			if (e.getItem().equals(item)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getSequenceNumberOf(IItem item) {
		for (ItemEntry e : items) {
			if (e.getItem().equals(item)) {
				return items.indexOf(e);
			}
		}
		return -1;
	}

}

/* 
 * @project jisoagrinet
 * @file EntityValue.java
 * @date 15.07.2012
 * 
 * @author Marcel M. Otte
 * Copyright (c) 2012 - 2012, Marcel M. Otte
 * @license LGPL
 */
package eu.orthanc.jisoagrinet.common;

import java.util.ArrayList;
import java.util.List;

public class EntityValue {
	private String entity;
	private List<ItemValue> values;
	private boolean isHeader;

	public EntityValue() {
		values = new ArrayList<ItemValue>();
	}

	public EntityValue(String entity) {
		this();
		this.setEntity(entity);
	}

	public void addValue(ItemValue value) {
		getValues().add(value);
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public List<ItemValue> getValues() {
		return values;
	}

	public boolean isHeader() {
		return isHeader;
	}

	public void setHeader(boolean isHeader) {
		this.isHeader = isHeader;
	}
}

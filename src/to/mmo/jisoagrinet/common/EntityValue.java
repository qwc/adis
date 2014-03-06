/* 
 * Project: jisoagrinet
 * File: EntityValue.java
 * Date: 22.07.2012
 * 
 * Copyright (c) 2012, Marcel M. Otte
 * License: LGPL
 */
package to.mmo.jisoagrinet.common;

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

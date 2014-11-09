/* 
 * Project: jisoagrinet
 * File: EntityValue.java
 * Date: 22.07.2012
 * 
 * Copyright (c) 2012, Marcel M. Otte
 * License: LGPL
 */
package to.mmo.adis;

import java.util.ArrayList;
import java.util.List;

import to.mmo.adis.ItemValue.ItemError;

public class EntityValue {
	private String entity;
	private List<ItemValue> values;
	private boolean isHeader;
	private ADIS.Status status;
	private DefinitionError error;
	private ItemError itemError;

	public static class DefinitionError {
		public boolean isError;
		public int position;

		public DefinitionError() {
			isError = false;
			position = -1;
		}
	}

	public EntityValue() {
		values = new ArrayList<ItemValue>();
		error = new DefinitionError();
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

	public String toString() {
		String data = "Entity: " + entity + " (Header?" + isHeader + ")\n";
		for (ItemValue v : values) {
			data += "Item: " + v.getItem() + " Len: " + v.getLength()
					+ " Res: " + v.getResolution() + " Value: " + v.getValue()
					+ "\n";
		}
		return data;
	}

	public ADIS.Status getStatus() {
		return status;
	}

	public void setStatus(ADIS.Status status) {
		this.status = status;
	}

	public void setError(boolean isError, int position) {
		this.error.isError = isError;
		this.error.position = position;
	}

	public DefinitionError getError() {
		return error;
	}

	public void setItemError(boolean isError, int position) {
		this.itemError.isError = isError;
		this.itemError.position = position;
	}

	public ItemError getItemError() {
		return itemError;
	}
}

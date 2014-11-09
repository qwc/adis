/* 
 * Project: jisoagrinet
 * File: ItemValue.java
 * Date: 22.07.2012
 * 
 * Copyright (c) 2012, Marcel M. Otte
 * License: LGPL
 */
package to.mmo.adis;

import to.mmo.aded.IItem;
import to.mmo.aded.IItem.Format;

public class ItemValue implements Cloneable {
	private String item;
	private String value;
	private int length;
	private int resolution;
	private ItemRestriction restriction;
	private ItemError error;
	private IItem.Format format;

	public static class ItemError {
		public int position;
		public boolean isError;

		public ItemError() {
			this.isError = false;
			this.position = -1;
		}
	}

	public static class ItemRestriction {
		public String min;
		public String max;
	}

	public ItemValue() {
		error = new ItemError();
	}

	public ItemValue(String item, int length, int resolution, String value) {
		this();
		this.setLength(length);
		this.resolution = resolution;
		this.setItem(item);
		this.setValue(value);
	}

	public ItemValue(String item, int length, int resolution) {
		this();
		this.setLength(length);
		this.resolution = resolution;
		this.setItem(item);
		this.value = null;
	}

	public ItemValue clone() {
		return new ItemValue(item, length, resolution, value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		if (!value.matches("\\?+"))
			this.value = value;
		else
			this.value = null;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getResolution() {
		return resolution;
	}

	public void setResolution(int resolution) {
		this.resolution = resolution;
	}

	public Integer getValueAsInteger() {
		if (this.resolution == 0) {
			return Integer.parseInt(value);
		}
		return null;
	}

	public Double getValueAsDouble() {
		if (this.resolution > 0) {
			return Double.parseDouble(value);
		}
		return null;
	}

	public ItemRestriction getRestriction() {
		return restriction;
	}

	public void setRestriction(ItemRestriction restriction) {
		this.restriction = restriction;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public void setError(boolean isError, int position) {
		this.error.isError = isError;
		this.error.position = position;
	}

	public ItemError getError() {
		return error;
	}
}

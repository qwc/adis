/* 
 * @project jisoagrinet
 * @file ItemValue.java
 * @date 15.07.2012
 * 
 * @author Marcel M. Otte
 * Copyright (c) 2012 - 2012, Marcel M. Otte
 * @license LGPL
 */
package eu.orthanc.jisoagrinet.common;

public class ItemValue implements Cloneable {
	private String item;
	private String value;
	private int length;
	private int resolution;

	public ItemValue() {
	}

	public ItemValue(String item, int length, int resolution, String value) {
		this.setLength(length);
		this.resolution = resolution;
		this.setItem(item);
		this.value = value;
	}

	public ItemValue(String item, int length, int resolution) {
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
		this.value = value;
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
}

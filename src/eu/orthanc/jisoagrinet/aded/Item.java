/* 
 * Project: jisoagrinet
 * File: Item.java
 * Date: 22.07.2012
 * 
 * Copyright (c) 2012, Marcel M. Otte
 * License: LGPL
 */
package eu.orthanc.jisoagrinet.aded;

public class Item implements IItem {

	private String number;
	private String identity;
	private String name;
	private Format format;
	private int length;
	private int resolution;
	private String unit;
	private String minimum;
	private String maximum;
	private String ddVersion;
	private String comment;
	private ICodeset codeset;

	public Item(String number, String identity, String name, Format format,
			int length, int resolution) {
		this(number, identity, name, format, length, resolution, null, null,
				null, null, null, null);
	}

	public Item(String number, String identity, String name, Format format,
			int length, int resolution, String unit, ICodeset codeset,
			String minimum, String maximum, String comment, String ddVersion) {
		this.number = number;
		this.identity = identity;
		this.name = name;
		this.format = format;
		this.length = length;
		this.resolution = resolution;
		this.unit = unit;
		this.codeset = codeset;
		this.minimum = minimum;
		this.maximum = maximum;
		this.comment = comment;
		this.ddVersion = ddVersion;
	}

	@Override
	public String getNumber() {
		return number;
	}

	@Override
	public String getIdentity() {
		return identity;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Format getFormat() {
		return format;
	}

	@Override
	public int getLength() {
		return length;
	}

	@Override
	public int getResolution() {
		return resolution;
	}

	@Override
	public String getUnit() {
		return unit;
	}

	@Override
	public ICodeset getCodeset() {
		return codeset;
	}

	@Override
	public String getMinimum() {
		return minimum;
	}

	@Override
	public String getMaximum() {
		return maximum;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public String getDDVersion() {
		return ddVersion;
	}

	@Override
	public boolean hasCodeset() {
		return codeset != null;
	}

}

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
	private String codesetHint;

	public Item(String number, String identity, String name, Format format,
			int length, int resolution) {
		this(number, identity, name, format, length, resolution, null, null,
				null, null, null, null);
	}

	public Item(String number, String identity, String name, Format format,
			int length, int resolution, String unit, String codeset,
			String minimum, String maximum, String comment, String ddVersion) {
		String emptyRegex = "^\\?+$";
		if (unit.matches(emptyRegex))
			unit = null;
		if (minimum.matches(emptyRegex))
			minimum = null;
		if (maximum.matches(emptyRegex))
			maximum = null;
		if (comment.matches(emptyRegex))
			comment = null;
		if (ddVersion.matches(emptyRegex))
			ddVersion = null;

		this.number = number;
		this.identity = identity;
		this.name = name;
		this.format = format;
		this.length = length;
		this.resolution = resolution;
		this.unit = unit;
		this.codesetHint = codeset;
		this.minimum = minimum;
		this.maximum = maximum;
		this.comment = comment;
		this.ddVersion = ddVersion;
	}

	public String getCodeSetHint() {
		return codesetHint;
	}

	public void setCodeSet(ICodeset codeset) {
		this.codeset = codeset;
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

	public void setNumber(String number) {
		this.number = number;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setResolution(int resolution) {
		this.resolution = resolution;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setMinimum(String minimum) {
		this.minimum = minimum;
	}

	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}

	public void setDdVersion(String ddVersion) {
		this.ddVersion = ddVersion;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setCodesetHint(String codesetHint) {
		this.codesetHint = codesetHint;
	}

	public String toString() {
		String item = "    Item: ";
		return null;
	}

}

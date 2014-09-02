/* 
 * Project: jisoagrinet
 * File: IItem.java
 * Date: 22.07.2012
 * 
 * Copyright (c) 2012, Marcel M. Otte
 * License: LGPL
 */
package to.mmo.aded;

public interface IItem {

	public enum Format {
		N, AN
	};

	String getNumber();

	String getIdentity();

	String getName();

	Format getFormat();

	int getLength();

	int getResolution();

	String getUnit();

	ICodeset getCodeset();

	Object getMinimum();

	Object getMaximum();

	String getComment();

	String getDDVersion();

	boolean hasCodeset();

}

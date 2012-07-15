/* 
 * @project jisoagrinet
 * @file IItem.java
 * @date 15.07.2012
 * 
 * @author Marcel M. Otte
 * Copyright (c) 2012 - 2012, Marcel M. Otte
 * @license LGPL
 */
package eu.orthanc.jisoagrinet.aded;

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

}

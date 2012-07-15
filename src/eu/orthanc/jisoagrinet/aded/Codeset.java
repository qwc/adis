/* 
 * @project jisoagrinet
 * @file Codeset.java
 * @date 15.07.2012
 * 
 * @author Marcel M. Otte
 * Copyright (c) 2012 - 2012, Marcel M. Otte
 * @license LGPL
 */
package eu.orthanc.jisoagrinet.aded;

import java.util.ArrayList;
import java.util.List;

public class Codeset implements ICodeset {

	private String number;
	private String name;
	private String ddVersion;
	private List<Element> elements;

	public Codeset(String number, String name, String ddVersion) {
		this.number = number;
		this.name = name;
		this.ddVersion = ddVersion;
		elements = new ArrayList<Element>();
	}

	public void addElement(Object key, Object definition) {
		elements.add(new Element(key, definition));
	}

	@Override
	public String getNumber() {
		return number;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDataDictionaryVersion() {
		return ddVersion;
	}

	@Override
	public List<Element> getElements() {
		return elements;
	}

}

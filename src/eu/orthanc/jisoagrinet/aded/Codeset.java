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

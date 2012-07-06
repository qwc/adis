package eu.orthanc.jisoagrinet.common;

import eu.orthanc.jisoagrinet.aded.IItem;

public class ItemValue {
	private IItem reference;
	private String value;

	public ItemValue() {
	}

	public ItemValue(IItem reference, String value) {
		this.reference = reference;
		this.value = value;
	}

	public IItem getReference() {
		return reference;
	}

	public String getValue() {
		return value;
	}

	public void setReference(IItem item) {
		this.reference = item;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

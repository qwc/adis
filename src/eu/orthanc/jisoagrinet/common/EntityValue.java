package eu.orthanc.jisoagrinet.common;

import java.util.ArrayList;
import java.util.List;

public class EntityValue {
	private String entity;
	private List<ItemValue> values;

	public EntityValue() {
		values = new ArrayList<ItemValue>();
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
}

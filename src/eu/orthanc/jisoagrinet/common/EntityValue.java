package eu.orthanc.jisoagrinet.common;

import java.util.ArrayList;
import java.util.List;

import eu.orthanc.jisoagrinet.aded.IEntity;
import eu.orthanc.jisoagrinet.aded.IItem;

public class EntityValue {
	private IEntity reference;
	private List<ItemValue> values;

	public EntityValue(IEntity reference) {
		this.reference = reference;
		values = new ArrayList<ItemValue>();
	}

	public void addValue(IItem item, String value) {
		values.add(new ItemValue(item, value));
	}

}

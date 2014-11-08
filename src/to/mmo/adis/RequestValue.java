package to.mmo.adis;

import java.util.ArrayList;
import java.util.List;

public class RequestValue {

	private ArrayList<SearchValue> searchFilters;
	private EntityValue entity;
	private int maxEntries;
	private boolean error;

	public RequestValue() {
		this.searchFilters = new ArrayList<SearchValue>();
	}

	public RequestValue(EntityValue entity, int maxEntries) {
		this();
		this.entity = entity;
		this.maxEntries = maxEntries;
	}

	public EntityValue getEntity() {
		return entity;
	}

	public void setEntity(EntityValue entity) {
		this.entity = entity;
	}

	public int getMaxEntries() {
		return maxEntries;
	}

	public void setMaxEntries(int maxEntries) {
		this.maxEntries = maxEntries;
	}

	public void addSearchValue(SearchValue value) {
		searchFilters.add(value);
	}

	public List<SearchValue> getSearchValues() {
		return searchFilters;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
}

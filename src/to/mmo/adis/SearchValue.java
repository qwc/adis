package to.mmo.adis;

public class SearchValue {

	private EntityValue entity;
	private int maxEntries;
	private boolean error;

	public SearchValue() {

	}

	public SearchValue(EntityValue entity, int maxentries) {
		this();
		this.entity = entity;
		maxEntries = maxentries;
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

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

}

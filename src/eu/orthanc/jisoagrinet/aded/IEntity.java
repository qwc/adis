package eu.orthanc.jisoagrinet.aded;

import java.util.List;

public interface IEntity {

	public enum Type {
		KEY, MAN, CON, OPT
	}

	public static class ItemEntry {
		private IItem item;
		private Type type;

		public ItemEntry(IItem item, Type type) {
			this.item = item;
			this.type = type;
		}

		void setItem(IItem item) {
			this.item = item;
		}

		void setType(Type type) {
			this.type = type;
		}

		Type getType() {
			return type;
		}

		IItem getItem() {
			return item;
		}
	}

	String getNumber();

	String getName();

	String getDataDictionaryVersion();

	List<ItemEntry> getItems();

	// Informational methods

	Type getTypeOf(IItem item);

	boolean hasItem(IItem item);

	int getSequenceNumberOf(IItem item);

}

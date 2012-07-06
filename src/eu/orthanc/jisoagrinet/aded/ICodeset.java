package eu.orthanc.jisoagrinet.aded;

import java.util.List;

public interface ICodeset {

	public static class Element {
		private Object key;
		private Object definition;

		public Element(Object key, Object definition) {
			this.key = key;
			this.definition = definition;
		}

		public void setKey(Object key) {
			this.key = key;
		}

		public void setDefinition(Object definition) {
			this.definition = definition;
		}

		public Object getKey() {
			return key;
		}

		public Object getDefinition() {
			return definition;
		}
	}

	String getNumber();

	String getName();

	String getDataDictionaryVersion();

	List<Element> getElements();

}

/* 
 * Project: jisoagrinet
 * File: ICodeset.java
 * Date: 22.07.2012
 * 
 * Copyright (c) 2012, Marcel M. Otte
 * License: LGPL
 */
package to.mmo.adis.structure;

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

	String getDefinition(String key);

}

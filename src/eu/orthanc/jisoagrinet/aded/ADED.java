/* 
 * @project jisoagrinet
 * @file ADED.java
 * @date 15.07.2012
 * 
 * @author Marcel M. Otte
 * Copyright (c) 2012 - 2012, Marcel M. Otte
 * @license LGPL
 */
package eu.orthanc.jisoagrinet.aded;

import eu.orthanc.jisoagrinet.Configuration;
import eu.orthanc.jisoagrinet.aded.IEntity.ItemEntry;
import eu.orthanc.jisoagrinet.aded.IEntity.Type;
import eu.orthanc.jisoagrinet.aded.IItem.Format;
import eu.orthanc.jisoagrinet.common.EntityValue;
import eu.orthanc.jisoagrinet.common.ItemValue;
import eu.orthanc.jisoagrinet.storage.ADEDPersistency;

/* Plans:
 * Store information in database. Get Entity/Item data on demand from DB
 * 
 * Validate each start of the server or client part of the library versus the xml defined structures, to gather new information if present. 
 * Database.CODE stores all information on start in the memory. (will take time...)
 */

public class ADED {

	private ADEDPersistency persistency;

	public ADED(ADEDPersistency persistency) {
		this.persistency = persistency;
	}

	public IEntity getEntity(String entity) {
		return persistency.getEntity(entity);
	}

	public IItem getItem(String item) {
		return persistency.getItem(item);
	}

	public boolean validate(EntityValue value) {
		IEntity entity = getEntity(value.getEntity());
		boolean validation = true;
		// check items for validity
		for (ItemValue i : value.getValues()) {
			if (!validate(i)) {
				validation = false;
			}
		}
		// check mandatory items for entity in strict mode
		if (Configuration.getBooleanProperty("jisoagrinet.validation.strict")) {
			boolean found = false;
			for (ItemEntry e : entity.getItems()) {
				if (e.getType() == Type.MAN) {
					found = false;
					for (ItemValue i : value.getValues()) {
						if (e.getItem().getNumber().equals(i.getItem())) {
							found = true;
							break;
						}
					}
					if (!found) {
						validation = false;
						break;
					}
				}
			}
		}
		return validation;
	}

	public boolean validate(ItemValue value) {
		IItem item = getItem(value.getItem());
		boolean validation = true;
		if (item.getLength() != value.getLength()
				&& item.getResolution() != value.getResolution()) {
			return false;
		}
		// check content for numeric and alphanumeric
		if (item.getFormat() == Format.N) {
			String numeric = value.getValue().trim();
			if (item.getResolution() > 0) {
				try {
					Double.parseDouble(numeric.substring(0, numeric.length()
							- item.getResolution())
							+ "."
							+ numeric.substring(
									numeric.length() - item.getResolution(),
									numeric.length()));
				} catch (NumberFormatException e) {
					return false;
				}
			}
		}
		// check content for codeset
		if (item.hasCodeset()) {
			if (item.getCodeset().getDefinition(value.getValue()) == null) {
				validation = false;
			}
		}
		return validation;
	}

	public ICodeset getCodeset(String codeset) {
		return persistency.getCodeset(codeset);
	}

}

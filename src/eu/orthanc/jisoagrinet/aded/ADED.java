package eu.orthanc.jisoagrinet.aded;

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

	public Entity getEntity(String entity) {
		return null;
	}

	public Item getItem(String item) {
		return null;
	}

	public String getVersion() {
		return null;
	}

	public boolean validate(EntityValue value, Entity entity) {
		return false;
	}

	public boolean validate(ItemValue value, Item item) {
		return false;
	}

	public Codeset getCodeset(String codeset) {
		return null;
	}

}

package eu.orthanc.jisoagrinet.aded;

import java.util.HashMap;
import java.util.Map;

import eu.orthanc.jisoagrinet.common.EntityValue;
import eu.orthanc.jisoagrinet.common.ItemValue;

/* Plans:
 * Store information in database. Get Entity/Item data on demand from DB
 * 
 * Validate each start of the server or client part of the library versus the xml defined structures, to gather new information if present. 
 * Database.CODE reads stores all information on start in the memory.
 */

public class AgriculturalDataElementDictionary implements ADED {
	private enum Database {
		CODE, POSTGRES, SQLITE, MYSQL
	};

	private Database defaultDatabase = Database.CODE;
	private Map<String, Entity> entities;
	private Map<String, Item> items;
	private Map<String, Codeset> codesets;

	public AgriculturalDataElementDictionary() {
		entities = new HashMap<String, Entity>();
		items = new HashMap<String, Item>();
		codesets = new HashMap<String, Codeset>();
	}

	@Override
	public Entity getEntity(String entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItem(String item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validate(EntityValue value, Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validate(ItemValue value, Item item) {
		// TODO Auto-generated method stub
		return false;
	}

}

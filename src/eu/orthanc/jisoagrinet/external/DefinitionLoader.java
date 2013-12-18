/**
 * Project: jisoagrinet
 * File: LKVNRWDefinitionLoader.java
 * Date: 10.11.2013
 *
 * Copyright (c) 2013, Marcel M. Otte
 * License: LGPL
 */
package eu.orthanc.jisoagrinet.external;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import eu.orthanc.jisoagrinet.aded.ADED;
import eu.orthanc.jisoagrinet.aded.Codeset;
import eu.orthanc.jisoagrinet.aded.Entity;
import eu.orthanc.jisoagrinet.aded.IEntity;
import eu.orthanc.jisoagrinet.aded.IItem.Format;
import eu.orthanc.jisoagrinet.aded.Item;
import eu.orthanc.jisoagrinet.common.ADEDRepository;
import eu.orthanc.jisoagrinet.common.EntityValue;
import eu.orthanc.jisoagrinet.common.ItemValue;
import eu.orthanc.jisoagrinet.parser.ADEDParser;
import eu.orthanc.jisoagrinet.parser.ADEDParser.LineState;
import eu.orthanc.jisoagrinet.storage.MemoryADED;

/*
 * Definition loader for files like the ADIS/ADED files supplied by the LKV NRW and defined with the entities 190001-190016.
 * 
 * 
 */

public class DefinitionLoader {

	private File file;
	private ArrayList<EntityValue> parsedEntities;

	public DefinitionLoader(File f) {
		this.file = f;
	}

	public void runParser() {
		ADEDParser parser = null;
		try {
			parser = new ADEDParser(new FileInputStream(file), System.out,
					new ADEDParser.FinishCondition() {
						@Override
						public boolean getCondition(LineState state) {
							if (state == LineState.Z)
								return false;
							return true;
						}
					});
			parser.run();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		parsedEntities = parser.getParsedEntities();
	}

	public ADEDRepository buildRepository() {
		if (parsedEntities == null || parsedEntities.size() == 0) {
			return null;
		}
		MemoryADED persistency = new MemoryADED();
		ADED aded = new ADED(persistency);
		String adedDef = null;

		for (EntityValue v : parsedEntities) {
			if (v.getEntity().equals("190001")) {
				adedDef = v.getValues().get(0).getValue();
			}
			// entity
			if (v.getEntity().equals("190002")) {
				persistency.putEntity(new Entity(v.getValues().get(1)
						.getValue(), v.getValues().get(2).getValue(), v
						.getValues().get(0).getValue()));
			}
			// item
			if (v.getEntity().equals("190003")) {
				List<ItemValue> values = v.getValues();
				persistency.putItem(new Item(values.get(1).getValue(), values
						.get(11).getValue(), values.get(11).getValue(), values
						.get(2).getValue().equals("AN") ? Format.AN : Format.N,
						values.get(3).getValueAsInteger(), values.get(4)
								.getValueAsInteger(), values.get(5).getValue(),
						values.get(10).getValue(), values.get(7).getValue(),
						values.get(8).getValue(), null, values.get(0)
								.getValue()));
			}
			if (v.getEntity().equals("190009")) {
				Item item = persistency
						.getItem(v.getValues().get(3).getValue());
				item.setComment(v.getValues().get(2).getValue());
			}
			if (v.getEntity().equals("190004")) {
				Entity entity = persistency.getEntity(v.getValues().get(1)
						.getValue());
				Item item = persistency
						.getItem(v.getValues().get(2).getValue());
				entity.addItem(v.getValues().get(3).getValueAsInteger(), item,
						IEntity.Type.valueOf(v.getValues().get(4).getValue()));
			}
			if (v.getEntity().equals("190005")) {
				persistency.putCodeset(new Codeset(v.getValues().get(1)
						.getValue(), null, v.getValues().get(0).getValue()));
			}
			if (v.getEntity().equals("190006")) {
				Codeset codeset = persistency.getCodeset(v.getValues().get(1)
						.getValue());
				// codeset.addElement(key, definition);
			}
		}

		return null;
	}

}

/* 
 * Project: jisoagrinet
 * File: ADEDRepository.java
 * Date: 22.07.2012
 * 
 * Copyright (c) 2012, Marcel M. Otte
 * License: LGPL
 */
package to.mmo.aded.common;

import java.util.Map;
import java.util.Set;

import to.mmo.aded.ADED;

public class ADEDRepository {
	public enum ADEDDatabase {
		CODE, POSTGRES, ORACLE, MYSQL
	};

	private Map<String, ADED> repository;

	public ADEDRepository() {

	}

	public Set<String> getRepository() {
		return repository.keySet();
	}

	public ADED getADED(String name) {
		return repository.get(name);
	}

	public String toString() {
		String repo = "ADED repository\n" + "  containing\n";
		for (String n : repository.keySet()) {
			repo += "    " + n + "\n";
		}
		repo += "Contents:\n";
		for (String n : repository.keySet()) {
			repo += repository.get(n).toString();
		}
		return repo;
	}
}

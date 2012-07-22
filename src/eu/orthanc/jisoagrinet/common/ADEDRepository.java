/* 
 * Project: jisoagrinet
 * File: ADEDRepository.java
 * Date: 22.07.2012
 * 
 * Copyright (c) 2012, Marcel M. Otte
 * License: LGPL
 */
package eu.orthanc.jisoagrinet.common;

import java.util.Map;

import eu.orthanc.jisoagrinet.aded.ADED;

public class ADEDRepository {
	public enum ADEDDatabase {
		CODE, POSTGRES, ORACLE, MYSQL
	};

	private Map<String, ADED> repository;

	public ADEDRepository() {

	}

}

/* 
 * @project jisoagrinet
 * @file ADEDRepository.java
 * @date 15.07.2012
 * 
 * @author Marcel M. Otte
 * Copyright (c) 2012 - 2012, Marcel M. Otte
 * @license LGPL
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

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

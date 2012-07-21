package eu.orthanc.jisoagrinet;

public interface IConfiguration {

	String getProperty(String name);

	int getIntegerProperty(String name);

	double getDoubleProperty(String name);

}

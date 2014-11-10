/* 
 * Project: jisoagrinet
 * File: Configuration.java
 * Date: 22.07.2012
 * 
 * Copyright (c) 2012, Marcel M. Otte
 * License: LGPL
 */
package to.mmo.aded.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Configuration {

	private static class ConfigurationSource {
		private Properties properties;
		private ArrayList<String> allProps;

		public ConfigurationSource() {
			allProps = new ArrayList<String>();
			properties = new Properties();
			String file = System.getProperty("jisoagrinet.configuration.file");
			if (file != null) {
				try {
					properties.load(new FileInputStream(new File(file)));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					properties.load(new FileInputStream(new File(
							"default.properties")));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		public String getProperty(String name) {
			if (!allProps.contains(name)) {
				allProps.add(name);
			}
			return properties.getProperty(name);
		}

		public void printAllProperties() {
			for (String p : allProps) {
				System.out.println(p);
			}
		}

		public void save(String file) {
			try {
				FileOutputStream out = new FileOutputStream(new File(file));
				properties.store(out, null);
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static ConfigurationSource source;

	private static ConfigurationSource getSource() {
		if (source == null) {
			source = new ConfigurationSource();
		}
		return source;
	}

	public static String getProperty(String name) {
		return getSource().getProperty(name);
	}

	public static int getIntegerProperty(String name) {
		return Integer.parseInt(getSource().getProperty(name));
	}

	public static double getDoubleProperty(String name) {
		return Double.parseDouble(getSource().getProperty(name));
	}

	public static boolean getBooleanProperty(String name) {
		return Boolean.parseBoolean(getSource().getProperty(name));
	}

	@Deprecated
	// dunno if it will be used some time...
	public static void setProperty(String name, Object value) {

	}

	public static void save(String filename) {
		getSource().save(filename);
	}

	public static void printAll() {
		getSource().printAllProperties();
	}
}

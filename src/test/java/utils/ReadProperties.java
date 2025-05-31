package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

	FileInputStream fis;

	Properties pro;

	String filepath = System.getProperty("user.dir") + "/src/test/resources/config.properties";

	public ReadProperties() {

		try {

			fis = new FileInputStream(filepath);

			pro = new Properties();

			pro.load(fis);

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	// ----Read data from properties file-------------------//

	public String readConfig(String key) {

		return pro.getProperty(key);

	}

}

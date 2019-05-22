package gov.gsa.sam.rms.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is an abstraction over a single properties file. You pass the name
 * of the file (CLASSPATH-relative) and then you can use this class to deal with
 * the Properties file.
 * 
 * The Properties class is pretty nice, so this class doesn't represent any real
 * upgrade, and is purely for pedagogical purposes.
 *
 */
public class PropertiesFileUtility {

	private static final Logger log = Logger.getLogger(PropertiesFileUtility.class.getName());

	private String propertiesFileName;

	private Properties properties;

	/**
	 * Constructor - call this constructor to "wrap" a Properties file with this
	 * class.
	 * 
	 * @param propertiesFileName The name of the Properties file - relative to the
	 *                           CLASSPATH - that you want to process using this
	 *                           class.
	 */
	public PropertiesFileUtility(String propertiesFileName) {

		// Store the properties file name
		this.propertiesFileName = propertiesFileName;

		// Create the Properties object
		properties = new Properties();

		// Load the properties file into the Properties object
		try {
			properties.load(new FileInputStream(propertiesFileName));
			log.info("Properties file '" + propertiesFileName + "' loaded.");
		} catch (IOException e) {
			String message = "Exception while reading properties file '" + propertiesFileName + "':"
					+ e.getLocalizedMessage();
			log.log(Level.SEVERE, message, e);
			// Let's nip this in the bud
			throw new RuntimeException(message, e);
		}
	}

	/**
	 * Fetches a single property whose value will be returned as a String.
	 * 
	 * @param propertyName The name of the property (e.g., the.property.name)
	 * 
	 * @return String - the value of the property as a String (e.g.,
	 *         "propertyValue")
	 */
	public String getProperty(String propertyName) {
		String ret = "";
		log.fine("Looking for property '" + propertyName + "'...");

		// Read the property
		ret = properties.getProperty(propertyName);

		log.fine("Property value is '" + ret + "'");

		return ret;
	}
	
	/**
	   * Saves the properties object to a new file, along with
	   * the COPYRIGHT statement.
	   * 
	   * All properties that have been added will be stored as well.
	   * 
	   * @param makeBackup
	   */
	  public void updateProperty(String propertykey, String updatedvalue) {
	    String outputFileName = this.propertiesFileName;

	    try {

	      // Open an OutputStream to write the properties backup file to
	      FileOutputStream fileOutputStream = new FileOutputStream(outputFileName);

	      // Write the properties. 
	      properties.setProperty(propertykey, updatedvalue);
	      properties.store(fileOutputStream, null);
	    } catch (IOException e) {
	      String message = "Exception occurred: " + e.getLocalizedMessage();
	      log.log(Level.SEVERE, message, e);
	    }
	  }

	/**
	 * Adds a property to this Properties file.
	 * 
	 * @param propertyName
	 * @param propertyValue
	 */
	public void putProperty(String propertyName, String propertyValue) {
		properties.put(propertyName, propertyValue);
	}

}

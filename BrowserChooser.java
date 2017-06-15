/**
 * Launches the appropriate browser based on URL. IE is used for all QAL sites 
 * (based on sites.properties config file), and Chrome is used for everything else 
 * (IE is the fallback if Chrome is not installed).
 *
 * @author	Matthew Douglas
 * @date	15 June 2017
 * @version	0.1
**/

import java.net.*;
import java.io.*;
import java.util.Properties;

public class BrowserChooser {

  /**
   * Reads property file for list of sites that should be opened in IE, then
   * takes String of URL to be launched and calls the appropriate browser.
   *
   * @args	String of URL to be launched
   */
  public static void main (String[] args) {
	
	//Reads properties file for list of sites/domains that IE is preferred for
	Properties prop = new Properties;
	String propFileName = "sites.properties";
	String[] ieDomains;
	String browser;
	String site = args[0];

	InputStream inStream = getClass().getClassLoader().getResourcesAsStream(propFileName);

	try {
		// prop.load(new FileInputStream("\\\\ExampleLocation\\Project\\sites.properties"));
		prop.load(inputStream);
		ieDomains = prop.get("IESITES").toString().split("#");
	catch (IOException e) { 
		System.out.println ("Could not read sites.properties file");
	}

	for (String ieDomain : ieDomains) {
		System.out.println("Checking against domain: " +  ieDomain); // added for testing
		if (getDomainString(site).equals(ieDomain) ) { 
			browser = "iexplore";
		} else {
			File chromeExe = new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
			if (chromeExe.exists()) {
				browser = "chrome";
			} else {
				browser = "iexplore";
			}
		}
	}

	// Launches the site in the appropriate browser
	Process p = Runtime.getRuntime().exec(browser + " " + site);

  }


  /**
   * Returns the domain portion of a URL String.
   *
   * @args	String of URL to be parsed
   */
  private String getDomainString(String urlString) {
    URL url = null;
    String domainString = null;
    try {
        uri = new URI(urlString);
        //String[] domainNameParts = url.getHost().split("\\.");
        //domainString = domainNameParts[1];
		//or:  domainString = url.split("://")[1].split(":")[0];
		domainString = url.getHost();		
    }
    catch (MalformedURLException e) {
		System.out.println("Can't read URL");
		domainString = "";
    }

    return domainString;
  }
}

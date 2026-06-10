package commonUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EssentialFunctions {
	
	public static String generateEmail()
	{
		return("skj"+System.currentTimeMillis()+"@gmail.com");
	}

	public static String PropertyReaderForQAfox(String datainput) throws IOException 
	{
		Properties prop=new Properties();
		FileInputStream fp= new FileInputStream("C:\\Users\\itscm\\AutomationProjectQAFox\\QaFoxWebsite\\src\\test\\resources\\liveQaFoxProject.properties");
		prop.load(fp);
		String data=prop.getProperty(datainput);
		return data;		
		
	}
}

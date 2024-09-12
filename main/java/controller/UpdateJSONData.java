package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class UpdateJSONData {

	// Change this main to a APIs after successful development of all APIs, till
	// then run it as java-application for execution.
	public void updateJSONData() {
		System.out.println(
				"It will take 3 minutes. \nAre you sure you want to override old files and data in it?\n Enter \"Y\" to confirm and \"N\" to cancel:");
		Scanner scan = new Scanner(System.in);
		String writeNewData = scan.next();
		System.out.println(writeNewData.equalsIgnoreCase("Y") ? whichFunctionToUse() : "APIs data Update cancelled.");
		scan.close();
	}

	private String whichFunctionToUse() {
		System.out.print("Use \n1). JSON-Simple\n2). JackSon (default) \nEnter 1 or 2: - ");
		Scanner scan = new Scanner(System.in);
		String whichFunctionTouse = scan.next().toUpperCase();
		scan.close();
		return (whichFunctionTouse == "1") ? UpdateFunc_JSONSimple.fetchNewData() : UpdateFunc_Jackson.fetchNewData();
	}

	@SuppressWarnings("unused")
	private void checkConfigProps() throws IOException{
		System.out.println("This is just to check, how to access enums.S");
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = ClassLoader.getSystemClassLoader().getResourceAsStream("configs.properties");
			prop.load(input); // Adding all the data in prop variable.
			// All the values in ENUM
			System.out.println(prop.toString());
		} catch (IOException io) {
			io.printStackTrace();
		}
// 		Checking values in Enumeration data
//		System.out.println("ENUMS name: -" + APIsGettingEndpoints.type_effectiveness);
//		System.out.println("ENUMS name attached: -" + APIsGettingEndpoints.type_effectiveness.name());
//		System.out.println("ENUMS value attached: -" + APIsGettingEndpoints.type_effectiveness.getAction());
	}
}
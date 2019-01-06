package ExtractData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvRead {

	//Delimiter used in CSV file
		private static final String COMMA_DELIMITER = ",";
	
		
	
		public static List<Drivers> readCsvFile(String fileName) {

			BufferedReader fileReader = null;
			String surName = "";
			//Create a new list of student to be filled by CSV file data 
        	List<Drivers> Clients = new ArrayList<Drivers>();
	     
	        try {
	        	
	        
	        	
	            String line = "";
	            
	            //Create the file reader
	            fileReader = new BufferedReader(new FileReader(fileName));
	            
	            //Read the CSV file header to skip it
	            fileReader.readLine();
	            
	            //Read the file line by line starting from the second line
	            while ((line = fileReader.readLine()) != null) {
	                //Get all tokens available in line
	                String[] tokens = line.split(COMMA_DELIMITER);
//	                if (tokens.length > 0) {
	                surName = "";

	                if ((tokens.length == 2)) {
	                	surName = tokens[1];
//						System.out.println(tokens[1]);

	                }
	                
                	//Create a new student object and fill his  data
					Drivers student = new Drivers(tokens[0], surName);
					Clients.add(student);
	         
	            }
	            

	            
	            //Print the new student list
	            for (Drivers driver : Clients) {
					System.out.println(driver.getFirstName());
					System.out.println(driver.getLastName());

					
				}
	        } 
	        catch (Exception e) {
	        	System.out.println("Error in CsvFileReader !!!");
	            e.printStackTrace();
	        } finally {
	            try {
	                fileReader.close();
	            } catch (IOException e) {
	            	System.out.println("Error while closing fileReader !!!");
	                e.printStackTrace();
	            }
	        }
			return Clients;

		}
}

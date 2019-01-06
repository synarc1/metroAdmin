package ExtractData;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWrite {
	
	
	List <JourneyInfo> client;

	private static final String COMMA_DELIMITER = ",";
	 private static final String NEW_LINE_SEPARATOR = "\n";

	private static final String FILE_HEADER = "Client Name,Pay Type, Fare, Driver,"
			+ " Plate Number,Destination Lat, Origin Lat, Origin Lon";
	
	 public static void writeCsvFile(String fileName, List<JourneyInfo> client) {
		  FileWriter fileWriter = null;
    
    try {
    	fileWriter = new FileWriter(fileName);
    	
		fileWriter.append(FILE_HEADER.toString());
		
		fileWriter.append(NEW_LINE_SEPARATOR);
		
		for (JourneyInfo journeyInfo: client) {
			
			fileWriter.append(journeyInfo.getClientName());
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(journeyInfo.getPayType());
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(journeyInfo.getFare());
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(journeyInfo.getDriver());
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(journeyInfo.getPlateNumber());
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(journeyInfo.getDesLat());
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(journeyInfo.getOriLat());
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(journeyInfo.getOriLon());
			fileWriter.append(NEW_LINE_SEPARATOR);

		}
		System.out.println("CSV file was created successfully !!!");
		
		 
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		
		try {
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	  }

}

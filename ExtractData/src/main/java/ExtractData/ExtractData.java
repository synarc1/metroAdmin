package ExtractData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class ExtractData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		
		List<JourneyInfo> client = new ArrayList<JourneyInfo>();
	    final AtomicBoolean done = new AtomicBoolean(false);
	    String fileName = System.getProperty("user.home")+"/Documents/Extract.csv";
	    String fileName2 = System.getProperty("user.home")+"/Documents/ClientsTest.csv";
	    List<Drivers> drivers = new ArrayList<Drivers>();

	   
		
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream("/home/isaac/Documents/Json/serviceAccountKey.json");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Initialize the app with a service account, granting admin privileges
		FirebaseOptions options = null;
		try {
			options = new FirebaseOptions.Builder()
			    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
			    .setDatabaseUrl("https://metrolinq-9e482.firebaseio.com/")
			    .build();
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FirebaseApp.initializeApp(options);
		
		
		
		DatabaseReference ref = FirebaseDatabase.getInstance()
			    .getReference("TestCompleteJourney");
		DatabaseReference driverRef = FirebaseDatabase.getInstance()
			    .getReference("Clients");
		    
		    
		    ref.addListenerForSingleValueEvent(new ValueEventListener() {
		    	
				  @Override
				  public void onDataChange(DataSnapshot dataSnapshot) {
					    System.out.println("Start reading from Firebase Database");


	                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){

	                    
	                        String name = postSnapshot.child("clientName").getValue().toString();
	                        String fare = postSnapshot.child("fare").getValue().toString();
	                        String payType = postSnapshot.child("payType").getValue().toString();
	                        String driver = postSnapshot.child("driver").getValue().toString();
	                        String plateNumber = postSnapshot.child("plateNumber").getValue().toString();
	                        String desLat = postSnapshot.child("desLat").getValue().toString();
	                        String oriLat = postSnapshot.child("oriLat").getValue().toString();
	                        String oriLon = postSnapshot.child("oriLon").getValue().toString();
	                        String year = postSnapshot.child("year").getValue().toString();
	                        
	                  
	             			client.add(new JourneyInfo(name,payType, fare, driver, plateNumber, 
	             					desLat, oriLat, oriLon, year ));
	             			   
	                   
	                }
     			  done.set(true);

				  }
		
				  @Override
				  public void onCancelled(DatabaseError error) {
					    System.out.println("err");
		
				  }
			});

		    

	        while (!done.get());
		    
	    
		    System.out.println("Write CSV file:");
	        CsvWrite.writeCsvFile(fileName, client);
	        
	        System.out.println("\nRead CSV file:");
	        
	        drivers = CsvRead.readCsvFile(fileName2);
	        
	        done.set(false);
	        
	        //Print the new student list
            for (Drivers driver : drivers) {
				System.out.println(driver.getFirstName());
				System.out.println(driver.getLastName());
				
				
		        String uploadId = driverRef.push().getKey();
				System.out.println(uploadId);

                driverRef.child(uploadId).setValueAsync(driver);
                
//                if (drivers.size() == i ) {
//                    done.set(true);
//                    i = 0;
//                }
//                
//                i++;
			}
            
	        while (!done.get());

		   
		    
	}

}

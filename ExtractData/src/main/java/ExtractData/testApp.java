package ExtractData;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.FirebaseDatabase;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.awt.event.ActionEvent;

public class testApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExtractData.initFirebaseDB();
					testApp window = new testApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public testApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnRetrive = new JButton("Retrive");
		btnRetrive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  String fileName = System.getProperty("user.home")+"/Documents/ExtractDATA2.csv";
				   String fileName2 = System.getProperty("user.home")+"/Documents/ClientsTest.csv";
				   
				    final AtomicBoolean done = new AtomicBoolean(false);

				   
				   DatabaseReference removeRef = FirebaseDatabase.getInstance().getReference("TestCompleteJourney");
				   
				   
				   List<JourneyInfo> journeyInfoRecieved = ExtractData.readFirebaseDB();
				
				System.out.println("Write CSV file:");
		        CsvWrite.writeCsvFile(fileName, journeyInfoRecieved);
		        
				   removeRef.removeValue(new CompletionListener () {

						@Override
						public void onComplete(DatabaseError error, DatabaseReference ref) {
							// TODO Auto-generated method stub
							
							System.out.println("Client Database deleted");
							
							done.set(true);
							
						}
						
					});
					
			        while (!done.get());
		        
			}
		});
		btnRetrive.setBounds(75, 191, 117, 25);
		frame.getContentPane().add(btnRetrive);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.setBounds(291, 191, 117, 25);
		frame.getContentPane().add(btnUpload);
	}
}

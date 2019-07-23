package ParkingManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OWNER {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void ownerpage() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OWNER window = new OWNER();
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
	public OWNER() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 368, 213);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel ownlab1 = new JLabel("Welcome Owner");
		ownlab1.setFont(new Font("Tahoma", Font.BOLD, 11));
		ownlab1.setBounds(35, 23, 97, 14);
		frame.getContentPane().add(ownlab1);
		
		JLabel ownlab2 = new JLabel("Today's Collection:");
		ownlab2.setBounds(35, 59, 97, 14);
		frame.getContentPane().add(ownlab2);
		
		JLabel ownlab3 = new JLabel("Total Cars:");
		
		int nocar = 0;
		//double coll = 0;
		String sum = null;
		try
		{
		 Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/parking?autoReconnect=true&useSSL=false","root","@Kush98#");
		 PreparedStatement mystat1 =myConn.prepareStatement("select count(*) from pparking");
		 ResultSet cnt= mystat1.executeQuery();
		 while(cnt.next())
		 {
			nocar = cnt.getInt("count(*)"); 
		 }
		 PreparedStatement mystat2 =myConn.prepareStatement("select sum(pay) from pparking");
		 ResultSet res = mystat2.executeQuery();
		 res.next();
		 sum = res.getString(1);
	    }
     	catch(Exception exc)
     	{
     		exc.printStackTrace();
     	}
		ownlab3.setBounds(35, 99, 97, 14);
		frame.getContentPane().add(ownlab3);
		
		JLabel ownlab4 = new JLabel(sum);
		ownlab4.setBounds(172, 59, 46, 14);
		frame.getContentPane().add(ownlab4);
		
		JLabel ownlab5 = new JLabel(String.valueOf(nocar));
		ownlab5.setBounds(172, 99, 46, 14);
		frame.getContentPane().add(ownlab5);
	}
}

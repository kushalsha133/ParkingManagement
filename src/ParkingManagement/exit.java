package ParkingManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import java.util.Date;

import java.sql.*;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class exit {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void exitt() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					exit window = new exit();
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
	public exit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Date d=new Date();
      
		
		frame = new JFrame();
		frame.setForeground(Color.WHITE);
		frame.setBounds(100, 100, 375, 294);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterCarNo = new JLabel("Enter Car no:");
		lblEnterCarNo.setBounds(28, 34, 72, 14);
		frame.getContentPane().add(lblEnterCarNo);
		
		textField = new JTextField();
		textField.setBounds(127, 31, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblInTime = new JLabel("In Time:");
		lblInTime.setBounds(26, 118, 46, 14);
		frame.getContentPane().add(lblInTime);
		
		JLabel lblOutTime = new JLabel("Out Time:");
		lblOutTime.setBounds(26, 161, 60, 14);
		frame.getContentPane().add(lblOutTime);
		
		
		JLabel labintime = new JLabel("");
		labintime.setForeground(Color.RED);
		labintime.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labintime.setBounds(127, 161, 222, 14);
		frame.getContentPane().add(labintime);
		
		
		JLabel labouttime = new JLabel("");
		labouttime.setForeground(Color.RED);
		labouttime.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labouttime.setBounds(127, 118, 222, 14);
		frame.getContentPane().add(labouttime);
		
		
		JLabel fare = new JLabel("");
		fare.setForeground(Color.RED);
		fare.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fare.setBounds(131, 202, 72, 14);
		frame.getContentPane().add(fare);		

		
		JButton btnCalculateFare = new JButton("Calculate Fare");
		btnCalculateFare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				double carno = Double.parseDouble(textField.getText());
				
				try
				{
					Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/parking?autoReconnect=true&useSSL=false","root","@Kush98#");
					PreparedStatement mystat =myConn.prepareStatement("update pparking set out_time = ? where car_no=?");
					java.sql.Timestamp sqlTime=new java.sql.Timestamp(d.getTime());
		            mystat.setTimestamp(1,sqlTime);
					mystat.setDouble(2, carno);
					mystat.executeUpdate();	                
					
					PreparedStatement mystat1 =myConn.prepareStatement("update pparking set tot_time = TIMESTAMPDIFF(minute,in_time,out_time) where car_no=?");
					mystat1.setDouble(1, carno);
					mystat1.executeUpdate();
					
					PreparedStatement mystat2 =myConn.prepareStatement("update pparking set pay = ((tot_time/60)*20) where car_no=? and out_time=NULL");
					mystat2.setDouble(1, carno);
					mystat2.executeUpdate();
					
					PreparedStatement mystat3 =myConn.prepareStatement("select * from pparking where car_no=?");
					mystat3.setDouble(1, carno);
					ResultSet rs = mystat3.executeQuery();
					rs.first();
					labintime.setText(rs.getString("in_time"));
					labouttime.setText(rs.getString("out_time"));
					fare.setText(rs.getString(5));
					
					
				}
				catch(Exception exc)
				{
					exc.printStackTrace();
				}
				
			}
		});
		btnCalculateFare.setBounds(53, 72, 257, 23);
		frame.getContentPane().add(btnCalculateFare);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPay.setBounds(141, 227, 89, 23);
		frame.getContentPane().add(btnPay);
		
		JLabel lblFare = new JLabel("Fare:");
		lblFare.setBounds(28, 202, 46, 14);
		frame.getContentPane().add(lblFare);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\eclipse-workplace\\ParkingManagementProgram\\Photos\\exitpager.PNG"));
		lblNewLabel.setBounds(0, 0, 359, 261);
		frame.getContentPane().add(lblNewLabel);
		
	
		
		
		}
}

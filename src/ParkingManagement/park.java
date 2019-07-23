package ParkingManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

import com.fazecast.jSerialComm.*;

import java.sql.*;

public class park{

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void parkk() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					park window = new park();
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
	public park() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Date d=new Date();
		SimpleDateFormat sd=new SimpleDateFormat("HH:mm:ss");
		//String dt=sd.format(d.getTime());
		//System.out.println(sd.format(d.getTime()));
	    
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 390, 367);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		int a[]= new int[100];
		int b[]= new int[5];
		
	
		
		
		
		
		JLabel lblTime = new JLabel("TIME:");
		lblTime.setBounds(56, 185, 46, 14);
		frame.getContentPane().add(lblTime);
		
		JLabel lblEnterCarNo = new JLabel("ENTER CAR NO:");
		lblEnterCarNo.setBounds(56, 232, 92, 14);
		frame.getContentPane().add(lblEnterCarNo);
		
		textField = new JTextField();
		textField.setBounds(184, 229, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(sd.format(d.getTime()));
		lblNewLabel.setBounds(184, 185, 86, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblParkingSpaces = new JLabel("PARKING SPACES:");
		lblParkingSpaces.setBounds(22, 21, 115, 27);
		frame.getContentPane().add(lblParkingSpaces);
		
		JToggleButton button1 = new JToggleButton("1");
		button1.setBounds(147, 83, 46, 27);
		frame.getContentPane().add(button1);
		
		
		
		JToggleButton button2 = new JToggleButton("2");
		button2.setBounds(218, 83, 46, 27);
		frame.getContentPane().add(button2);
		
		
		JToggleButton button3 = new JToggleButton("3");
		button3.setBounds(292, 83, 44, 27);
		frame.getContentPane().add(button3);
		
		JToggleButton button4 = new JToggleButton("4");
		button4.setBounds(292,135, 46, 27);
		frame.getContentPane().add(button4);
		
	
		JToggleButton button5 = new JToggleButton("5");
		button5.setBounds(218, 135, 46, 27);
		frame.getContentPane().add(button5);
		
		
		
		JButton btnNewButton = new JButton("Check Space");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					SerialPort ports[] = SerialPort.getCommPorts();
					SerialPort port= ports[0];
					try
					{
						port.openPort();
						System.out.println("Successfully opened port");
					}catch(Exception ec){
						System.out.println(ec);
					}
				
					port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
			    
					Scanner data =new Scanner(port.getInputStream());
					int number=0,i=0;
					Thread.sleep(200);
					while(data.hasNextLine())
					{
						try{number=Integer.parseInt(data.nextLine());}catch(Exception ez) {}
						a[i]=number;
						i++;			
					}		
			
					if ( port != null ) {
						port.closePort();
					}
					data.close();
						
					int k=0,l,j;
					for(j=0;j<i;j++)
					{
						if(a[j]==9)
						{
							k++;
							if(k==3)
							{   j++;
								for(l=0;l<5;l++)
								{
									b[l]=a[j];
									j++;
								}
								break;
							}
						}
					}
			   }
			    catch(Exception ex)
			    {
			     	ex.printStackTrace();
			    }
				
				
				//writing in file
				
				try
				{
					Connection myConnec = DriverManager.getConnection("jdbc:mysql://localhost:3306/spots?autoReconnect=true&useSSL=false","root","@Kush98#");
					PreparedStatement mystatment =myConnec.prepareStatement("insert into spotts (sp1,sp2,sp3,sp4,sp5,date_time) values(?,?,?,?,?,?)");
					mystatment.setInt(1, b[0]);
					mystatment.setInt(2, b[1]);
					mystatment.setInt(3, b[2]);
					mystatment.setInt(4, b[3]);
					mystatment.setInt(5, b[4]);
					java.sql.Timestamp sqlTime1=new java.sql.Timestamp(d.getTime());	
					mystatment.setTimestamp(6, sqlTime1);
					mystatment.executeUpdate();
				}
				catch(Exception exc)
				{
					exc.printStackTrace();
				}
				
				if(b[0]==1)
				 {button1.doClick();}
				
				if(b[1]==1)
				 {button2.doClick();}
				
				if(b[2]==1)
				 {button3.doClick();}
				
				if(b[3]==1)
				 {button4.doClick();}
				
				if(b[4]==1)
				 {button5.doClick();}
				
				
				
			}//action listener end
		});
		btnNewButton.setBounds(22, 59, 104, 75);
		frame.getContentPane().add(btnNewButton);
		
		
		
		
		
		JButton btnEnter = new JButton("ENTER");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
		        
				double carno = Double.parseDouble(textField.getText());
				try
				{
					Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/parking?autoReconnect=true&useSSL=false","root","@Kush98#");
					PreparedStatement mystat =myConn.prepareStatement("insert into pparking (car_no,in_time) values(?,?)");
					java.sql.Timestamp sqlTime=new java.sql.Timestamp(d.getTime());
		            
					mystat.setDouble(1, carno);
				    mystat.setTimestamp(2,sqlTime);
					mystat.executeUpdate();
					
					
				}
				catch(Exception exc)
				{
					exc.printStackTrace();
				}
			}
		});
		btnEnter.setBounds(157, 286, 89, 23);
		frame.getContentPane().add(btnEnter);
		
		JLabel lblAvai = new JLabel("Avai.");
		lblAvai.setBounds(147, 27, 31, 14);
		frame.getContentPane().add(lblAvai);
		
		JLabel lblOccu = new JLabel("Occu.");
		lblOccu.setBounds(239, 27, 31, 14);
		frame.getContentPane().add(lblOccu);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\eclipse-workplace\\ParkingManagementProgram\\Photos\\free.PNG"));
		lblNewLabel_1.setBounds(184, 27, 31, 27);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\eclipse-workplace\\ParkingManagementProgram\\Photos\\occu.PNG"));
		label.setBounds(292, 27, 31, 27);
		frame.getContentPane().add(label);
		
		
	}
}
